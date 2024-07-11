package com.chowe.depotmanagementsystem.aspect;


import com.chowe.depotmanagementsystem.api.dto.DispatchRequest;
import com.chowe.depotmanagementsystem.api.dto.GpsRequest;
import com.chowe.depotmanagementsystem.bulksms.client.SmsClient;
import com.chowe.depotmanagementsystem.bulksms.data.SmsRequest;
import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Conductor;
import com.chowe.depotmanagementsystem.domain.Route;
import com.chowe.depotmanagementsystem.repository.BusRepository;
import com.chowe.depotmanagementsystem.repository.ConductorRepository;
import com.chowe.depotmanagementsystem.repository.RouteRepository;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessor {
    private final SmsClient smsClient;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final ConductorRepository conductorRepository;

    @Pointcut("execution(* com.chowe.depotmanagementsystem.service.RouteService.createDispatch(..))")
    private void dispatch() {
    }

    @Pointcut("execution(* com.chowe.depotmanagementsystem.service.LocationService.saveLocation(..))")
    private void saveLocation() {
    }

    @After(value = "dispatch()")
    public void sendDispatchNotificationToDriver(JoinPoint joinPoint) throws Throwable {
        var conductor=  conductorRepository.findFirstByIsAssignedFalse().orElseThrow(()-> new ResourceNotFoundException("conductor not found"));
        DispatchRequest dispatchRequest = (DispatchRequest) joinPoint.getArgs()[0];
        var bus = busRepository.findBusByFleetNumber(dispatchRequest.getFleetNumber())
                .orElseThrow(()-> new ResourceNotFoundException("No bus with that fleet number was found"));
        var route = routeRepository.findById(dispatchRequest.getRouteId())
                .orElseThrow(()-> new ResourceNotFoundException("No such route was found"));
        SmsRequest smsRequest = getSmsRequest(bus, dispatchRequest,route,conductor);
        SmsRequest conductorSmsRequest = getConductorSmsRequest(route,conductor);
        smsClient.sendSms(smsRequest);
        smsClient.sendSms(conductorSmsRequest);
    }

    @After(value = "saveLocation()")
    public void sendOverSpeedNotificationToManager(JoinPoint joinPoint) throws Throwable {
        GpsRequest gpsRequest = (GpsRequest) joinPoint.getArgs()[0];
        var speed = gpsRequest.getSpeed();
        if (Double.parseDouble(speed)> 10.00) {
          SmsRequest smsRequest = new SmsRequest();
            smsRequest.setTo("0782962043");//manager phone number
            smsRequest.setContent("over speeding bus");//to improve and have more info on who was driving....if we esp could also send the driver id of driver over speeding when sending gps location
          smsClient.sendSms(smsRequest);
        }
    }

    private static SmsRequest getSmsRequest(Bus bus,DispatchRequest dispatchRequest,Route route, Conductor conductor) {

        String driverPhoneNumber = bus.getDriver().getPhoneNumber();

        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setTo(driverPhoneNumber);
        smsRequest.setContent("Good day " + bus.getDriver().getFirstName() + " " + bus.getDriver().getLastName() + "(" + dispatchRequest.getFleetNumber() +
                "). You have been dispatched to a new route\n" +
                "Take off: " + route.getFromDestination() + "\n" +
                "Destination: " + route.getToDestination() + "\n" +
                "Distance: " + route.getDistance() + "km");

        return smsRequest;
    }

    private static SmsRequest getConductorSmsRequest(Route route, Conductor conductor) {

        String conductorPhoneNumber = conductor.getPhoneNumber();

        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setTo(conductorPhoneNumber);
        smsRequest.setContent("Good day " + conductor.getConductorName()   +
                "). You have been dispatched to a new route\n" +
                "Take off: " + route.getFromDestination() + "\n" +
                "Destination: " + route.getToDestination() + "\n" +
                "Distance: " + route.getDistance() + "km");

        return smsRequest;
    }
}


