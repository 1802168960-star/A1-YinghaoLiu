/**
 * A health professional who provides dietary advice.
 */
public class Dietitian extends HealthProfessional {
    private final String dietaryFocus;
    private final String clinicRoom;

    public Dietitian(String id, String name, String[] availableDays,
            String dietaryFocus, String clinicRoom) {
        super(id, name, availableDays);
        this.dietaryFocus = requireText(dietaryFocus, "Dietary focus");
        this.clinicRoom = requireText(clinicRoom, "Clinic room");
    }

    public Dietitian(String id, String name, String dietaryFocus, String clinicRoom) {
        this(id, name, defaultAvailableDays(), dietaryFocus, clinicRoom);
    }

    public String getDietaryFocus() {
        return dietaryFocus;
    }

    public String getClinicRoom() {
        return clinicRoom;
    }

    /**
     * Creates a meal plan for a patient.
     */
    public String createMealPlan(String patientName) {
        return getName() + " created a meal plan for "
                + requireText(patientName, "Patient name") + ".";
    }

    @Override
    public String getRole() {
        return "Dietitian";
    }

    @Override
    public String toString() {
        return super.toString() + " | Dietary focus: " + dietaryFocus
                + " | Room: " + clinicRoom;
    }
}
