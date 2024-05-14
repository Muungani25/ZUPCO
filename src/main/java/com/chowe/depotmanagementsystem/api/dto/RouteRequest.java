package com.chowe.depotmanagementsystem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteRequest {
    private String to;
    private String from;
    private BigDecimal distance;
}
