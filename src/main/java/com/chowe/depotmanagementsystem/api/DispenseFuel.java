package com.chowe.depotmanagementsystem.api;

import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class DispenseFuel {

    private final BusService busService;

    @GetMapping("/dispense_fuel/{fleetNumber}")
    public String controlRelay(@PathVariable String fleetNumber){

            return busService.dispenseFuel(fleetNumber).getMessage();


    }
    }