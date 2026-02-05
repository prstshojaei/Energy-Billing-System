# Energy Billing System

This project was developed as the **final project of the Visual Object Software course** at the **University of Northampton**.

The Energy Billing System is a **Java-based desktop application** designed to manage customers and calculate **electricity and gas bills** through a user-friendly **JavaFX graphical interface**. The system follows **object-oriented design principles** and uses file-based storage to persist data.

---

## Project Overview
The application allows users to:
- Log in to the system before accessing core features
- Manage customer information (add, view, delete)
- Calculate electricity and gas bills based on meter readings
- Save billing records and track payment status
- View, update, and delete existing bills

The system was implemented incrementally, starting from a **console-based application** and evolving into a complete **JavaFX GUI application**.

---

## Key Features
- JavaFX-based graphical user interface
- Customer management with table views
- Electricity and gas bill calculation
- CSV-based file storage (no database required)
- Bill payment status tracking (Paid / Unpaid)
- Modular and object-oriented architecture
- UML-based system design
- Blackbox testing and JUnit-based whitebox testing

---

## Technologies Used
- **Java**
- **JavaFX**
- **Object-Oriented Programming (OOP)**
- **FXML & Scene Builder**
- **CSV File Handling**
- **JUnit 5** (unit testing)
- **UML**

---

## Application Workflow
1. The system starts with a **login screen** to control access.
2. After successful login, the main layout and navigation menu are displayed.
3. Users can:
   - Add and manage customers
   - Calculate electricity and gas bills
   - Save bills to CSV files
   - View, mark as paid, or delete existing bills
4. All data is automatically loaded from and saved to CSV files.

---

## Testing
- **Blackbox testing** was performed through GUI interaction to validate system behavior.
- **Whitebox testing** was implemented using **JUnit** to verify the correctness of electricity and gas bill calculations with known inputs.
