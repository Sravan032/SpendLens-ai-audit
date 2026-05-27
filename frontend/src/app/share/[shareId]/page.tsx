"use client";

import { useEffect, useState } from "react";

import { useParams } from "next/navigation";

import api from "../../../services/api";

export default function ShareReportPage() {

  const params = useParams();

  const shareId =
    params.shareId;

  const [audit, setAudit] =
    useState<any>(null);

  const [loading, setLoading] =
    useState(true);

  const [error, setError] =
    useState("");

  useEffect(() => {

    const fetchAudit =
      async () => {

        try {

          const response =
            await api.get(
              `/audit/${shareId}`
            );

          setAudit(
            response.data
          );

        } catch (err) {

          console.error(err);

          setError(
            "Failed to load report"
          );

        } finally {

          setLoading(false);
        }
      };

    if (shareId) {

      fetchAudit();
    }

  }, [shareId]);

  if (loading) {

    return (

      <main className="min-h-screen flex items-center justify-center">

        <p className="text-2xl font-semibold">
          Loading Report...
        </p>

      </main>
    );
  }

  if (error) {

    return (

      <main className="min-h-screen flex items-center justify-center">

        <p className="text-red-500 text-2xl">
          {error}
        </p>

      </main>
    );
  }

  if (!audit) {

      return null;
  }
  return (

    <main className="min-h-screen bg-gray-100 p-10">

      <div className="max-w-5xl mx-auto bg-white rounded-2xl shadow-lg p-8">

        <h1 className="text-4xl font-bold mb-8">
          Shared SpendLens Report
        </h1>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">

          <div className="bg-green-100 p-6 rounded-2xl">

            <h2 className="text-xl font-semibold mb-2">
              Monthly Savings
            </h2>

            <p className="text-3xl font-bold">
              ₹{audit.totalMonthlySavings}
            </p>

          </div>

          <div className="bg-blue-100 p-6 rounded-2xl">

            <h2 className="text-xl font-semibold mb-2">
              Annual Savings
            </h2>

            <p className="text-3xl font-bold">
              ₹{audit.totalAnnualSavings}
            </p>

          </div>

        </div>

        <div className="bg-white border rounded-2xl p-6 mb-8">

          <h2 className="text-2xl font-semibold mb-4">
            Executive Summary
          </h2>

          <p className="text-gray-700 leading-7">
            {audit.executiveSummary}
          </p>

        </div>

        <div className="bg-white border rounded-2xl p-6">

          <h2 className="text-2xl font-semibold mb-4">
            Recommendations
          </h2>

          <div className="space-y-4">

            {audit.recommendations?.length > 0 ? (

              audit.recommendations.map(
                (recommendation: any, index: number) => (

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
              ))
            ) : (

                <p className="text-gray-500">
                  No recommendations available for this report.
                </p>

            )}

          </div>

        </div>

      </div>

    </main>
  );
}