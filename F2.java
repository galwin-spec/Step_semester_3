class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class F2 {
    public static void main(String[] args) {

        Employee e1 = new Employee(101, "John", 40000);
        Employee e2 = new ManagerEmployee(102, "David", 70000, 8000);
        Employee e3 = new InternEmployee(103, "Alex", 12000, 10000);

        Employee[] employees = {e1, e2, e3};

        for (Employee e : employees) {

            if (e instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) e;
                System.out.println("Manager effective pay: Rs " + m.effectiveSalary());

            } else if (e instanceof InternEmployee) {
                InternEmployee i = (InternEmployee) e;
                System.out.println("Intern effective pay: Rs " + i.effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs " + e.getSalary());
            }
        }
    }
}