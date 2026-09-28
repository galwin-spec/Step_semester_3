public class F1AttendanceSystem {
    static class SrmStudent {
        private final String name;
        private final String regNo;
        private int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        void addAttendanceUpdate(int newAttendance) {
            attendance = newAttendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        static double classAverage(SrmStudent[] students) {
            if (students == null || students.length == 0) {
                return 0.0;
            }

            int totalAttendance = 0;
            for (SrmStudent student : students) {
                totalAttendance += student.attendance;
            }
            return (double) totalAttendance / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
                new SrmStudent("Ravi", "RA231100301011", 82),
                new SrmStudent("Anitha", "RA231100301012", 68),
                new SrmStudent("Karthik", "RA231100301013", 91),
                new SrmStudent("Meera", "RA231100301014", 74),
                new SrmStudent("Suresh", "RA231100301015", 60)
        };

        for (SrmStudent student : students) {
            String status = student.isEligible() ? "Eligible" : "Detained";
            System.out.println(student.name + " - " + student.attendance + "% - " + status);
        }

        // This operates on a collection of students, so it belongs to the class;
        // isEligible() depends on one student's attendance, so it is an instance method.
        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}
