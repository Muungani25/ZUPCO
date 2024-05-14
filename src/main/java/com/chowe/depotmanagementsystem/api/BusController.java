package com.chowe.depotmanagementsystem.api;

import com.chowe.depotmanagementsystem.api.dto.BusRequest;
import com.chowe.depotmanagementsystem.api.dto.DeactivateRequest;
import com.chowe.depotmanagementsystem.api.dto.GpsRequest;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.service.BusService;
import com.chowe.depotmanagementsystem.service.DriverService;
import com.chowe.depotmanagementsystem.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bus")
@CrossOrigin
@Slf4j
public class BusController {
    private final BusService busService;
    private final DriverService service;
    private final LocationService locationService;

    @PostMapping("/add_new")
    public ResponseEntity<?> addNewBusses(@RequestBody BusRequest request){
        return busService.addNewBus(request);
    }

    @GetMapping("/get_all_buses")
    public List<?> getAll(){
        return busService.getAll();
    }

    @GetMapping("/get_all_buses_not_dispatched")
    public List<?> getAllBusesNotDispatched(){
        return service.getAllBusesNotDispatched();
    }

    @GetMapping("/get_all_dispatched_buses")
    public List<?> getAllBusesDispatched(){
        return busService.getAllBusesDispatched();
    }

    @GetMapping("/get_active_buses")
    public List<?> getAllActiveBuses(){
        return  busService.getAllActiveBuses();
    }

    @DeleteMapping("/deactivate_bus")
    public ResponseEntity<?> deactivateBus(@RequestParam String id){
        return busService.deactivateBus(id);
    }

    @GetMapping("/fuel_summary")
    public List<?> getFuelSummary(){
        return busService.getFuelSummary();
    }

    @PostMapping("/gps")
    public void getGps(@RequestBody GpsRequest gps){
        log.info("receiving....{}",gps);
locationService.saveLocation(gps);

    }
    @GetMapping("/get_speed")
    public ResponseEntity<?> getSpeed(){
        return locationService.getSpeed();
    }
}
