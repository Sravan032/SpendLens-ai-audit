package com.sravan.spendlens.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shareId;

    private Integer teamSize;

    private String useCase;

    private Double totalMonthlySpend;

    private Double totalMonthlySavings;

    private Double totalAnnualSavings;

    private LocalDateTime createdAt;

    public Audit() {
    }

    public Audit(Long id, String shareId, Integer teamSize, String useCase,
                 Double totalMonthlySpend, Double totalMonthlySavings,
                 Double totalAnnualSavings, LocalDateTime createdAt) {

        this.id = id;
        this.shareId = shareId;
        this.teamSize = teamSize;
        this.useCase = useCase;
        this.totalMonthlySpend = totalMonthlySpend;
        this.totalMonthlySavings = totalMonthlySavings;
        this.totalAnnualSavings = totalAnnualSavings;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
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

    public Double getTotalMonthlySpend() {
        return totalMonthlySpend;
    }

    public void setTotalMonthlySpend(Double totalMonthlySpend) {
        this.totalMonthlySpend = totalMonthlySpend;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
