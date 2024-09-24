BatiCuisine - Kitchen Construction Cost Estimation Application
Project Overview

BatiCuisine is a Java-based application designed for construction and kitchen renovation professionals. It provides an intuitive interface for estimating the total cost of a project, including material costs and labor fees. This application simplifies project management by handling client details, generating cost estimates, and offering a financial overview of renovation projects.
Features

    Project Management:
        Add and manage clients and materials.
        Associate cost estimates with each project.
        Track project status: In Progress, Completed, or Canceled.

    Component Management:
        Materials: Specify material costs, quantities, and associated transport costs.
        Labor: Calculate labor costs based on hourly rates and productivity.

    Client Management:
        Store client information (name, address, phone number).
        Differentiate between professional and individual clients for customized discounts.

    Quotation Generation:
        Generate estimates before starting the project, including materials, labor, and taxes.
        Indicate quotation acceptance by the client.

    Cost Calculation:
        Calculate total project cost by factoring in materials, labor, and taxes.
        Include profit margins to provide the final project cost.

    Detailed Summary and Results:
        Display project details, including client information, materials used, and final costs.
        Generate a detailed cost breakdown, including VAT and profit margins.

Technical Requirements

    Java 8 for core logic.
    PostgreSQL as the database.
    Utilizes design patterns such as Singleton and Repository.
    Data validation (dates, VAT, etc.).
    Layered architecture: Service, Repository, and Presentation.
    Git and JIRA for version control and project management.

Project Structure

    DOMAIN DRIVEN DESIGN WITH FOLLOWING LAYERS :
    Service Layer: Handles the business logic, including cost calculations and client management.
    Repository Layer: Manages database operations for storing and retrieving project, client, and component data.
    UI Layer: Command-line interface for interacting with the application.

Dependencies

    Java Time API for date handling.
    JDBC for PostgreSQL connection.
    Enum for managing project statuses and component types.
    Streams and Optionals for efficient data handling.

Usage Instructions

    Clone the Repository: Clone the project from the Git repository.
    Build the Application: Use the provided build.gradle or pom.xml file to compile the Java source code.
    Database Setup: Execute the SQL scripts provided to create and initialize the PostgreSQL database.
    Run the Application: Launch the JAR file to start the cost estimation process.

Example Commands

    Create a new project: Follow the interactive prompts to add a client, materials, and labor, then generate a detailed cost estimate.
    Generate a quotation: Calculate the total cost based on inputs and apply VAT and profit margins.
    Manage clients and projects: View or update existing projects and clients through the command-line interface.