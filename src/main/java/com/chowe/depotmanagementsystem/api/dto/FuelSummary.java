package com.chowe.depotmanagementsystem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class FuelSummary {
    private String fleetNumber;
    private BigDecimal allocatedFuel;
    private BigDecimal remainingFuel;
}
