import java.util.ArrayList;

/**
 * Provides the fixed time slots used by the clinic each day.
 */
public class ClinicSchedule {
    private static final int START_MINUTES = 8 * 60;
    private static final int END_MINUTES = 16 * 60;
    private static final int SLOT_MINUTES = 30;
    private static final int MINUTES_PER_HOUR = 60;

    private ClinicSchedule() {
    }

    /**
     * Creates the clinic schedule from 08:00 until 16:00.
     * The last appointment starts at 15:30.
     */
    public static ArrayList<TimeSlot> createTimeSlots() {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        for (int minutes = START_MINUTES; minutes < END_MINUTES; minutes += SLOT_MINUTES) {
            int hour = minutes / MINUTES_PER_HOUR;
            int minute = minutes % MINUTES_PER_HOUR;
            slots.add(new TimeSlot(hour, minute));
        }
        return slots;
    }
}
