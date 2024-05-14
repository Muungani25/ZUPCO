package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Bus;
import com.chowe.depotmanagementsystem.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Long> {

    Optional<Driver> findDriverByDriverId(String id);
    List<Driver> findAllByIsActiveTrue();

    List<Driver> findAllByIsAssignedFalse();
}
