package com.sravan.spendlens.service;

import com.sravan.spendlens.dto.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditService {

    public AuditResponse generateAudit(
            AuditRequest request
    ) {

        List<RecommendationResponse> recommendations =
                new ArrayList<>();

        double totalSavings = 0;

        for (ToolRequest tool : request.getTools()) {

            RecommendationResponse response =
                    new RecommendationResponse();

            response.setToolName(
                    tool.getToolName()
            );

            response.setCurrentPlan(
                    tool.getPlan()
            );

            double savings = 0;

            String recommendedPlan =
                    tool.getPlan();

            String reason =
                    "Current plan appears appropriate for your usage.";

            // RULE 1 — ChatGPT Team downgrade
            if (
                    tool.getToolName().equalsIgnoreCase("ChatGPT")
                            &&
                            tool.getPlan().equalsIgnoreCase("Team")
                            &&
                            tool.getSeats() <= 2
            ) {

                recommendedPlan = "Plus";

                savings = 50.0;

                reason =
                        "Small teams typically do not require collaboration features included in Team plans.";
            }

            // RULE 2 — Cursor Business downgrade
            if (
                    tool.getToolName().equalsIgnoreCase("Cursor")
                            &&
                            tool.getPlan().equalsIgnoreCase("Business")
                            &&
                            tool.getSeats() <= 2
            ) {

                recommendedPlan = "Pro";

                savings = 40.0;

                reason =
                        "Cursor Pro is usually sufficient for smaller engineering teams.";
            }

            // RULE 3 — Claude Team optimization
            if (
                    tool.getToolName().equalsIgnoreCase("Claude")
                            &&
                            tool.getPlan().equalsIgnoreCase("Team")
                            &&
                            tool.getSeats() <= 2
            ) {

                recommendedPlan = "Pro";

                savings = 30.0;

                reason =
                        "Claude Pro may provide similar value for smaller research-focused teams.";
            }

            response.setRecommendedPlan(
                    recommendedPlan
            );

            response.setSavings(
                    savings
            );

            response.setReason(
                    reason
            );

            recommendations.add(
                    response
            );

            totalSavings += savings;
        }

        AuditResponse auditResponse =
                new AuditResponse();

        auditResponse.setRecommendations(
                recommendations
        );

        auditResponse.setTotalMonthlySavings(
                totalSavings
        );

        auditResponse.setTotalAnnualSavings(
                totalSavings * 12
        );

        return auditResponse;
    }
}