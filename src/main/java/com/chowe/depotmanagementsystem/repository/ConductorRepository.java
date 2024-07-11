package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Conductor;
import com.chowe.depotmanagementsystem.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor,Long> {
    Optional<Conductor> findFirstByIsAssignedFalse();
}
