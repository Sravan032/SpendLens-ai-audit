# Architecture

## Overview

SpendLens AI Audit is a full-stack SaaS cost optimization platform that helps teams analyze AI tool spending and identify cost-saving opportunities.

The system consists of:

- Next.js frontend
- Spring Boot backend
- OpenAI integration
- H2 database
- REST API communication

---

# Frontend Architecture

The frontend is built using:

- Next.js
- React
- TypeScript
- Tailwind CSS

The frontend handles:

- User input forms
- Audit generation UI
- Charts and analytics
- Shared report pages
- API communication

API requests are handled using Axios.

---

# Backend Architecture

The backend is built using:

- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

The backend handles:

- Audit processing
- Recommendation generation
- Executive summary generation
- Shareable reports
- Database persistence

---

# AI Integration

OpenAI API is used to generate:

- Executive summaries
- Business-focused optimization insights

The backend sends recommendation data to OpenAI and receives structured summaries.

---

# Database

The application uses H2 database for persistence.

Stored entities include:

- Audit reports
- Recommendations
- Share IDs

---

# Deployment

Frontend:
- Render

Backend:
- Render

Version Control:
- GitHub

---

# Data Flow

Frontend
↓
REST API Request
↓
Spring Boot Backend
↓
Recommendation Engine
↓
OpenAI Summary Generation
↓
Database Storage
↓
Frontend Rendering
