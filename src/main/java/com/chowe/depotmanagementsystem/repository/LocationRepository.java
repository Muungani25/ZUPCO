package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository  extends JpaRepository<Location,Long> {
    Optional<Location> findFirstByOrderByIdDesc();
}
