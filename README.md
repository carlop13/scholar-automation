# Server and Database Commands: Google Scholar Data Integrator

This repository contains the Java application developed for the "Server and Database Commands" challenge. The project's main goal is to automate the integration of academic data by fetching researcher information from the Google Scholar API and preparing it for persistence in a MySQL database.

The application is built following the **Model-View-Controller (MVC)** design pattern to ensure a clean separation of concerns and a maintainable codebase.

## 1. Project Purpose
The primary objective is to replace a time-consuming and error-prone manual data entry process with a reliable, automated Java application. This tool will provide the university's Innovation Center with timely and accurate data on its top researchers, extracted directly from Google Scholar.

## 2. Key Functionalities
*   **Data Extraction:** Connects to the Google Scholar API (via SerpApi) using Java's built-in `HttpClient` to perform `GET` requests.
*   **Data Parsing:** Parses the incoming JSON response from the API using the **Jackson** library.
*   **Data Mapping (MVC Model):** Maps the parsed JSON data into a structured set of Plain Old Java Objects (POJOs) located in the `model` package.
*   **Data Display (MVC View):** Presents the extracted data in a readable format in the system console.

## 3. Project Relevance
This project solves a critical operational bottleneck by automating data integration. This facilitates:
*   **Accuracy:** Eliminates human error in data transcription.
*   **Efficiency:** Frees up staff to focus on data analysis rather than manual data gathering.
*   **Timeliness:** Allows for on-demand retrieval of the latest academic metrics.

---

## 🛠️ Technologies and Libraries Used
*   **Language:** Java 17
*   **Build Tool:** Apache Maven
*   **HTTP Client:** `java.net.http.HttpClient` (Standard Java 11+ library)
*   **JSON Processing:** Jackson Databind
*   **Design Pattern:** Model-View-Controller (MVC)
*   **External API:** [SerpApi Google Scholar API](https://serpapi.com/google-scholar-api)

---

## 🚀 How to Run the Project

### Prerequisites
*   Java Development Kit (JDK) 11 or higher.
*   Apache Maven.
*   An active SerpApi account with a valid API Key.

### Steps to Run
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/carlop13/scholar-automation.git
    cd scholar-automation
    ```

2.  **Configure the API Key:**
    *   Open the project in your favorite IDE (e.g., IntelliJ IDEA).
    *   Navigate to the file: `src/main/java/org/example/Main.java`.
    *   Find the following line:
        ```java
        String apiKey = "YOUR_API_KEY_HERE"; 
        ```
    *   Replace `"YOUR_API_KEY_HERE"` with your actual private API Key from your SerpApi dashboard.

3.  **Build the project with Maven:**
    *   Open a terminal in the project's root directory.
    *   Run the following command to compile the code and download all dependencies:
        ```bash
        mvn clean install
        ```

4.  **Run the application from your IDE:**
    *   Navigate back to the `src/main/java/org/example/Main.java` file.
    *   Right-click on the file and select "Run 'Main.main()'".
    *   The application will execute, and the fetched data for the hardcoded author ("Alan Turing") will be displayed in the console.

---

## 📂 Project Structure (MVC)
The project's source code is organized into three main packages to adhere to the MVC design pattern:

*   **`org.example.model`**: Contains the POJO classes (`Publication`, `Author`, etc.) that represent the data structure. This is the **Model**.
*   **`org.example.view`**: Contains the `ConsoleView` class, which is responsible for displaying the data to the user. This is the **View**.
*   **`org.example.controller`**: Contains the `ScholarController` class, which holds the core application logic, including making API requests and coordinating between the model and the view. This is the **Controller**.
*   **`org.example`**: The root package, which contains the `Main.java` class that initializes and runs the application.