package com.sravan.spendlens.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ToolRequest {

    @NotBlank(message = "Tool name is required")
    private String toolName;

    @NotBlank(message = "Plan is required")
    private String plan;

    @NotNull(message = "Monthly spend is required")
    @Min(value = 1, message = "Monthly spend must be greater than 0")
    private Double monthlySpend;

    @NotNull(message = "Seats are required")
    @Min(value = 1, message = "Seats must be at least 1")
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