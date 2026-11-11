/**
 * Stores the patient details required for an appointment.
 */
public class Patient {
    private final String name;
    private final String mobileNumber;

    public Patient(String name, String mobileNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (mobileNumber == null || mobileNumber.length() != 11) {
            throw new IllegalArgumentException(
                    "Mobile number must contain exactly 11 digits.");
        }

        for (int i = 0; i < mobileNumber.length(); i++) {
            char character = mobileNumber.charAt(i);
            if (character < '0' || character > '9') {
                throw new IllegalArgumentException("Mobile number must contain numbers only.");
            }
        }

        this.name = name.trim();
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " | Mobile: " + mobileNumber;
    }
}


