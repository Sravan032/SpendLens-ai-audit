import "./globals.css";

export const metadata = {
  title: "SpendLens AI Audit",
  description: "AI Spend Optimization Dashboard",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {

  return (
    <html lang="en">

      <body>
        {children}
      </body>

    </html>
  );
}