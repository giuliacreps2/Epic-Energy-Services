# Epic Energy Services - CRM Backend

**Epic Energy Services** is a RESTful API built with **Spring Boot 3.x**, designed for an energy provider to manage complex B2B client portfolios. The system centralizes business contact management, multi-category addresses, and invoicing processes through a scalable PostgreSQL database.

## 🚀 Key Features

- **Dynamic Search Engine**: Implementation of **Spring Data Specifications** (Criteria API) for multi-parameter searches with AND logic.
- **RBAC Security**: Role-Based Access Control via **Spring Security and JWT**, managing `USER` and `ADMIN` permissions.
- **Geographic Data Management**: Automated ingestion and cross-referencing of Italian Municipalities and Provinces from CSV sources.
- **Advanced Data Sorting**: Server-side pagination and multi-field sorting (revenue, dates, location).

## 🛠 Tech Stack

- **Backend**: Java 17/21, Spring Boot 3.x
- **Security**: Spring Security, JWT (JSON Web Token)
- **Data**: Spring Data JPA, Hibernate, PostgreSQL
- **API**: REST, Spring Data Specifications
- **Build Tool**: Maven

## 📊 Database Architecture

The project architecture focuses on relational integrity:

- **Client Management**: Linked legal and operational addresses, revenue metrics, and contact history.
- **Geographic Hierarchy**: Structured relation from Provinces to Municipalities, integrated with Client locations.
- **Invoicing System**: Dynamic invoice tracking linked to B2B clients.

## 🔌 API Endpoints & Filtering

The core functionality is the dynamic filtering at `GET /clients/details`.

| Parameter | Description | Example |
| :--- | :--- | :--- |
| `createdAt` | Filter by first contact date | `?createdAt=2025-01-01` |
| `lastContactDate` | Filter by last contact date | `?lastContactDate=2026-01-01` |
| `annualRevenue` | Filter by minimum annual revenue | `?annualRevenue=500000` |
| `name` | Filter by company name (partial match) | `?name=Energy` |
| `sortBy` | Field to sort by (e.g., companyName) | `?sortBy=companyName` |
| `direction` | Sort direction | `?direction=DESC` |

**Example Query:**

## 👥 Contributors & Roles

| Contributor | Focus Area |
| :--- | :--- |
| **Giulia Crepaldi** | **B2bClient Entity & Dynamic Search Engine (Spring Specifications)** |
| **Abdellah Bazi** | **CSV Data Ingestion & Geographic Data Management** |
| **Francesco Dattola** | **Security Layer (Login & JWT Authentication)** |
| **Francesco Alves** | **Invoicing System & Document Status Logic** |
