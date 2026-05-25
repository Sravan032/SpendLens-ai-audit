package com.sravan.spendlens.service;

import com.sravan.spendlens.config.PricingConfig;
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

                savings =
                        PricingConfig.PLAN_PRICING.get("ChatGPT_Team")
                                -
                                PricingConfig.PLAN_PRICING.get("ChatGPT_Plus");

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

                savings =
                        PricingConfig.PLAN_PRICING.get("Cursor_Business")
                                -
                                PricingConfig.PLAN_PRICING.get("Cursor_Pro");

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

                savings =
                        PricingConfig.PLAN_PRICING.get("Claude_Team")
                                -
                                PricingConfig.PLAN_PRICING.get("Claude_Pro");

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
        RecommendationResponse overlapRecommendation =
                detectOverlapRecommendations(request);

        if (overlapRecommendation != null) {

            recommendations.add(
                    overlapRecommendation
            );

            totalSavings +=
                    overlapRecommendation.getSavings();
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
    private RecommendationResponse
    detectOverlapRecommendations(AuditRequest request) {

        boolean hasChatGPT = false;

        boolean hasClaude = false;

        for (ToolRequest tool : request.getTools()) {

            if (
                    tool.getToolName()
                            .equalsIgnoreCase("ChatGPT")
            ) {

                hasChatGPT = true;
            }

            if (
                    tool.getToolName()
                            .equalsIgnoreCase("Claude")
            ) {

                hasClaude = true;
            }
        }

        if (
                hasChatGPT
                        &&
                        hasClaude
                        &&
                        (
                                request.getUseCase()
                                        .equalsIgnoreCase("writing")
                                        ||
                                        request.getUseCase()
                                                .equalsIgnoreCase("research")
                        )
        ) {

            RecommendationResponse response =
                    new RecommendationResponse();

            response.setToolName(
                    "AI Stack"
            );

            response.setCurrentPlan(
                    "ChatGPT + Claude"
            );

            response.setRecommendedPlan(
                    "Consolidate to One Assistant"
            );

            response.setSavings(20.0);

            response.setReason(
                    "Your stack includes overlapping general-purpose AI assistants. Consolidating into one primary assistant may reduce redundant spending."
            );

            return response;
        }

        return null;
    }
}