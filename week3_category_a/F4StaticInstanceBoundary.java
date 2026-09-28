public class F4StaticInstanceBoundary {
    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }
    }

    static class SrmStudent {
        private final String name;
        private final String regNo;
        private final int attendance;
        private static final String university = "SRM";
        private static int admissionCount;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = String.format("RA2311003010%02d", admissionCount + 10);
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo + " | " + university + " | Attendance: " + attendance + "%");
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        BrokenSrmStudent raviBroken = new BrokenSrmStudent("Ravi", "RA001", 82);
        BrokenSrmStudent meeraBroken = new BrokenSrmStudent("Meera", "RA002", 74);
        System.out.println("Broken version: " + raviBroken.name + ", " + meeraBroken.name
                + " (Ravi's data was overwritten)");

        // name, regNo, and attendance describe an individual student, so static would make
        // every object share one value. university and admissionCount are common to all students.
        SrmStudent ravi = new SrmStudent("Ravi", 82);
        SrmStudent meera = new SrmStudent("Meera", 74);
        ravi.printIdCard();
        meera.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}
