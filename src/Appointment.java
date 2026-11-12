/**
 * Links a patient to a professional at a specific time.
 */
public class Appointment implements Comparable<Appointment> {
    private final Patient patient;
    private final HealthProfessional healthProfessional;
    private final TimeSlot timeSlot;

    public Appointment(Patient patient, HealthProfessional healthProfessional,
            TimeSlot timeSlot) {
        // Reject incomplete appointments before storing any values.
        if (patient == null || healthProfessional == null || timeSlot == null) {
            throw new IllegalArgumentException("Appointment details cannot be empty.");
        }

        this.patient = patient;
        this.healthProfessional = healthProfessional;
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHealthProfessional() {
        return healthProfessional;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public int compareTo(Appointment other) {
        // Time is the only field used for appointment ordering.
        return timeSlot.compareTo(other.timeSlot);
    }

    @Override
    public String toString() {
        return "Time: " + timeSlot + "\n  " + patient
                + "\n  Professional: " + healthProfessional;
    }
}
