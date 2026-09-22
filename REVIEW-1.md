# OmniStock: Project Review 1

## Team Details

- Team lead: [Add name]
- Team members: [Add all member names and roll numbers]
- Department and section: [Add details]

## Problem Statement

Small and growing businesses often manage products, stock, orders, and user access through disconnected tools. This makes stock levels difficult to trust, increases manual work, and delays order processing.

OmniStock is a microservices-based inventory management system that centralizes product, inventory, order, and authentication workflows behind an API gateway.

## Problem Analysis and Requirements

- Maintain product information in one place.
- Track available inventory and reduce stock when orders are created.
- Create and retrieve orders reliably.
- Authenticate users before allowing protected operations.
- Route requests through one API gateway.
- Keep service data separated so each service can evolve independently.

## Survey and Common Issue

Survey participants: [Add number and audience].

Common issue identified: [Add the most frequent issue from the survey, for example: inaccurate stock counts caused by manual updates].

Survey evidence and selected responses: [Add chart, screenshot, or summary].

## DTI Concepts

### Empathy Map

- Says: “I need to know whether an item is actually available.”
- Thinks: “Manual updates may cause an incorrect promise to the customer.”
- Does: Checks multiple records before confirming an order.
- Feels: Frustrated by delays and uncertain stock information.

### Persona

- Name: [Create from one survey respondent]
- Role: Store or warehouse operator
- Goal: Update inventory quickly and confidently
- Pain point: Stock information is spread across separate records
- Need: A simple workflow with clear product, inventory, and order status

### Customer Journey Map

| Stage | Customer action | Pain point | OmniStock response |
| --- | --- | --- | --- |
| Discover | Looks for an item | Availability is unclear | Product information is centralized |
| Verify | Checks stock | Records may be outdated | Inventory service provides current quantity |
| Order | Places an order | Stock may be oversold | Order and inventory services work together |
| Fulfil | Processes the order | Status is difficult to track | Order data is available through the gateway |
| Review | Checks results | Reports are fragmented | Services expose consistent APIs |

## Architecture and Modules

```text
Client
  |
API Gateway :8080
  |-- Product Service :8081 --> omnistock_product
  |-- Inventory Service :8082 -> omnistock_inventory
  |-- Order Service :8083 ----> omnistock_order
  `-- Auth Service :8084 ------> omnistock_auth

Eureka Server :8761 provides service registration and discovery.
```

## Microservices and Data Handling

| Service | Responsibility | Main data |
| --- | --- | --- |
| API Gateway | Single entry point and request routing | Route configuration |
| Auth Service | Registration and login | Users |
| Product Service | Product CRUD operations | Products |
| Inventory Service | Stock tracking and reduction | Inventory records |
| Order Service | Order creation and retrieval | Orders |
| Eureka Server | Service registration | Service instances |

Each business service owns its data and exposes REST APIs. The gateway removes the public service prefix before forwarding requests, so `/product-service/products` reaches the product service as `/products`.

## Service Integration

1. The client sends a request to the API gateway.
2. The gateway forwards it to the appropriate service.
3. The order workflow uses inventory reduction when stock is committed.
4. Eureka registers available services for discovery.
5. Each service persists its own records in its database.

## Innovative Ideas

- Low-stock alerts for products below a configurable threshold.
- Audit history for inventory changes.
- Role-based access for administrators, warehouse staff, and viewers.
- Dashboard showing stock health, recent orders, and delayed fulfilment.
- Future event-driven order and inventory updates for higher scale.

## Review 1 Checklist

- Review dates: 21 September 2026 to 26 September 2026.
- Assessment is team-wise but individual for every student.
- Complete Review 1 within the allotted schedule; no additional attempt is expected.
- Only the team lead should publish the LinkedIn article.
- The article must include all team members, the selected problem statement, analysis, survey, DTI concepts, architecture, modules, data handling, integration, and innovative ideas.
- Each student should have at least 50% progress in every required MOOC course before the review.
- Suggested hashtags: `#KLUniversity #KLEF #Y24SOA #SDPReview1`.