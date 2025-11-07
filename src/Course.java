import java.util.Objects;

public class Course implements Comparable<Course> {
    private boolean isGraduateCourse;
    private int courseNum;
    private String courseDept;
    private int numCredits;

    //private Course[] courses;

    public Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits) {
        this.isGraduateCourse = isGraduateCourse;
        this.courseNum = courseNum;
        this.courseDept = courseDept;
        this.numCredits = numCredits;
    }

    public boolean isGraduateCourse() {
        return isGraduateCourse;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public String getCourseName() {
        String level = isGraduateCourse ? "G" : "U";
        return level + courseDept + courseNum;
//        String level;
//        if(isGraduateCourse) {
//            level = "G";
//            isGraduateCourse = false;
//        } else {
//            level = "U";
//            isGraduateCourse = true;
//        }
//        return level + courseDept + courseNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return isGraduateCourse == course.isGraduateCourse && courseNum == course.courseNum && numCredits == course.numCredits && Objects.equals(courseDept, course.courseDept);
    }

    @Override
    public String toString() {
        String level = isGraduateCourse ? "Graduate" : "Undergraduate";
        return String.format(
                "Course: %3s-%3d | Number of Credits: %02d | %s",
                this.courseDept, this.courseNum, this.numCredits, level
        );
    }


    @Override
    public int compareTo(Course c) {
        return Integer.compare(this.courseNum, c.courseNum);
    }
}
