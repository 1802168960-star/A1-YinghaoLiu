/**
 * A nurse who works in a hospital ward.
 */
public class Nurse extends HealthProfessional {
    private final String ward;
    private final String shift;

    public Nurse(String id, String name, String[] availableDays,
            String ward, String shift) {
        super(id, name, availableDays);
        this.ward = requireText(ward, "Ward");
        this.shift = requireText(shift, "Shift");
    }

    public Nurse(String id, String name, String ward, String shift) {
        this(id, name, defaultAvailableDays(), ward, shift);
    }

    public String getWard() {
        return ward;
    }

    public String getShift() {
        return shift;
    }

    /**
     * Records a blood sample collection for this nurse.
     */
    public String takeBloodSample(String patientName) {
        return getName() + " took a blood sample from "
                + requireText(patientName, "Patient name") + ".";
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return super.toString() + " | Ward: " + ward + " | Shift: " + shift;
    }
}
