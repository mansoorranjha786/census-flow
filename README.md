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

```bash
git clone https://github.com/mansoorranjha786/census-flow.git