package com.chowe.depotmanagementsystem.espclient;

import com.chowe.depotmanagementsystem.espdata.EspDataRepository;
import com.chowe.depotmanagementsystem.service.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class EspClientImpl implements EspClient{

    private final RestTemplate restTemplate;
    private final EspDataRepository espDataRepository;

    @Override
    public Object sendDispenseFuelSignalToEsp(BigDecimal fuelLitres) {
        var espData = espDataRepository.findByEspBoardName("esp8266")
                .orElseThrow(()-> new ResourceNotFoundException("Esp board not found"));
        var ipAddress = espData.getIpAddress();
        Object response = restTemplate.getForObject
                (String.format("http://%s/dispense_fuel/%s", ipAddress, fuelLitres), Object.class);
        assert response != null;
        log.info("{}",response);
        return response;
    }
}

