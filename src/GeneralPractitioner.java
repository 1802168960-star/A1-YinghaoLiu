/**
 * A doctor who provides general medical care.
 */
public class GeneralPractitioner extends HealthProfessional {
    private final String clinicRoom;
    private final String specialInterest;

    public GeneralPractitioner(String id, String name, String[] availableDays,
            String clinicRoom, String specialInterest) {
        super(id, name, availableDays);
        this.clinicRoom = requireText(clinicRoom, "Clinic room");
        this.specialInterest = requireText(specialInterest, "Special interest");
    }

    public GeneralPractitioner(String id, String name, String clinicRoom,
            String specialInterest) {
        this(id, name, defaultAvailableDays(), clinicRoom, specialInterest);
    }

    public String getClinicRoom() {
        return clinicRoom;
    }

    public String getSpecialInterest() {
        return specialInterest;
    }

    /**
     * Records a prescription for this doctor.
     */
    public String prescribe(String medication) {
        return getName() + " prescribed " + requireText(medication, "Medication") + ".";
    }

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    @Override
    public String toString() {
        return super.toString() + " | Room: " + clinicRoom
                + " | Special interest: " + specialInterest;
    }
}
