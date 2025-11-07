import java.util.Arrays;
import java.util.Objects;

public class Faculty extends Employee implements Comparable<Person>{
//    private static final int MAX_COURSES = 100;
    private final Course[] coursesTaught;
    private int numCoursesTaught;
    private boolean isTenured;

    public Faculty() {
        super();
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = false;
    }

    public Faculty(boolean isTenured) {
        super();
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured) {
        super(deptName);
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String name, int birthYear, String deptName, boolean isTenured) {
        super(name, birthYear, deptName);
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

    public void setTenured(boolean tenured) {
        isTenured = tenured;
    }

    public void addCourseTaught(Course course) {
        if (course == null) return;
        if (numCoursesTaught < 100) {
            coursesTaught[numCoursesTaught++] = course;
        }
    }

    public void addCoursesTaught(Course[] courseTaught) {
        if (courseTaught == null) return;
        for (Course c : courseTaught) {
            if (c == null) continue;
            if (numCoursesTaught >= 100) {
                courseTaught[numCoursesTaught++] = c;
            }
        }

    }

    public Course getCourseTaught(int index) {
        if (index < 0 || index >= numCoursesTaught) return null;
        return coursesTaught[index];
    }

    public String getCourseTaughtAsString(int index) {
        Course c = getCourseTaught(index);
        if (c == null) return "";
        return c.getCourseDept() + "-" + c.getCourseNum();
    }
    public String getAllCoursesTaughtAsString() {
        if (numCoursesTaught == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCoursesTaught; i++) {
            if (i > 0) sb.append(", ");
            sb.append(getCourseTaughtAsString(i));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Faculty)) return false;
        Faculty other = (Faculty) obj;

        if (!super.equals(other)) return false;
        if (this.isTenured != other.isTenured) return false;
        if (this.numCoursesTaught != other.numCoursesTaught) return false;

        for (int i = 0; i < this.numCoursesTaught; i++) {
            if (!Objects.equals(this.coursesTaught[i], other.coursesTaught[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String tenureStr = isTenured ? "Is Tenured" : "Not Tenured";
        return String.format(
                "%s Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s",
                super.toString(), tenureStr, numCoursesTaught, getAllCoursesTaughtAsString()
        );
    }

    @Override
    public int compareTo(Person p) {
        if (p instanceof Faculty) {
            Faculty f = (Faculty) p;
            return Integer.compare(this.numCoursesTaught, f.numCoursesTaught);
        }
        // If compared to a non-Faculty Person, use Employee's ordering
        return super.compareTo(p);
    }
}
