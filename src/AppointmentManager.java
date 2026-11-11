import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages appointments for one clinic day.
 */
public class AppointmentManager {
    private final String currentDay;
    private final ArrayList<TimeSlot> timeSlots;
    private final ArrayList<Appointment> appointments;

    public AppointmentManager(String currentDay) {
        if (currentDay == null || currentDay.trim().isEmpty()) {
            throw new IllegalArgumentException("Current day cannot be empty.");
        }

        this.currentDay = currentDay.trim().toUpperCase();
        this.timeSlots = ClinicSchedule.createTimeSlots();
        this.appointments = new ArrayList<>();
    }

    /**
     * Adds an appointment after checking the slot, availability and double booking.
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "WARNING: Appointment cannot be null.";
        }

        TimeSlot timeSlot = appointment.getTimeSlot();
        HealthProfessional professional = appointment.getHealthProfessional();

        if (!timeSlots.contains(timeSlot)) {
            return "WARNING: " + timeSlot + " is not a valid clinic time slot.";
        }
        if (!professional.isAvailableOn(currentDay)) {
            return "WARNING: " + professional.getName()
                    + " is not available on " + currentDay + ".";
        }

        for (Appointment existing : appointments) {
            boolean sameProfessional = existing.getHealthProfessional().equals(professional);
            boolean sameTime = existing.getTimeSlot().equals(timeSlot);
            if (sameProfessional && sameTime) {
                return "WARNING: " + professional.getName()
                        + " is already booked at " + timeSlot + ".";
            }
        }

        appointments.add(appointment);
        return "SUCCESS: Booked " + appointment.getPatient().getName()
                + " with " + professional.getName() + " at " + timeSlot + ".";
    }

    /**
     * Returns all appointments sorted by time.
     */
    public String getAllAppointments() {
        if (appointments.isEmpty()) {
            return "WARNING: No appointments have been booked.";
        }
        return formatAppointments(
                "ALL APPOINTMENTS (sorted by time)",
                new ArrayList<>(appointments));
    }

    /**
     * Returns appointments for one professional, sorted by time.
     */
    public String getAppointmentsByProfessionalId(String professionalId) {
        if (professionalId == null || professionalId.trim().isEmpty()) {
            return "WARNING: Professional ID cannot be empty.";
        }

        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getHealthProfessional().getId()
                    .equals(professionalId.trim())) {
                result.add(appointment);
            }
        }

        if (result.isEmpty()) {
            return "WARNING: No appointments found for professional ID "
                    + professionalId.trim() + ".";
        }
        return formatAppointments(
                "APPOINTMENTS FOR PROFESSIONAL " + professionalId.trim(),
                result);
    }

    /**
     * Returns appointments for one patient phone number, sorted by time.
     */
    public String getAppointmentsByPatientPhone(String patientPhone) {
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            return "WARNING: Patient phone number cannot be empty.";
        }

        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobileNumber()
                    .equals(patientPhone.trim())) {
                result.add(appointment);
            }
        }

        if (result.isEmpty()) {
            return "WARNING: No appointments found for phone number "
                    + patientPhone.trim() + ".";
        }
        return formatAppointments(
                "APPOINTMENTS FOR PATIENT " + patientPhone.trim(),
                result);
    }

    /**
     * Cancels an appointment using a professional ID and time slot.
     */
    public String cancelAppointmentByProfessional(String professionalId,
            TimeSlot timeSlot) {
        if (professionalId == null || professionalId.trim().isEmpty()
                || timeSlot == null) {
            return "WARNING: Professional ID and time slot are required.";
        }

        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            boolean sameProfessional = appointment.getHealthProfessional().getId()
                    .equals(professionalId.trim());
            if (sameProfessional && appointment.getTimeSlot().equals(timeSlot)) {
                appointments.remove(i);
                return "SUCCESS: Cancelled appointment for "
                        + appointment.getPatient().getName() + " with "
                        + appointment.getHealthProfessional().getName()
                        + " at " + timeSlot + ".";
            }
        }

        return "WARNING: No appointment found for professional ID "
                + professionalId.trim() + " at " + timeSlot + ".";
    }

    /**
     * Cancels the first appointment found for a patient phone number.
     */
    public String cancelAppointmentByPatientPhone(String patientPhone) {
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            return "WARNING: Patient phone number cannot be empty.";
        }

        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getPatient().getMobileNumber()
                    .equals(patientPhone.trim())) {
                appointments.remove(i);
                return "SUCCESS: Cancelled appointment for "
                        + appointment.getPatient().getName() + " at "
                        + appointment.getTimeSlot() + ".";
            }
        }

        return "WARNING: No appointment found for phone number "
                + patientPhone.trim() + ".";
    }

    private String formatAppointments(String title,
            ArrayList<Appointment> appointmentList) {
        Collections.sort(appointmentList);

        String output = title + "\n";
        for (int i = 0; i < appointmentList.size(); i++) {
            output += (i + 1) + ". " + appointmentList.get(i) + "\n";
        }
        return output;
    }
}
