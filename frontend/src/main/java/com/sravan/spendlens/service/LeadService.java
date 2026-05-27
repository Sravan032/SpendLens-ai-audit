package com.sravan.spendlens.service;

import com.sravan.spendlens.dto.LeadRequest;
import com.sravan.spendlens.entity.Lead;
import com.sravan.spendlens.repository.LeadRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {

        this.leadRepository = leadRepository;
    }

    public Lead createLead(LeadRequest request) {

        Lead lead = new Lead();

        lead.setName(request.getName());

        lead.setEmail(request.getEmail());

        lead.setCompany(request.getCompany());

        lead.setRole(request.getRole());

        lead.setTeamSize(request.getTeamSize());

        lead.setCreatedAt(LocalDateTime.now());

        return leadRepository.save(lead);
    }
}