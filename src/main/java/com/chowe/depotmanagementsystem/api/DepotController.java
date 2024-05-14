package com.chowe.depotmanagementsystem.api;

import com.chowe.depotmanagementsystem.api.dto.*;
import com.chowe.depotmanagementsystem.service.BusService;
import com.chowe.depotmanagementsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/depot")
@Slf4j
@CrossOrigin
public class DepotController {
private final RouteService routeService;
private final BusService busService;

    @PostMapping("/dispatch")
    public ResponseEntity<Response> dispatch(@RequestBody DispatchRequest dispatchRequest){

        return routeService.createDispatch(dispatchRequest);
    }
    @PostMapping("/add-routes")
    public ResponseEntity<?> addRoutes(@RequestBody RouteRequest routeRequest){

        return routeService.addNewRoutes(routeRequest);
    }
    @GetMapping("/get_all_routes")
    public List<?> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @PostMapping("/fuel_level")
    public void fuelLevel(@RequestBody FuelLevel fuelLevel){
        log.info("receiving fuel level reading.........,{}",fuelLevel);

         busService.saveFuelLevel(fuelLevel);
    }
    @GetMapping("/get_latest_fuel_level_at_depot")
    public ResponseEntity<?> getFuelLevel(){
        return busService.getFuelLevel();
    }
}
