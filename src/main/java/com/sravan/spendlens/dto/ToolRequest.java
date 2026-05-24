package com.sravan.spendlens.dto;

public class ToolRequest {

    private String toolName;

    private String plan;

    private Double monthlySpend;

    private Integer seats;

    public ToolRequest() {
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getMonthlySpend() {
        return monthlySpend;
    }

    public void setMonthlySpend(Double monthlySpend) {
        this.monthlySpend = monthlySpend;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}