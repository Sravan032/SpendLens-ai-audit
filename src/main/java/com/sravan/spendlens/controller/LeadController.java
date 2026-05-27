package com.sravan.spendlens.controller;

import com.sravan.spendlens.dto.LeadRequest;
import com.sravan.spendlens.entity.Lead;
import com.sravan.spendlens.service.LeadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {
                "http://localhost:3030",
                "https://spendlens-ai-audit-2.onrender.com"
        }
)
@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(
            LeadService leadService
    ) {

        this.leadService = leadService;
    }

    @PostMapping
    public Lead createLead(
            @Valid @RequestBody LeadRequest request
    ) {

        return leadService.createLead(
                request
        );
    }
}