package com.sravan.spendlens.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class AuditRequest {

    @NotEmpty(message = "At least one tool is required")
    @Valid
    private List<ToolRequest> tools;

    @Min(value = 1, message = "Team size must be at least 1")
    private Integer teamSize;

    @NotBlank(message = "Use case is required")
    private String useCase;

    public AuditRequest() {
    }

    public List<ToolRequest> getTools() {
        return tools;
    }

    public void setTools(List<ToolRequest> tools) {
        this.tools = tools;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}