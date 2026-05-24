package com.sravan.spendlens.repository;

import com.sravan.spendlens.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit,Long> {
}
