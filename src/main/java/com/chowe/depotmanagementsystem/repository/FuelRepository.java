package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel,Long> {
    Optional<Fuel> findTopByOrderByIdDesc();
}
