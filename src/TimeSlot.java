import java.util.Objects;

/**
 * Represents one half-hour appointment time.
 */
public class TimeSlot implements Comparable<TimeSlot> {
    private final int hour;
    private final int minute;

    public TimeSlot(int hour, int minute) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23.");
        }
        if (minute != 0 && minute != 30) {
            throw new IllegalArgumentException("Minute must be 0 or 30.");
        }

        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    private int getMinuteOfDay() {
        return hour * 60 + minute;
    }

    @Override
    public int compareTo(TimeSlot other) {
        if (getMinuteOfDay() < other.getMinuteOfDay()) {
            return -1;
        }
        if (getMinuteOfDay() > other.getMinuteOfDay()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        TimeSlot slot = (TimeSlot) other;
        return hour == slot.hour && minute == slot.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
