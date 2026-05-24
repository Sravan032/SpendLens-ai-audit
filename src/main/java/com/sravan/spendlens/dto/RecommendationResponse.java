package com.sravan.spendlens.dto;

public class RecommendationResponse {

    private String toolName;

    private String currentPlan;

    private String recommendedPlan;

    private Double savings;

    private String reason;

    public RecommendationResponse() {
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(String currentPlan) {
        this.currentPlan = currentPlan;
    }

    public String getRecommendedPlan() {
        return recommendedPlan;
    }

    public void setRecommendedPlan(String recommendedPlan) {
        this.recommendedPlan = recommendedPlan;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}