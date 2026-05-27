package com.sravan.spendlens.controller;

import com.sravan.spendlens.dto.AuditRequest;
import com.sravan.spendlens.dto.AuditResponse;
import com.sravan.spendlens.entity.Audit;
import com.sravan.spendlens.service.AuditService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {
                "http://localhost:3030",
                "https://spendlens-ai-audit-2.onrender.com"
        }
)
@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping
    public AuditResponse generateAudit(@Valid @RequestBody AuditRequest request) {

        return auditService.generateAudit(
                request
        );
    }

    @GetMapping("/{shareId}")
    public Audit getAudit(@PathVariable String shareId) {

        return auditService.getAuditByShareId(
                shareId
        );
    }
}
