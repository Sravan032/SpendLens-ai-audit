# SpendLens AI Audit

AI-powered SaaS cost optimization platform that analyzes organizational AI tool spending and generates actionable recommendations to reduce unnecessary expenses.

---

# Live Demo

## Frontend
https://spendlens-ai-audit-2.onrender.com

## Backend API
https://spendlens-ai-audit-1.onrender.com

---

# Features

- AI tool spend analysis
- Executive summary generation using OpenAI
- AI subscription optimization recommendations
- Estimated monthly & annual savings
- Shareable audit reports
- Interactive charts & analytics
- REST API architecture
- Full-stack deployment

---

# Tech Stack

## Frontend
- Next.js
- React
- TypeScript
- Tailwind CSS
- Axios
- Recharts

## Backend
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- OpenAI API

## Deployment
- Render
- Docker
- GitHub

---

# Architecture

Frontend (Next.js)  
↓  
REST API Calls  
↓  
Spring Boot Backend  
↓  
AI Recommendation Engine + OpenAI Summary Generation  
↓  
Database Storage  

---

# Screenshots

## Dashboard
<img width="1808" height="1320" alt="Screenshot 2026-05-27 200602" src="https://github.com/user-attachments/assets/dbe9862c-531b-475c-815e-def6a0a73bb0" />


## Audit Results
<img width="1726" height="1423" alt="Screenshot 2026-05-27 200628" src="https://github.com/user-attachments/assets/8066579f-97fd-468d-99b9-357d4a57406f" />


## Shared Report
<img width="1785" height="954" alt="image" src="https://github.com/user-attachments/assets/f94c3f27-eecf-4d72-94c5-1d7c07faae72" />


---

# API Endpoints

## Generate Audit

### POST `/api/audit`

### Request Body

```json
{
  "teamSize": 3,
  "useCase": "coding",
  "tools": [
    {
      "toolName": "ChatGPT",
      "plan": "Plus",
      "monthlySpend": 2000,
      "seats": 3
    }
  ]
}
```

---

## Get Shared Report

### GET `/api/audit/{shareId}`

---

# Local Setup

## Clone Repository

```bash
git clone https://github.com/Sravan032/spendlens-ai-audit.git
```

---

## Backend Setup

```bash
./mvnw spring-boot:run
```

---

## Frontend Setup

```bash
cd frontend

npm install

npm run dev
```

---

# Environment Variables

## Backend

```properties
OPENAI_API_KEY=your_openai_key
```

---

## Frontend

```properties
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```

---

# Folder Structure

```text
spendlens/
│
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   ├── services/
│   │   └── lib/
│   │
│   ├── package.json
│
├── src/
│   ├── main/
│   └── test/
│
├── pom.xml
├── Dockerfile
└── README.md
```

---

# Future Improvements

- Authentication
- PDF export
- Audit history
- Subscription tracking
- Real-time spend monitoring

---

# Author

## Sravan Jyothi Reddy

GitHub:  
https://github.com/Sravan032
