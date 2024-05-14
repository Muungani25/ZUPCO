package com.chowe.depotmanagementsystem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusRequest {
    private String fleetNumber;
    private String driverId;
    private BigDecimal engineConsumptionRate;


}
