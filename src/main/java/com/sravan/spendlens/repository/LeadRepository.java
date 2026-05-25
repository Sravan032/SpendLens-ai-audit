package com.sravan.spendlens.repository;

import com.sravan.spendlens.entity.Lead;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}