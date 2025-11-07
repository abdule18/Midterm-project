import java.util.Arrays;
import java.util.Objects;

public class Student extends Person implements Comparable<Person> {

//    private static final int MAX_COURSES = 50;
    private static int numStudents = 0;
    private int studentID;

    private Course[] coursesTaken;
    private int  numCoursesTaken;

    private boolean isGraduate;
    private String major;

    public Student() {
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        this.isGraduate = false;
        this.major = "undeclared";
        numStudents++;
        this.studentID = numStudents;
    }

    public Student(boolean isGraduate) {
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = "undeclared";
        numStudents++;
        this.studentID = numStudents;
    }

    public Student(String major, boolean isGraduate) {
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = (major == null ? "undeclared" : major);
        numStudents++;
        this.studentID = numStudents;
    }

    public Student(String name, int birthYear, String major, boolean isGraduate) {
        super(name, birthYear);
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = (major == null ? "undeclared" : major);
        numStudents++;
        this.studentID = numStudents;
    }

    public int getNumCoursesTaken() {
        return numCoursesTaken;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public String getMajor() {
        return major;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setGraduate(boolean graduate) {
        isGraduate = graduate;
    }

    public void setMajor(String major) {
        this.major = (major  == null ? "undeclared" : major);
    }

    public void addCourseTaken(Course course) {
        if (numCoursesTaken < coursesTaken.length) {
            coursesTaken[numCoursesTaken++] = course;
        }
    }

    public void addCoursesTaken(Course[] coursesTaken) {
        for (Course c :  coursesTaken) {
            addCourseTaken(c);
        }
    }

    public Course getCourseTaken(int index) {
        if (index >= 0 && index < coursesTaken.length) {
            return coursesTaken[index];
        }
        return null;
    }

    public String getCourseTakenAsString(int index) {
        if (index >= 0 && index < coursesTaken.length) {
            return coursesTaken[index].getCourseDept() + "-" +  coursesTaken[index].getCourseNum();
        }
        return "";
    }

    public String getAllCoursesTakenAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCoursesTaken; i++) {
            sb.append(getCourseTakenAsString(i) + "\n");
            if (i != numCoursesTaken - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private int getTotalCredits() {
        int sum = 0;
        for (int i = 0; i < numCoursesTaken; i++) {
            Course c = coursesTaken[i];
            if (c != null) sum += c.getNumCredits();
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;

        if (!super.equals(other)) return false;
        if (this.studentID != other.studentID) return false;
        if (this.isGraduate != other.isGraduate) return false;
        if (this.numCoursesTaken != other.numCoursesTaken) return false;
        if (!Objects.equals(this.major, other.major)) return false;

        for (int i = 0; i < this.numCoursesTaken; i++) {
            if (!Objects.equals(this.coursesTaken[i], other.coursesTaken[i])) return false;
        }
        return true;
    }


    @Override
    public String toString() {
        String level = isGraduate ? "Graduate" : "Undergraduate";
        return String.format(
                "%s Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s",
                super.toString(), studentID, major, level, numCoursesTaken, getAllCoursesTakenAsString()
        );
    }

    @Override
    public int compareTo(Person p) {
        if (p instanceof Student) {
            Student s = (Student) p;
            return Integer.compare(this.getTotalCredits(), s.getTotalCredits());
        }
        return this.getName().compareTo(p.getName());
    }
}
