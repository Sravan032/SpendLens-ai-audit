"use client";

import { useState } from "react";

import { useFieldArray, useForm } from "react-hook-form";

import api from "../services/api";

import { TOOL_OPTIONS } from "../lib/tools";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";

type Tool = {
  toolName: string;
  plan: string;
  monthlySpend: number;
  seats: number;
};

type AuditForm = {
  teamSize: number;
  useCase: string;
  tools: Tool[];
};

type Recommendation = {
  toolName: string;
  currentPlan: string;
  recommendedPlan: string;
  savings: number;
  reason: string;
};

type AuditResult = {
  totalMonthlySavings: number;
  totalAnnualSavings: number;
  executiveSummary: string;
  shareId: string;
  recommendations: Recommendation[];
};

export default function Home() {

  const {
    register,
    control,
    handleSubmit,
    watch,
  } = useForm<AuditForm>({
    defaultValues: {
      teamSize: 1,
      useCase: "",
      tools: [
        {
          toolName: "",
          plan: "",
          monthlySpend: 0,
          seats: 1,
        },
      ],
    },
  });

  const {
    fields,
    append,
    remove,
  } = useFieldArray({
    control,
    name: "tools",
  });

  const [auditResult, setAuditResult] =
    useState<AuditResult | null>(null);

  const [loading, setLoading] =
    useState(false);

  const chartData = auditResult
    ? [
        {
          name: "Monthly Savings",
          amount: auditResult.totalMonthlySavings,
        },
        {
          name: "Annual Savings",
          amount: auditResult.totalAnnualSavings,
        },
      ]
    : [];

  const onSubmit = async (
    data: AuditForm
  ) => {

    setLoading(true);

    try {

      const response =
        await api.post(
          "/audit",
          data
        );

      setAuditResult(
        response.data
      );

    } catch (error) {

      console.error(error);

      alert(
        "Failed to generate audit"
      );

    } finally {

      setLoading(false);
    }
  };

  return (

    <main className="min-h-screen bg-gray-100 p-10">

      <div className="max-w-6xl mx-auto bg-white rounded-2xl shadow-lg p-8">

        <h1 className="text-5xl font-bold mb-8">
          SpendLens AI Audit
        </h1>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className="space-y-8"
        >

          <div>

            <label className="block mb-2 font-semibold">
              Team Size
            </label>

            <input
              type="number"
              {...register("teamSize")}
              className="
                border
                rounded-xl
                px-4
                py-3
                w-full
              "
            />

          </div>

          <div>

            <label className="block mb-2 font-semibold">
              Primary Use Case
            </label>

            <select
              {...register("useCase")}
              className="
                border
                rounded-xl
                px-4
                py-3
                w-full
              "
            >

              <option value="">
                Select Use Case
              </option>

              <option value="coding">
                Coding
              </option>

              <option value="marketing">
                Marketing
              </option>

              <option value="research">
                Research
              </option>

            </select>

          </div>

          <div>

            <div className="
              flex
              justify-between
              items-center
              mb-4
            ">

              <h2 className="
                text-2xl
                font-bold
              ">
                AI Tools
              </h2>

              <button
                type="button"
                onClick={() =>
                  append({
                    toolName: "",
                    plan: "",
                    monthlySpend: 0,
                    seats: 1,
                  })
                }
                className="
                  bg-black
                  text-white
                  px-4
                  py-2
                  rounded-xl
                "
              >
                Add Tool
              </button>

            </div>

            <div className="space-y-6">

              {fields.map(
                (field, index) => {

                  const selectedTool =
                    watch(
                      `tools.${index}.toolName`
                    );

                  return (

                    <div
                      key={field.id}
                      className="
                        border
                        rounded-2xl
                        p-6
                        bg-gray-50
                      "
                    >

                      <div className="
                        grid
                        grid-cols-2
                        gap-4
                      ">

                        <div>

                          <label className="
                            block
                            mb-2
                            font-semibold
                          ">
                            Tool Name
                          </label>

                          <select
                            {...register(
                              `tools.${index}.toolName`
                            )}
                            className="
                              border
                              rounded-xl
                              px-4
                              py-3
                              w-full
                            "
                          >

                            <option value="">
                              Select Tool
                            </option>

                            {Object.keys(
                              TOOL_OPTIONS
                            ).map((tool) => (

                              <option
                                key={tool}
                                value={tool}
                              >
                                {tool}
                              </option>
                            ))}

                          </select>

                        </div>

                        <div>

                          <label className="
                            block
                            mb-2
                            font-semibold
                          ">
                            Plan
                          </label>

                          <select
                            {...register(
                              `tools.${index}.plan`
                            )}
                            className="
                              border
                              rounded-xl
                              px-4
                              py-3
                              w-full
                            "
                          >

                            <option value="">
                              Select Plan
                            </option>

                            {(TOOL_OPTIONS[
                              selectedTool as keyof typeof TOOL_OPTIONS
                            ] || []).map((plan) => (

                              <option
                                key={plan}
                                value={plan}
                              >
                                {plan}
                              </option>
                            ))}

                          </select>

                        </div>

                        <div>

                          <label className="
                            block
                            mb-2
                            font-semibold
                          ">
                            Monthly Spend
                          </label>

                          <input
                            type="number"
                            placeholder="eg 120"
                            {...register(
                              `tools.${index}.monthlySpend`,
                              {
                                valueAsNumber: true,
                              }
                            )}
                            className="
                              border
                              rounded-xl
                              px-4
                              py-3
                              w-full
                            "
                          />

                        </div>

                        <div>

                          <label className="
                            block
                            mb-2
                            font-semibold
                          ">
                            Seats
                          </label>

                          <input
                            type="number"
                            {...register(
                              `tools.${index}.seats`,
                              {
                                valueAsNumber: true,
                              }
                            )}
                            className="
                              border
                              rounded-xl
                              px-4
                              py-3
                              w-full
                            "
                          />

                        </div>

                      </div>

                      <button
                        type="button"
                        onClick={() =>
                          remove(index)
                        }
                        className="
                          mt-4
                          text-red-500
                          font-semibold
                        "
                      >
                        Remove Tool
                      </button>

                    </div>
                  );
                }
              )}

            </div>

          </div>

          <button
            type="submit"
            disabled={loading}
            className="
              bg-black
              text-white
              px-8
              py-4
              rounded-2xl
              font-semibold
              text-lg
              disabled:opacity-50
            "
          >

            {loading
              ? "Generating Audit..."
              : "Generate Audit"
            }

          </button>

        </form>

        {auditResult && (

          <div className="mt-12 space-y-8">

            <div className="
              grid
              grid-cols-2
              gap-6
            ">

              <div className="
                bg-green-100
                rounded-2xl
                p-6
              ">

                <h2 className="
                  text-xl
                  font-semibold
                  mb-2
                ">
                  Monthly Savings
                </h2>

                <p className="
                  text-4xl
                  font-bold
                ">
                  ₹{auditResult.totalMonthlySavings}
                </p>

              </div>

              <div className="
                bg-blue-100
                rounded-2xl
                p-6
              ">

                <h2 className="
                  text-xl
                  font-semibold
                  mb-2
                ">
                  Annual Savings
                </h2>

                <p className="
                  text-4xl
                  font-bold
                ">
                  ₹{auditResult.totalAnnualSavings}
                </p>

              </div>

            </div>

            <div className="
              bg-white
              border
              rounded-2xl
              p-6
            ">

              <h2 className="
                text-2xl
                font-bold
                mb-4
              ">
                Executive Summary
              </h2>

              <p className="
                text-gray-700
                leading-7
              ">
                {auditResult.executiveSummary}
              </p>

            </div>

            <div className="
              bg-white
              border
              rounded-2xl
              p-6
            ">

              <h2 className="
                text-2xl
                font-bold
                mb-4
              ">
                Savings Chart
              </h2>

              <div className="h-80">

                <ResponsiveContainer
                  width="100%"
                  height="100%"
                >

                  <BarChart data={chartData}>

                    <XAxis dataKey="name" />

                    <YAxis />

                    <Tooltip />

                    <Bar
                      dataKey="amount"
                      fill="#000000"
                    />

                  </BarChart>

                </ResponsiveContainer>

              </div>

            </div>

            <div className="
              bg-white
              border
              rounded-2xl
              p-6
            ">

              <h2 className="
                text-2xl
                font-bold
                mb-4
              ">
                Recommendations
              </h2>

              <div className="space-y-4">

                {auditResult.recommendations?.map(
                  (
                    recommendation,
                    index
                  ) => (

                    <div
                      key={index}
                      className="
                        border
                        rounded-xl
                        p-4
                        bg-gray-50
                      "
                    >

                      <h3 className="
                        text-lg
                        font-semibold
                      ">
                        {recommendation.toolName}
                      </h3>

                      <p>
                        Current Plan:
                        {" "}
                        {recommendation.currentPlan}
                      </p>

                      <p>
                        Recommended:
                        {" "}
                        {recommendation.recommendedPlan}
                      </p>

                      <p>
                        Estimated Savings:
                        {" "}
                        ₹{recommendation.savings}
                      </p>

                      <p className="
                        text-gray-600
                        mt-2
                      ">
                        {recommendation.reason}
                      </p>

                    </div>
                  )
                )}

              </div>

            </div>

            <a
              href={`/share/${auditResult.shareId}`}
              target="_blank"
              className="
                inline-block
                bg-black
                text-white
                px-6
                py-3
                rounded-xl
                font-semibold
              "
            >
              Open Shared Report
            </a>

          </div>
        )}

      </div>

    </main>
  );
}