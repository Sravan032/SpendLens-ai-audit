package com.sravan.spendlens.dto;

import java.util.List;

public class AuditResponse {

    private Double totalMonthlySavings;

    private Double totalAnnualSavings;

    private List<RecommendationResponse> recommendations;

    public AuditResponse() {
    }

    public Double getTotalMonthlySavings() {
        return totalMonthlySavings;
    }

    public void setTotalMonthlySavings(Double totalMonthlySavings) {
        this.totalMonthlySavings = totalMonthlySavings;
    }

    public Double getTotalAnnualSavings() {
        return totalAnnualSavings;
    }

    public void setTotalAnnualSavings(Double totalAnnualSavings) {
        this.totalAnnualSavings = totalAnnualSavings;
    }

    public List<RecommendationResponse> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationResponse> recommendations) {
        this.recommendations = recommendations;
    }
}