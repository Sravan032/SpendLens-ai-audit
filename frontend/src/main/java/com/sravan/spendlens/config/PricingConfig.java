package com.sravan.spendlens.config;

import java.util.HashMap;
import java.util.Map;

public class PricingConfig {

    public static final Map<String, Double> PLAN_PRICING =
            new HashMap<>();

    static {

        // ChatGPT
        PLAN_PRICING.put(
                "ChatGPT_Plus",
                2000.0
        );

        PLAN_PRICING.put(
                "ChatGPT_Team",
                6000.0
        );

        // Cursor
        PLAN_PRICING.put(
                "Cursor_Pro",
                2000.0
        );

        PLAN_PRICING.put(
                "Cursor_Business",
                4000.0
        );

        // Claude
        PLAN_PRICING.put(
                "Claude_Pro",
                2000.0
        );

        PLAN_PRICING.put(
                "Claude_Team",
                3500.0
        );
    }
}