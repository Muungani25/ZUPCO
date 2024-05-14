package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.CreateDriverRequest;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {
    ResponseEntity<Response> addNewDriver(CreateDriverRequest request);

    List<Driver> getAllDrivers();

    List<Bus> getAllBusesNotDispatched();

    List<Bus> getAllBuses();

  List<Driver> getAllActiveDrivers();

  List<Driver> getAllDriversNotAssigned();

  ResponseEntity<Response> deactivateDriver(String idNumber);


}
