public class F5HrParkingMiniSystem {
    static class Employee {
        private final double salary;

        Employee(double salary) {
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private final double teamBonus;

        ManagerEmployee(double salary, double teamBonus) {
            super(salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private final double stipendCap;

        InternEmployee(double salary, double stipendCap) {
            super(salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    static class ParkingSlot {
        private final String slotNo;
        private final int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity) {
            this.slotNo = slotNo;
            this.capacity = capacity;
        }

        boolean allot(String vehicleNo) {
            if (occupiedCount >= capacity) {
                return false;
            }
            occupiedCount++;
            return true;
        }
    }

    static class CompanyEmployeeRecord {
        private final String name;
        private final String empId;
        private final Employee employee;
        private final ParkingSlot slot;
        private static int totalRecords;

        CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;
            totalRecords++;
        }

        private double effectivePay() {
            if (employee instanceof ManagerEmployee) {
                return ((ManagerEmployee) employee).effectiveSalary();
            }
            if (employee instanceof InternEmployee) {
                return ((InternEmployee) employee).effectiveSalary();
            }
            return employee.getSalary();
        }

        String fullProfile() {
            String slotNumber = slot == null ? "no parking assigned" : slot.slotNo;
            return name + " | Pay: Rs " + effectivePay() + " | Slot: " + slotNumber;
        }
    }

    public static void main(String[] args) {
        ParkingSlot slotA1 = new ParkingSlot("A1", 1);
        ParkingSlot slotA2 = new ParkingSlot("A2", 1);
        slotA1.allot("TN09AB1234");
        slotA2.allot("TN10CD5678");

        CompanyEmployeeRecord divya = new CompanyEmployeeRecord(
                "Divya", "E001", new ManagerEmployee(70000, 8000), slotA1);
        CompanyEmployeeRecord karan = new CompanyEmployeeRecord(
                "Karan", "E002", new Employee(40000), slotA2);
        CompanyEmployeeRecord meera = new CompanyEmployeeRecord(
                "Meera", "E003", new InternEmployee(12000, 10000), null);

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
