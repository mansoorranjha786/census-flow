# CensusFlow: Standardized Group Benefits Data Api

A Spring Boot REST API that validates, cleans and transforms raw employee census data into a standardized JSON
format ready for insurance carrier rating engines.

Built for **CoverPoint Broker** to streamline the group census intake process

## Feature
- **Employee Data Validation**: Validate Name, DOB, ZIP Code, and Tobacco Use fields
- **Insurance Age Calculation**: Calculate age based DOB with nearest-birthday rounding
- **Rating Area Assignment**: Maps ZIP codes to rating areas(1-4) using prefix-based lookup
- **Tobacco Use Normalization**: Converts various input format (Y/yes/true/1) to boolean
- **Eligibility Check**: Flags employee under 18 or over 65 as "Ineligible"
- **Comprehensive Error Handling**: Clear JSON error response for malformed payloads
- **OpenAPI/Swagger Documentation**: Interactive API documentation included

## Tech Stack

- **Java 21**
- ** Spring Boot 4.0.3**
- **SpringDoc OpenAPI (Swagger UI)**
- **Maven**
- **Junit 5** (with parameterized Tests)

## Prerequisites

- Java 21 (JDK)
- Maven 4.0+

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/mansoorranjha786/census-flow.git
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

- **OpenAPI Specification**: [openapi.yaml](openapi.yaml)
- **Swagger UI**: Accessible at `http://localhost:8080/swagger-ui.html` when the application is running.

## Demonstration

### Valid Census Processing
Process a valid census with multiple employee records:
```bash
curl -X POST http://localhost:8080/api/v1/census/validate \
-H "Content-Type: application/json" \
-d '{
  "employees": [
    {
      "name": "Jane Smith",
      "dob": "1990-05-15",
      "zipCode": "90210",
      "tobaccoUse": "no"
    },
    {
      "name": "John Doe",
      "dob": "1985-06-15",
      "zipCode": "91302",
      "tobaccoUse": "Y"
    }
  ]
}'
```

### Invalid Payload (Validation Errors)
Processing a payload with missing fields or invalid formats:
```bash
curl -X POST http://localhost:8080/api/v1/census/validate \
-H "Content-Type: application/json" \
-d '{
  "employees": [
    {
      "name": "", 
      "dob": "invalid-date",
      "zipCode": "123",
      "tobaccoUse": "maybe"
    }
  ]
}'
```

### Ineligible Employee (Age-Based)
Flagging an employee as "Ineligible" due to age (e.g., under 18 or over 65):
```bash
curl -X POST http://localhost:8080/api/v1/census/validate \
-H "Content-Type: application/json" \
-d '{
  "employees": [
    {
      "name": "Young Person",
      "dob": "2015-01-01",
      "zipCode": "90210",
      "tobaccoUse": "no"
    }
  ]
}'
```