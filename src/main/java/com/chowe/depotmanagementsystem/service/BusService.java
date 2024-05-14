package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.BusRequest;
import com.chowe.depotmanagementsystem.api.dto.FuelLevel;
import com.chowe.depotmanagementsystem.api.dto.FuelSummary;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Fuel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BusService {
    ResponseEntity<Response> addNewBus(BusRequest request);
    List<Bus> getAll();
List<Bus> getAllBusesDispatched();
    ResponseEntity<Fuel> getFuelLevel();
    List<Bus> getAllActiveBuses();
    void saveFuelLevel(FuelLevel fuelLevel);

    ResponseEntity<Response> deactivateBus(String idNumber);

    List<FuelSummary> getFuelSummary();

}
