package com.chowe.depotmanagementsystem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchRequest {
        private Long routeId;
        private String fleetNumber;

}
