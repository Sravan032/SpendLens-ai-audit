package com.sravan.spendlens.dto;

import java.util.List;

public class AuditResponse {

    private Double totalMonthlySavings;

    private Double totalAnnualSavings;

    private Double totalMonthlySpend;

    private String executiveSummary;

    private List<RecommendationResponse> recommendations;
    private String shareId;

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

    public String getShareId(){
        return shareId;
    }

    public void setShareId(String shareId){
        this.shareId=shareId;
    }

    public Double getTotalMonthlySpend(){
        return totalMonthlySpend;
    }

    public void setTotalMonthlySpend(Double totalMonthlySpend){
        this.totalMonthlySpend=totalMonthlySpend;
    }

    public String getExecutiveSummary(){
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }
}