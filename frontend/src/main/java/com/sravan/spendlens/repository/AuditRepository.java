package com.sravan.spendlens.repository;

import com.sravan.spendlens.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditRepository extends JpaRepository<Audit,Long> {
    Optional<Audit> findByShareId(String shareId);
}
