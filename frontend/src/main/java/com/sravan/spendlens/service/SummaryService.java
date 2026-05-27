package com.sravan.spendlens.service;

import com.sravan.spendlens.dto.RecommendationResponse;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private final WebClient webClient;

    public SummaryService(
            WebClient openAIWebClient
    ) {

        this.webClient = openAIWebClient;
    }

    public String generateExecutiveSummary(

            List<RecommendationResponse> recommendations,

            Double totalMonthlySavings,

            Double totalAnnualSavings
    ) {

        StringBuilder recommendationText =
                new StringBuilder();

        for (
                RecommendationResponse recommendation
                : recommendations
        ) {

            recommendationText.append(
                    recommendation.getToolName()
            );

            recommendationText.append(" : ");

            recommendationText.append(
                    recommendation.getReason()
            );

            recommendationText.append("\n");
        }

        String prompt = """
                You are an AI spend optimization consultant.

                Generate a concise executive summary for a company audit.

                Monthly Savings: ₹%s
                Annual Savings: ₹%s

                Recommendations:
                %s

                Keep the tone professional, concise, and business-oriented.
                """.formatted(

                totalMonthlySavings.intValue(),

                totalAnnualSavings.intValue(),

                recommendationText
        );

        Map<String, Object> requestBody =
                Map.of(

                        "model", "gpt-3.5-turbo",

                        "messages", List.of(

                                Map.of(
                                        "role", "user",
                                        "content", prompt
                                )
                        )
                );

        try {

            Map response = webClient.post()

                    .uri("/chat/completions")

                    .bodyValue(requestBody)

                    .retrieve()

                    .bodyToMono(Map.class)

                    .block();

            List choices =
                    (List) response.get("choices");

            Map choice =
                    (Map) choices.get(0);

            Map message =
                    (Map) choice.get("message");

            return message.get("content")
                    .toString();
        } catch (Exception ex) {

            return "AI summary generation temporarily unavailable. However, significant AI spend optimization opportunities were identified.";
        }
    }
}