package com.chowe.depotmanagementsystem.api;

import com.chowe.depotmanagementsystem.api.dto.CreateDriverRequest;
import com.chowe.depotmanagementsystem.api.dto.DeactivateRequest;
import com.chowe.depotmanagementsystem.domain.Driver;
import com.chowe.depotmanagementsystem.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/add_new")
    public ResponseEntity<?> addNewDriver(@RequestBody CreateDriverRequest request) {
        return driverService.addNewDriver(request);

    }

    @GetMapping("/get_all_Drivers")
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @GetMapping("/get_active_drivers")
    public List<Driver> getAllActiveDrivers(){
      return  driverService.getAllActiveDrivers();
    }

    @DeleteMapping("/deactivate_driver")
    public ResponseEntity<?> deactivateDriver(@RequestParam String id){
        return driverService.deactivateDriver(id);
    }

    @GetMapping("/not_assigned")
    public List<?> getAllDriversNotAssigned(){
        return driverService.getAllDriversNotAssigned();
    }


}
