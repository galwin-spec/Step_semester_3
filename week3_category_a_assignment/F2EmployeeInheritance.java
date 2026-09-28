public class F2EmployeeInheritance {
    static class Employee {
        private final String empId;
        private final String empName;
        private final double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private final double teamBonus;

        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private final double stipendCap;

        InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static void main(String[] args) {
        Employee plain = new Employee("E001", "Karan", 40000);
        Employee manager = new ManagerEmployee("E002", "Divya", 70000, 8000);
        Employee intern = new InternEmployee("E003", "Meera", 12000, 10000);
        Employee[] employees = {plain, manager, intern};

        for (Employee employee : employees) {
            if (employee instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs "
                        + ((ManagerEmployee) employee).effectiveSalary());
            } else if (employee instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs "
                        + ((InternEmployee) employee).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + employee.getSalary());
            }
        }
    }
}
