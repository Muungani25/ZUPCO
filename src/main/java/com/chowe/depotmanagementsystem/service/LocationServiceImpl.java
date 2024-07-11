package com.chowe.depotmanagementsystem.service;

import com.chowe.depotmanagementsystem.api.dto.GpsRequest;
import com.chowe.depotmanagementsystem.api.dto.Response;
import com.chowe.depotmanagementsystem.domain.Location;
import com.chowe.depotmanagementsystem.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;
    @Override
    public void saveLocation(GpsRequest gpsRequest) {

        locationRepository.save(Location.builder()
                        .location(gpsRequest.getLocation())
                        .speed(gpsRequest.getSpeed())
                .build());
    }

    @Override
    public ResponseEntity<GpsRequest> getSpeed() {
        return ResponseEntity.ok(GpsRequest.builder()
                .speed(locationRepository.findFirstByOrderByIdDesc().get().getSpeed())
                        .location(locationRepository.findFirstByOrderByIdDesc().get().getLocation())
                .build());
    }
}
