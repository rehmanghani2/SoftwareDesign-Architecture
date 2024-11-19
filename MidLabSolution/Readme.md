# Design Overview
# 1. Observer Pattern
The system uses the Observer Pattern to decouple the AdmissionProcess (the Subject) from its dependent filters (the Observers). When the AdmissionProcess state changes, it notifies all registered filters (observers) to act accordingly. This ensures modularity and separation of concerns.

# 2. Layered Architecture
The system is divided into the following layers:

Presentation Layer: Handles user interactions (via the AdmissionSystemUI class).
Business Logic Layer: Contains the core logic, including the admission process and filter classes.
Data Layer (optional): Can be extended to integrate data persistence (e.g., saving application records).
Key Components
1. AdmissionProcess Class
This class represents the core of the admission process. It:

Acts as the Subject in the Observer Pattern.
Maintains a list of registered observers (filters).
Notifies all observers whenever the application process advances.
Tracks the current status of a student during the admission process.
2. Filters
Filters represent the different stages of the admission process. Each filter implements the Observer interface and performs specific operations:

EligibilityFilter: Validates if a student meets the admission criteria.
TestFilter: Simulates the student's test phase.
InterviewFilter: Conducts the interview phase.
MeritListFilter: Adds successful applicants to the merit list.
Each filter's update method is triggered when the AdmissionProcess notifies its observers.

3. Interfaces
Subject Interface: Defines the contract for managing observers:
Add an observer: void addObserver(Observer observer)
Remove an observer: void removeObserver(Observer observer)
Notify observers: void notifyObservers(String studentName)
Observer Interface: Defines the contract for filters:
React to updates from the subject: void update(String studentName, AdmissionProcess admissionProcess)
4. AdmissionService Class
Responsible for:

Registering all filters with the AdmissionProcess.
Initiating the admission process by passing the student's name.
5. AdmissionSystemUI Class
Acts as the entry point to the program, providing a user interface to start the admission process for a specific student.

# Workflow
Initialization:

An instance of AdmissionProcess is created.
Filters (EligibilityFilter, TestFilter, InterviewFilter, MeritListFilter) are registered as observers to the AdmissionProcess.
Processing a Student Application:

The user provides a student's name.
The AdmissionProcess begins the application process by notifying all filters sequentially.
Filter Execution:

Each filter performs its specific task (e.g., checking eligibility, conducting tests) and updates the AdmissionProcess status.
Final Status:

After all filters are executed, the system outputs the final status of the admission process.
Example Scenario
Input:
text
Copy code
Student Name: John Doe
Output:
text
Copy code
Starting admission process for John Doe
EligibilityFilter: Checking eligibility for John Doe
TestFilter: Conducting test for John Doe
InterviewFilter: Conducting interview for John Doe
MeritListFilter: Adding John Doe to the merit list.
Admission process completed for John Doe.
Features
Decoupled Design:

Filters and the admission process are independent, improving flexibility and maintainability.
Extensibility:

New filters can be added without modifying the core admission process.
Reusability:

Components like AdmissionProcess and filters can be reused in similar systems.
Layered Architecture:

Clear separation of the presentation, business, and data layers.
Real-Time Notifications:

Observers are immediately notified of state changes in the admission process.
