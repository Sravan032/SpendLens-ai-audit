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
                20.0
        );

        PLAN_PRICING.put(
                "ChatGPT_Team",
                60.0
        );

        // Cursor
        PLAN_PRICING.put(
                "Cursor_Pro",
                20.0
        );

        PLAN_PRICING.put(
                "Cursor_Business",
                40.0
        );

        // Claude
        PLAN_PRICING.put(
                "Claude_Pro",
                20.0
        );

        PLAN_PRICING.put(
                "Claude_Team",
                35.0
        );
    }
}