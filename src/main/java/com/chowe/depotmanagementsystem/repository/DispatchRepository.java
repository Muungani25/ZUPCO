package com.chowe.depotmanagementsystem.repository;

import com.chowe.depotmanagementsystem.domain.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
}
