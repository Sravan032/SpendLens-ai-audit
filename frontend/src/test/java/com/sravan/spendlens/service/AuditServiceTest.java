package com.sravan.spendlens.service;

import com.sravan.spendlens.dto.*;

import com.sravan.spendlens.repository.AuditRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    void setup() {

        AuditRepository auditRepository =
                Mockito.mock(AuditRepository.class);

        ShareIdService shareIdService =
                new ShareIdService();

        SummaryService summaryService =
                Mockito.mock(SummaryService.class);

        auditService = new AuditService(
                auditRepository,
                shareIdService,
                summaryService
        );
    }

    @Test
    void shouldRecommendChatGPTDowngrade() {

        ToolRequest tool = new ToolRequest();

        tool.setToolName("ChatGPT");

        tool.setPlan("Team");

        tool.setMonthlySpend(6000.0);

        tool.setSeats(2);

        AuditRequest request =
                new AuditRequest();

        request.setTeamSize(2);

        request.setUseCase("coding");

        request.setTools(List.of(tool));

        AuditResponse response =
                auditService.generateAudit(
                        request
                );

        assertEquals(
                4000.0,
                response.getTotalMonthlySavings()
        );

        assertFalse(
                response.getRecommendations()
                        .isEmpty()
        );
    }

    @Test
    void shouldRecommendCursorDowngrade() {

        ToolRequest tool = new ToolRequest();

        tool.setToolName("Cursor");

        tool.setPlan("Business");

        tool.setMonthlySpend(4000.0);

        tool.setSeats(2);

        AuditRequest request =
                new AuditRequest();

        request.setTeamSize(2);

        request.setUseCase("coding");

        request.setTools(List.of(tool));

        AuditResponse response =
                auditService.generateAudit(
                        request
                );

        assertEquals(
                2000.0,
                response.getTotalMonthlySavings()
        );
    }

    @Test
    void shouldDetectOverlappingSubscriptions() {

        ToolRequest chatgpt =
                new ToolRequest();

        chatgpt.setToolName("ChatGPT");

        chatgpt.setPlan("Plus");

        chatgpt.setMonthlySpend(2000.0);

        chatgpt.setSeats(1);

        ToolRequest claude =
                new ToolRequest();

        claude.setToolName("Claude");

        claude.setPlan("Pro");

        claude.setMonthlySpend(2000.0);

        claude.setSeats(1);

        AuditRequest request =
                new AuditRequest();

        request.setTeamSize(1);

        request.setUseCase("research");

        request.setTools(
                List.of(chatgpt, claude)
        );

        AuditResponse response =
                auditService.generateAudit(
                        request
                );

        boolean foundOverlap =
                response.getRecommendations()
                        .stream()
                        .anyMatch(r ->

                                r.getRecommendedPlan()
                                        .contains("Consolidate")
                        );

        assertTrue(foundOverlap);
    }
}