import java.util.ArrayList;

/**
 * Runs the console demonstration for Assessment 1.
 */
public class AssignmentOne {
    private static final String CURRENT_DAY = "MONDAY";

    public static void main(String[] args) {
        // The simulation treats Monday as the current clinic day.
        GeneralPractitioner gp1 = new GeneralPractitioner(
                "1001", "Dr. Alice Chen",
                new String[] { "MONDAY", "WEDNESDAY", "FRIDAY" },
                "2A", "Family Medicine");
        GeneralPractitioner gp2 = new GeneralPractitioner(
                "1002", "Dr. Ben Patel",
                new String[] { "MONDAY", "TUESDAY", "THURSDAY" },
                "2B", "Chronic Disease");
        GeneralPractitioner gp3 = new GeneralPractitioner(
                "1003", "Dr. Chloe Nguyen", "3A", "Women's Health");
        Surgeon surgeon = new Surgeon(
                "2001", "Dr. Daniel Kim",
                new String[] { "MONDAY", "THURSDAY" },
                "Orthopaedics", "Theatre 1");
        Nurse nurse = new Nurse(
                "3001", "Eva Singh",
                new String[] { "MONDAY", "TUESDAY", "FRIDAY" },
                "Ward 3", "DAY");
        Dietitian dietitian = new Dietitian(
                "4001", "Farah Ali",
                new String[] { "MONDAY", "WEDNESDAY" },
                "Diabetes Nutrition", "Room 5");

        ArrayList<HealthProfessional> professionals = new ArrayList<>();
        professionals.add(gp1);
        professionals.add(gp2);
        professionals.add(gp3);
        professionals.add(surgeon);
        professionals.add(nurse);
        professionals.add(dietitian);

        printHeader("PART 3: HEALTH PROFESSIONAL OBJECTS");
        System.out.println("Current clinic day: " + CURRENT_DAY);
        System.out.println();
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
        }

        System.out.println();
        System.out.println(gp1.prescribe("Amoxicillin"));
        System.out.println(surgeon.performSurgery("Appendectomy"));
        System.out.println(nurse.takeBloodSample("Alex Morgan"));
        System.out.println(dietitian.createMealPlan("Jamie Lee"));

        Patient alex = new Patient("Alex Morgan", "13800138001");
        Patient jamie = new Patient("Jamie Lee", "13800138002");
        Patient taylor = new Patient("Taylor Brown", "13800138003");
        Patient sam = new Patient("Sam Wilson", "13800138004");
        Patient riley = new Patient("Riley Davis", "13800138005");

        AppointmentManager manager = new AppointmentManager(CURRENT_DAY);

        printHeader("NORMAL APPOINTMENT BOOKINGS");
        System.out.println(manager.addAppointment(
                new Appointment(alex, gp1, new TimeSlot(9, 0))));
        System.out.println(manager.addAppointment(
                new Appointment(jamie, surgeon, new TimeSlot(9, 0))));
        System.out.println(manager.addAppointment(
                new Appointment(taylor, nurse, new TimeSlot(10, 30))));
        System.out.println(manager.addAppointment(
                new Appointment(sam, dietitian, new TimeSlot(11, 0))));
        System.out.println(manager.addAppointment(
                new Appointment(riley, gp1, new TimeSlot(14, 0))));
        System.out.println(manager.addAppointment(
                new Appointment(alex, gp2, new TimeSlot(15, 30))));

        printHeader("ERROR: DOUBLE BOOKING");
        System.out.println(manager.addAppointment(
                new Appointment(sam, gp1, new TimeSlot(9, 0))));

        printHeader("ERROR: INVALID TIME SLOT");
        System.out.println(manager.addAppointment(
                new Appointment(taylor, gp2, new TimeSlot(16, 0))));

        printHeader("ALL APPOINTMENTS (SORTED)");
        System.out.println(manager.getAllAppointments());

        printHeader("FILTER: APPOINTMENTS BY PROFESSIONAL");
        System.out.println(manager.getAppointmentsByProfessionalId(gp1.getId()));

        printHeader("FILTER: APPOINTMENTS BY PATIENT PHONE");
        System.out.println(manager.getAppointmentsByPatientPhone(
                jamie.getMobileNumber()));

        printHeader("CANCELLATION: PROFESSIONAL AND TIME");
        System.out.println(manager.cancelAppointmentByProfessional(
                gp1.getId(), new TimeSlot(9, 0)));

        printHeader("CANCELLATION: PATIENT PHONE");
        System.out.println(manager.cancelAppointmentByPatientPhone(
                jamie.getMobileNumber()));

        printHeader("ERROR: CANCELLING MISSING APPOINTMENTS");
        System.out.println(manager.cancelAppointmentByProfessional(
                gp1.getId(), new TimeSlot(9, 0)));
        System.out.println(manager.cancelAppointmentByPatientPhone("13999999999"));

        printHeader("APPOINTMENTS AFTER CANCELLATION");
        System.out.println(manager.getAllAppointments());

        System.out.println();
        System.out.println("Assessment 1 demonstration complete.");
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("==============================================");
        System.out.println(title);
        System.out.println("==============================================");
    }
}

