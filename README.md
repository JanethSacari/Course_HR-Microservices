# HR Microservices

Welcome to the HR Microservices repository! This project contains microservices designed for managing HR-related tasks in a course environment.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Contact](#contact)

## Overview

This project is a set of microservices aimed at handling various HR functions within a course management system. The microservices are designed to be scalable, maintainable, and easily deployable.

## Architecture

The architecture of this project follows a microservices model, where each service is responsible for a specific HR function. The services communicate with each other via REST APIs or messaging queues.

## Installation

To get started with the development or deployment of these microservices, follow the steps below:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/JanethSacari/Course_HR-Microservices.git
    cd Course_HR-Microservices
    ```

2. **Install dependencies:**
    Each microservice has its own set of dependencies. Navigate to the microservice directory and install them using the appropriate package manager (e.g., npm, pip, etc.).

    ```sh
    cd <microservice-directory>
    npm install # or pip install -r requirements.txt
    ```

3. **Set up environment variables:**
    Create a `.env` file in each microservice directory with the required environment variables. Refer to the `.env.example` file for the required variables.

4. **Run the microservices:**
    Start each microservice using the appropriate command.

    ```sh
    npm start # or python app.py
    ```

## Usage

Once the microservices are up and running, you can interact with them through their respective APIs. Documentation for each microservice's API can be found in their respective directories.

## Microservices

The following microservices are part of this project:

1. **User Service:** Manages user information and authentication.
2. **Course Service:** Handles course creation and management.
3. **Attendance Service:** Tracks attendance for courses.
4. **Grade Service:** Manages grading and feedback for students.
5. **Notification Service:** Sends notifications to users.

## Contact

For any questions or inquiries, please contact [Janeth Sacari](https://github.com/JanethSacari).