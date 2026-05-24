package com.sravan.spendlens.dto;

import java.util.List;

public class AuditRequest {

    private List<ToolRequest> tools;

    private Integer teamSize;

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