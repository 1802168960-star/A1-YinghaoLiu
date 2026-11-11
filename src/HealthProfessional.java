import java.util.Arrays;
import java.util.Objects;

/**
 * Common details shared by every health professional.
 */
public abstract class HealthProfessional {
    private static final String[] DEFAULT_AVAILABLE_DAYS = {
        "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"
    };
    private static final String[] VALID_DAYS = {
        "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
        "SATURDAY", "SUNDAY"
    };

    private final String id;
    private final String name;
    private final String[] availableDays;

    /**
     * Creates a professional with a custom set of working days.
     */
    protected HealthProfessional(String id, String name, String[] availableDays) {
        validateId(id);
        this.id = id;
        this.name = requireText(name, "Name");
        this.availableDays = normalizeDays(availableDays);
    }

    /**
     * Creates a professional who works Monday to Friday by default.
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, defaultAvailableDays());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy so callers cannot change the stored working days.
     */
    public String[] getAvailableDays() {
        return Arrays.copyOf(availableDays, availableDays.length);
    }

    /**
     * Checks whether this professional works on the supplied day.
     */
    public boolean isAvailableOn(String day) {
        if (day == null) {
            return false;
        }

        for (String availableDay : availableDays) {
            if (availableDay.equalsIgnoreCase(day.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the type of professional for display and polymorphism.
     */
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + " | ID: " + id + " | Name: " + name
                + " | Available: " + Arrays.toString(availableDays);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        HealthProfessional professional = (HealthProfessional) other;
        return id.equals(professional.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private static void validateId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }

        // IDs are stored as text but may only contain decimal digits.
        for (int i = 0; i < id.length(); i++) {
            char character = id.charAt(i);
            if (character < '0' || character > '9') {
                throw new IllegalArgumentException("ID must contain numbers only.");
            }
        }
    }

    /**
     * Returns a fresh copy of the default Monday-to-Friday schedule.
     */
    protected static String[] defaultAvailableDays() {
        return Arrays.copyOf(DEFAULT_AVAILABLE_DAYS, DEFAULT_AVAILABLE_DAYS.length);
    }

    /**
     * Returns trimmed text or rejects an empty value.
     */
    protected static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    private static String[] normalizeDays(String[] days) {
        if (days == null || days.length == 0) {
            throw new IllegalArgumentException("At least one working day is required.");
        }

        String[] result = new String[days.length];
        for (int i = 0; i < days.length; i++) {
            if (days[i] == null || days[i].trim().isEmpty()) {
                throw new IllegalArgumentException("Working days cannot be empty.");
            }

            String day = days[i].trim().toUpperCase();
            if (!isValidDay(day)) {
                throw new IllegalArgumentException("Invalid working day: " + days[i]);
            }

            for (int j = 0; j < i; j++) {
                if (result[j].equals(day)) {
                    throw new IllegalArgumentException("Working days cannot be repeated.");
                }
            }
            result[i] = day;
        }
        return result;
    }

    private static boolean isValidDay(String day) {
        for (String validDay : VALID_DAYS) {
            if (validDay.equals(day)) {
                return true;
            }
        }
        return false;
    }
}

