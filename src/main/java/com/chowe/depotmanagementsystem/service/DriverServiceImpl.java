package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.CreateDriverRequest;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Driver;
import com.chowe.depotmanagementsystem.repository.BusRepository;
import com.chowe.depotmanagementsystem.repository.DriverRepository;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import com.chowe.depotmanagementsystem.service.util.DriverIdGenerator;
import com.chowe.depotmanagementsystem.service.util.MobileNumberFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{
    private final DriverRepository driverRepository;
    private final BusRepository busRepository;
    @Override
    public ResponseEntity<Response> addNewDriver(CreateDriverRequest request) {

        driverRepository.save(Driver.builder()
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .phoneNumber("0778873016")
                .driverId(DriverIdGenerator.generateDriverId())
                        .isAssigned(false)
                        .isActive(true)
                .build());
        return ResponseEntity.ok(Response.builder()
                .message("driver added successfully")
                .build());
    }

    @Override
    public List<Driver> getAllDrivers() {
        if (!driverRepository.findAll().isEmpty()) {
            return driverRepository.findAll();
        } else {
            throw new ResourceNotFoundException("no driver found");
        }

    }

    @Override
    public List<Bus> getAllBusesNotDispatched() {

        return busRepository.findByIsDispatchedFalse();
    }

    @Override
    public List<Bus> getAllBuses() {
        return null;
    }

    @Override
    public List<Driver> getAllActiveDrivers() {
        return driverRepository.findAllByIsActiveTrue();
    }

    @Override
    public List<Driver> getAllDriversNotAssigned() {
        return driverRepository.findAllByIsAssignedFalse();
    }

    @Override
    public ResponseEntity<Response> deactivateDriver(String idNumber) {
        var driver= driverRepository.findDriverByDriverId(idNumber);
        driver.ifPresent(value -> value.setActive(false));

        driverRepository.save(driver.get());
        return ResponseEntity.ok(Response.builder()
                .message("driver deactivated")
                .build());

    }


}
