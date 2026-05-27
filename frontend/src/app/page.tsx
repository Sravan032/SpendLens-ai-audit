"use client";

import { useState } from "react";

import { useFieldArray, useForm } from "react-hook-form";

import api from "../services/api";

import { TOOL_OPTIONS } from "../../lib/tools";

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

    try {

      const response =
        await api.post(
          "/audit",
          data
        );

      setAuditResult(
        response.data
      );

      alert(
        "Audit Generated Successfully"
      );

    } catch (error) {

      console.error(error);

      alert(
        "Failed to generate audit"
      );
    }
  };

  return (

    <main className="min-h-screen bg-gray-100 p-6 md:p-10">

      <div className="max-w-5xl mx-auto bg-white rounded-2xl shadow-lg p-6 md:p-8">

        <h1 className="text-3xl md:text-4xl font-bold mb-8">
          SpendLens AI Audit
        </h1>

        <form
          onSubmit={handleSubmit(onSubmit)}
          className="space-y-8"
        >

          <div>

            <label className="block mb-2 font-medium">
              Team Size
            </label>

            <input
              type="number"
              {...register("teamSize", {
                valueAsNumber: true,
              })}
              className="w-full border p-3 rounded"
              placeholder="e.g. 10"
            />

          </div>

          <div>

            <label className="block mb-2 font-medium">
              Primary Use Case
            </label>

            <select
              {...register("useCase")}
              className="w-full border p-3 rounded"
            >

              <option value="">
                Select Use Case
              </option>

              <option value="coding">
                Coding
              </option>

              <option value="research">
                Research
              </option>

              <option value="writing">
                Writing
              </option>

            </select>

          </div>

          <div className="space-y-6">

            <div className="flex justify-between items-center">

              <h2 className="text-2xl font-semibold">
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
                className="bg-black text-white px-4 py-2 rounded"
              >
                Add Tool
              </button>

            </div>

            {fields.map((field, index) => (

              <div
                key={field.id}
                className="border rounded-xl p-6 bg-gray-50 grid grid-cols-1 md:grid-cols-2 gap-4"
              >

                <div>

                  <label className="block mb-2">
                    Tool Name
                  </label>

                  <select
                    {...register(`tools.${index}.toolName`)}
                    className="w-full border p-3 rounded"
                  >

                    <option value="">
                      Select Tool
                    </option>

                    {Object.keys(TOOL_OPTIONS).map(
                      (tool) => (

                        <option
                          key={tool}
                          value={tool}
                        >
                          {tool}
                        </option>
                      )
                    )}

                  </select>

                </div>

                <div>

                  <label className="block mb-2">
                    Plan
                  </label>

                  <select
                    {...register(`tools.${index}.plan`)}
                    className="w-full border p-3 rounded"
                  >

                    <option value="">
                      Select Plan
                    </option>

                    {TOOL_OPTIONS[
                      watch(
                        `tools.${index}.toolName`
                      ) as keyof typeof TOOL_OPTIONS
                    ]?.map((plan) => (

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

                  <label className="block mb-2">
                    Monthly Spend (₹)
                  </label>

                  <input
                    type="number"
                    placeholder="e.g. 1200"
                    {...register(
                      `tools.${index}.monthlySpend`,
                      {
                        valueAsNumber: true,
                      }
                    )}
                    className="w-full border p-3 rounded"
                  />

                </div>

                <div>

                  <label className="block mb-2">
                    Seats
                  </label>

                  <input
                    type="number"
                    placeholder="e.g. 5"
                    {...register(
                      `tools.${index}.seats`,
                      {
                        valueAsNumber: true,
                      }
                    )}
                    className="w-full border p-3 rounded"
                  />

                </div>

                {fields.length > 1 && (

                  <button
                    type="button"
                    onClick={() => remove(index)}
                    className="bg-red-500 text-white px-4 py-2 rounded col-span-1 md:col-span-2"
                  >
                    Remove Tool
                  </button>

                )}

              </div>
            ))}

          </div>

          <button
            type="submit"
            className="w-full bg-black text-white py-4 rounded-xl text-lg font-semibold"
          >
            Generate Audit
          </button>

        </form>

        {auditResult && (

          <>

            <div className="mt-10 space-y-6">

              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

                <div className="bg-green-100 p-6 rounded-2xl">

                  <h2 className="text-xl font-semibold mb-2">
                    Monthly Savings
                  </h2>

                  <p className="text-3xl font-bold">
                    ₹{auditResult.totalMonthlySavings}
                  </p>

                </div>

                <div className="bg-blue-100 p-6 rounded-2xl">

                  <h2 className="text-xl font-semibold mb-2">
                    Annual Savings
                  </h2>

                  <p className="text-3xl font-bold">
                    ₹{auditResult.totalAnnualSavings}
                  </p>

                </div>

              </div>

              <div className="bg-white border rounded-2xl p-6">

                <h2 className="text-2xl font-semibold mb-4">
                  Executive Summary
                </h2>

                <p className="text-gray-700 leading-7">
                  {auditResult.executiveSummary}
                </p>

              </div>

              <div className="bg-white border rounded-2xl p-6">

                <h2 className="text-2xl font-semibold mb-4">
                  Recommendations
                </h2>

                <div className="space-y-4">

                  {auditResult.recommendations?.length > 0 ? (

                    auditResult.recommendations.map(
                      (recommendation, index) => (

                        <div
                          key={index}
                          className="border rounded-xl p-4 bg-gray-50"
                        >

                          <h3 className="text-lg font-semibold">
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

                          <p className="text-gray-600 mt-2">
                            {recommendation.reason}
                          </p>

                        </div>
                      )
                    )

                  ) : (

                    <p className="text-gray-500">
                      No recommendations available for this report.
                    </p>

                  )}

                </div>

              </div>

              <div className="bg-yellow-100 p-6 rounded-2xl">

                <h2 className="text-xl font-semibold mb-2">
                  Shared Report
                </h2>

                <a
                  href={`/share/${auditResult.shareId}`}
                  target="_blank"
                  className="text-blue-600 underline text-xl font-semibold"
                >
                  View Shared Report
                </a>

              </div>

            </div>

            <div className="bg-white border rounded-2xl p-6 mt-6">

              <h2 className="text-2xl font-semibold mb-6">
                Savings Analytics
              </h2>

              <div className="w-full h-80">

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
                      radius={[10, 10, 0, 0]}
                    />

                  </BarChart>

                </ResponsiveContainer>

              </div>

            </div>

          </>

        )}

      </div>

    </main>
  );
}