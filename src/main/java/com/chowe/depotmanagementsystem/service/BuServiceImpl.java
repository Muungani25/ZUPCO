package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.*;
import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Fuel;
import com.chowe.depotmanagementsystem.espclient.EspClient;
import com.chowe.depotmanagementsystem.repository.BusRepository;
import com.chowe.depotmanagementsystem.repository.DriverRepository;
import com.chowe.depotmanagementsystem.repository.FuelRepository;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceAlreadyExists;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuServiceImpl implements BusService{
    private final DriverRepository driverRepository;
    private final BusRepository busRepository;
    private final FuelRepository fuelRepository;
    private final EspClient espClient;
    @Override
    public ResponseEntity<Response> addNewBus(BusRequest request) {


        var driver = driverRepository.findDriverByDriverId(request.getDriverId());
        if(driver.get().isAssigned()) {
            throw new ResourceAlreadyExists(" driver already assigned");
        }
        driver.get().setAssigned(true);
        busRepository.save(Bus.builder()
                .engineConsumptionRate(request.getEngineConsumptionRate())
                .driver(driver.get())
                .fleetNumber(request.getFleetNumber())
                .isDispatched(false)
                        .isActive(true)
                .build());
        return ResponseEntity.ok(Response.builder()
                        .message("bus added")
                .build());
    }

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    @Override
    public List<Bus> getAllBusesDispatched() {
        return busRepository.findByIsDispatchedTrue();
    }

    @Override
    public  ResponseEntity<Fuel> getFuelLevel() {
        Optional<Fuel> optionalFuelLevel = fuelRepository.findTopByOrderByIdDesc();
        if (optionalFuelLevel.isPresent()) {
            Fuel lastFuelLevel = optionalFuelLevel.get();
            return ResponseEntity.ok(lastFuelLevel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void saveFuelLevel(FuelLevel fuelLevel) {
        fuelRepository.save(Fuel.builder()
                        .level(String.valueOf(fuelLevel.getLevel()))
                .build());
    }

    @Override
    public ResponseEntity<Response> deactivateBus(String idNumber) {
        var bus= busRepository.findBusByFleetNumber(idNumber);
        bus.ifPresent(value -> value.setActive(false));

        busRepository.save(bus.get());
        return ResponseEntity.ok(Response.builder()
                        .message("bus deactivated")
                .build());
    }

    @Override
    public List<FuelSummary> getFuelSummary() {
        List<Bus> buses = busRepository.findAll();

        return buses.stream()
                .map(this::buildFuelSummary)
                .collect(Collectors.toList());
    }

    @Override
    public Response dispenseFuel(String fleetNumber) {
        var bus = busRepository.findByFleetNumber(fleetNumber)
                .orElseThrow(()-> new ResourceNotFoundException("No such bus is recorded in the system"));
        var remainingFuel = bus.getRemainingFuel()== null ? BigDecimal.ZERO : bus.getRemainingFuel();

        var fuelToDisburse = bus.getAllocatedFuel().subtract(remainingFuel).abs();
        espClient.sendDispenseFuelSignalToEsp(fuelToDisburse);
        return Response.builder()
                .message("Fuel Disbursement process initiated")
                .build();
    }

    @Override
    public List<Bus> getAllActiveBuses() {
        return busRepository.findByIsActiveTrue();
    }

    private FuelSummary buildFuelSummary(Bus bus){

       return FuelSummary.builder()
               .fleetNumber(bus.getFleetNumber())
               .allocatedFuel(bus.getAllocatedFuel())
               .remainingFuel(bus.getRemainingFuel())
                .build();
    }
}
