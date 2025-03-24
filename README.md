# Category API Test Automation

This project contains automated tests for the Category API. It validates the API response against specific acceptance criteria using **JUnit 5** And **RestAssured**.

---

## **Acceptance Criteria**
The following criteria are validated by the tests:
1. The `Name` field in the API response should be **"Carbon credits"**.
2. The `CanRelist` field in the API response should be **true**.
3. The `Promotions` element with `Name = "Gallery"` should have a `Description` containing the text **"Good position in category"**.

---

## **Technologies Used**
- **Java**: The programming language used for writing the tests.
- **JUnit 5**: The testing framework for writing and running unit tests.
- **RestAssured**: A library for testing RESTful APIs.
- **Maven**: For dependency management and building the project.

---

## **Prerequisites**
Before running the tests, ensure you have the following installed:
- **Java 8 or higher**
- **Maven 3.x**

---

## **Setup**
1. Clone the repository:
   git clone [https://github.com/Nilakshi-R/category-api-test.git](https://github.com/Nilakshi-R/category-api-test.git)
2. cd category-api-test

## **Configuration**
Update the config.properties file in src/test/resources with the appropriate URL

## **Run the Tests**
- Run the tests using Maven:
  ```
  mvn test
  ```
- The test results will be displayed in the console. If all tests pass, you will see:
  ```
  [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
  ```
