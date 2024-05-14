package com.chowe.depotmanagementsystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fleetNumber;
    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private BigDecimal engineConsumptionRate;
    private BigDecimal allocatedFuel;
    private BigDecimal remainingFuel;
    @ManyToOne
    private Route route;
    private boolean isDispatched;
    private boolean isActive;


}
