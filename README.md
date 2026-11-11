# A1-YinghaoLiu

PROG2004 Object Oriented Programming - Assessment 1

A simple console application for managing current-day health appointments. The project models health professionals, patients, time slots and appointments. There is no graphical interface, menu or saved data.

## Main Classes

- `AssignmentOne` - runs the complete console demonstration.
- `HealthProfessional` - abstract base class for all health professionals.
- `GeneralPractitioner`, `Surgeon`, `Nurse`, `Dietitian` - specific professional types.
- `Patient` - stores patient name and mobile number.
- `TimeSlot` - represents an appointment time and supports sorting.
- `ClinicSchedule` - creates the fixed daily time slots.
- `Appointment` - links a patient, professional and time slot.
- `AppointmentManager` - adds, filters, sorts and cancels appointments.

## Features

- Display a mixed collection of health professional types using polymorphism.
- Add appointments for different professional types.
- Prevent double booking for the same professional and time.
- Sort appointments by time.
- Filter by professional ID or patient mobile number.
- Cancel by professional ID and time, or by patient mobile number.
- Return clear warning messages when an operation cannot be completed.

## Run

Open the project in VS Code, open `src/AssignmentOne.java` and click **Run**.

Or run from the project folder:

```powershell
javac -d bin src\*.java
java -cp bin AssignmentOne
```

## Demonstration Rules

- The simulation uses `MONDAY` as the current clinic day.
- Time slots run every 30 minutes from 08:00 to 16:00.
- The last appointment starts at 15:30.
- Mobile numbers must contain exactly 11 digits.
- Appointments are only valid for the current day.

## OOP Concepts

The design demonstrates abstraction through an abstract class, encapsulation through private fields and getters, inheritance through the professional hierarchy, polymorphism through shared base-type references, and composition through appointments and professional groups.
