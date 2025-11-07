import java.util.Objects;

public class Employee extends Person implements Comparable<Person> {
    private String deptName;
    private static int numEmployees;
    private int employeeID;

    public Employee() {
        super();
        deptName = "";
        employeeID = ++numEmployees;
    }

    public Employee(String deptName) {
        super();
        this.deptName = deptName;;
        employeeID = ++numEmployees;
    }

    public Employee(String name, int birthYear, String deptName) {
        super(name, birthYear);
        this.deptName = deptName;
        employeeID = ++numEmployees;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public static int getNumEmployees() {
        return numEmployees;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID && Objects.equals(deptName, employee.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deptName, employeeID);
    }

    @Override
    public String toString() {

        return String.format(
                "%s Employee: Department: %20s | Employee Number: %3d",
                super.toString(), deptName, employeeID
        );
    }


    @Override
    public int compareTo(Person p) {

        if(getClass() == p.getClass()) {
            Employee other = (Employee) p;

            if(this.employeeID == other.employeeID) {
                return 0;
            } else if(this.employeeID < other.employeeID) {
                return -1;
            }else {
                return 1;
            }
        } else {
            return super.compareTo(p);
        }
    }
}
