package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long> {

    Optional<Bus> findBusByFleetNumber(String fleetNumber);
    List<Bus> findByIsDispatchedFalse();
    List<Bus> findByIsDispatchedTrue();
    List<Bus> findByIsActiveTrue();

    Optional<Bus> findByFleetNumber(String fleetNumber);

}
