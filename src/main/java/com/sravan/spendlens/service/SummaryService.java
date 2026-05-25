package com.sravan.spendlens.service;

import com.sravan.spendlens.dto.RecommendationResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {

    public String generateExecutiveSummary(

            List<RecommendationResponse> recommendations,

            Double totalMonthlySavings,

            Double totalAnnualSavings
    ) {

        if (recommendations.isEmpty()) {

            return "Your current AI tooling stack appears reasonably optimized with limited cost-saving opportunities identified.";
        }

        StringBuilder summary =
                new StringBuilder();

        summary.append(
                "Our audit identified potential AI spend optimization opportunities totaling approximately ₹"
        );

        summary.append(
                totalMonthlySavings.intValue()
        );

        summary.append(
                " per month and ₹"
        );

        summary.append(
                totalAnnualSavings.intValue()
        );

        summary.append(
                " annually. "
        );

        summary.append(
                "Key recommendations include optimizing subscription tiers, reducing overlapping AI tooling, and aligning plans more closely with actual team usage patterns."
        );

        return summary.toString();
    }
}