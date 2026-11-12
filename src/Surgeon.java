/**
 * A doctor who performs surgical procedures.
 */
public class Surgeon extends HealthProfessional {
    private final String specialty;
    private final String operatingRoom;

    public Surgeon(String id, String name, String[] availableDays,
            String specialty, String operatingRoom) {
        super(id, name, availableDays);
        // Store validated values that only a surgeon needs.
        this.specialty = requireText(specialty, "Specialty");
        this.operatingRoom = requireText(operatingRoom, "Operating room");
    }

    public Surgeon(String id, String name, String specialty, String operatingRoom) {
        // Use the standard Monday-to-Friday availability.
        this(id, name, defaultAvailableDays(), specialty, operatingRoom);
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getOperatingRoom() {
        return operatingRoom;
    }

    /**
     * Records a surgical procedure for this surgeon.
     */
    public String performSurgery(String procedure) {
        return getName() + " performed " + requireText(procedure, "Procedure") + ".";
    }

    @Override
    public String getRole() {
        return "Surgeon";
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + specialty
                + " | Operating room: " + operatingRoom;
    }
}
