## High Level Architecture

![Architecture Diagram](Diagram%20Satrio-Architecture%2099co.png)

# System Architecture Overview

This diagram illustrates the overall architecture of the system, including the interactions between services and the public API.



## Components

- **Public API (Java) `:8080`**
  - Acts as the main entry point for external actors.
  - Communicates with internal services.

- **Listing Service (Python) `:6000`**
  - Handles listing-related operations.
  - Connects to the `Listings` database.

- **User Service (Golang) `:8000`**
  - Manages user-related data.
  - Connects to the `Users` database.

## Data Flow

1. Actor sends requests to the Public API.
2. Public API delegates requests to either:
   - Listing Service (for listings data)
   - User Service (for user data)
3. Services interact with their respective databases and return responses to the Public API.

---

## Documentation Postman

https://api.postman.com/collections/3378477-d2b91f8c-ff2f-4f68-88d1-6df5d7155a30?access_key=PMAT-01JZNSZBDHWAAD6Y6QXFXXKWVQ
