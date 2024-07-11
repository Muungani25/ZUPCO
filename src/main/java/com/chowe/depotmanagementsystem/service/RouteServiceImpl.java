package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.DispatchRequest;
import com.chowe.depotmanagementsystem.api.dto.DispatchResponse;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.api.dto.RouteRequest;
import com.chowe.depotmanagementsystem.domain.Dispatch;
import com.chowe.depotmanagementsystem.domain.Route;
import com.chowe.depotmanagementsystem.repository.*;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final BusRepository busRepository;
    private final DispatchRepository dispatchRepository;
    private final ConductorRepository conductorRepository;

    @Override
    public ResponseEntity<Response> addNewRoutes(RouteRequest request) {
        var route= Route.builder()
                .distance(request.getDistance())
                .fromDestination(request.getTo())
                .toDestination(request.getFrom())
                .build();
        routeRepository.save(route);
        return ResponseEntity.ok(Response.builder()
                        .message("route added")
                .build());
    }

    @Override
    public ResponseEntity<Response> createDispatch(DispatchRequest request) {



        var route = routeRepository.findById(request.getRouteId());
        var bus = busRepository.findBusByFleetNumber(request.getFleetNumber());
        bus.get().setDispatched(true);
        bus.get().setRoute(route.get());
        bus.get().setAllocatedFuel(calculateFuel(route.get().getDistance(),bus.get().getEngineConsumptionRate()));
        busRepository.save(bus.get());
        return ResponseEntity.ok(Response.builder()
                        .message("dispatched")
                .build());
    }

    @Override
    public List<Dispatch> getAllDispatches() {

        return dispatchRepository.findAll();
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }


    private BigDecimal calculateFuel(BigDecimal distance, BigDecimal rate){
        return distance.multiply(rate);
    }
}
