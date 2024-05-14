package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.GpsRequest;
import com.chowe.depotmanagementsystem.api.dto.Response;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface LocationService {

    void saveLocation(GpsRequest request);
    ResponseEntity<Response> getSpeed();
}
