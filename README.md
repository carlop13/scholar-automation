# Server and Database Commands: Google Scholar Data Integrator

This repository contains the final Java application developed for the "Server and Database Commands" challenge. The project's main goal is to fully automate the integration of academic data by fetching researcher information from the Google Scholar API and persisting it into a MySQL database.

The application is built following the **Model-View-Controller (MVC)** design pattern, with a dedicated persistence layer, to ensure a clean separation of concerns and a robust, maintainable codebase.

## 1. Project Purpose
The primary objective is to replace a time-consuming and error-prone manual data entry process with a reliable, automated Java application. This tool provides the university's Innovation Center with timely and accurate data on its top researchers, extracted directly from Google Scholar and stored in a structured database.

## 2. Key Functionalities
The application performs a complete Extract, Transform, Load (ETL) workflow:

*   **Data Extraction:** Connects to the Google Scholar API (via SerpApi) using Java's built-in `HttpClient` to perform `GET` requests.
*   **Data Parsing & Mapping (MVC Model):** Parses the incoming JSON response using the **Jackson** library and maps it into a structured set of Plain Old Java Objects (POJOs).
*   **Data Display (MVC View):** Presents the extracted data in a readable format in the system console for real-time verification.
*   **Data Persistence:** Connects to a MySQL database using JDBC and inserts the processed data into a dedicated `articles` table.

## 3. Project Relevance
This project solves a critical operational bottleneck by automating data integration, which facilitates:
*   **Accuracy:** Eliminates human error in data transcription.
*   **Efficiency:** Frees up staff to focus on data analysis rather than manual data gathering.
*   **Centralization:** Creates a single source of truth for researcher data in a structured SQL database.

---

## 🛠️ Technologies and Libraries Used
*   **Language:** Java 17
*   **Build Tool:** Apache Maven
*   **Database:** MySQL Server
*   **API Client:** `java.net.http.HttpClient` (Standard Java 11+ library)
*   **Database Connector:** MySQL Connector/J (JDBC Driver)
*   **JSON Processing:** Jackson Databind
*   **Design Pattern:** Model-View-Controller (MVC) with a Persistence Layer
*   **External API:** [SerpApi Google Scholar API](https://serpapi.com/google-scholar-api)

---

## 🚀 How to Run the Project

### Prerequisites
*   Java Development Kit (JDK) 11 or higher.
*   Apache Maven.
*   An active SerpApi account with a valid API Key.
*   A running instance of MySQL Server.

### Steps to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/carlop13/scholar-automation.git
    cd scholar-automation
    ```

2.  **Set up the Database:**
    *   Connect to your local MySQL instance.
    *   Execute the following SQL script to create the database and the required table:
        ```sql
        CREATE DATABASE IF NOT EXISTS scholar_db;
        USE scholar_db;
        CREATE TABLE IF NOT EXISTS articles (
            id INT AUTO_INCREMENT PRIMARY KEY,
            researcher_name VARCHAR(255) NOT NULL,
            title TEXT NOT NULL,
            authors TEXT,
            publication_info TEXT,
            abstract_summary TEXT,
            cited_by INT,
            source_api VARCHAR(255) DEFAULT 'Google Scholar',
            registration_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
        );
        ```

3.  **Configure Application Secrets:**
    *   Open the project in your favorite IDE (e.g., IntelliJ IDEA).
    *   Navigate to the file: `src/main/java/org/example/persistence/DatabaseManager.java`.
    *   Update the database credentials (`DB_URL`, `DB_USER`, `DB_PASSWORD`) to match your local MySQL setup.
    *   Navigate to the file: `src/main/java/org/example/App.java`.
    *   Replace `"YOUR_API_KEY_HERE"` with your actual private API Key from your SerpApi dashboard.

4.  **Build the project with Maven:**
    *   Open a terminal in the project's root directory.
    *   Run the following command to compile the code and download all dependencies:
        ```bash
        mvn clean install
        ```

5.  **Run the application:**
    *   Navigate back to the `src/main/java/org/example/App.java` file.
    *   Right-click and select "Run 'App.main()'".
    *   The application will execute, display the fetched data in the console, and print confirmation messages for each database insertion.

6.  **Verify the results:**
    *   After the program finishes, connect to your MySQL database again.
    *   Run `SELECT * FROM scholar_db.articles;` to see the newly inserted records.

---

## 📂 Project Structure
The project's source code is organized to follow best practices and a clear separation of concerns:

*   **`org.example.model`**: Contains the POJO classes that represent the data structure (e.g., `Publication`). This is the **Model**.
*   **`org.example.view`**: Contains the `ConsoleView` class, responsible for displaying data to the user. This is the **View**.
*   **`org.example.controller`**: Contains the `ScholarController` class, which holds the core application logic and coordinates between the other components. This is the **Controller**.
*   **`org.example.persistence`**: Contains the `DatabaseManager` class, which handles all database-related operations (connection and insertion). This is the **Persistence Layer**.
*   **`org.example`**: The root package, containing the `App.java` class that initializes and runs the application.