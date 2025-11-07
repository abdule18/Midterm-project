import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// I worked with Rokia on this project
//Youtube Link: https://youtu.be/HJKGUriD-TA

public class Driver_SchoolDB {

    private static final List<Course> courses = new ArrayList<>();
    private static final List<Person> persons = new ArrayList<>();
    private static final List<Employee> employees = new ArrayList<>();
    private static final List<GeneralStaff> generalStaff = new ArrayList<>();
    private static final List<Faculty> faculty = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Path input = Paths.get("SchoolDB_Initial.txt");

        List<String> lines = readAll(input);

        for (String line : lines) System.out.println(line);
        System.out.println();

        parse(lines);

        printDatabase();
    }

    // ---------- IO ----------
    private static List<String> readAll(Path p) {
        try {
            return Files.readAllLines(p);
        } catch (IOException e) {
            System.out.println("Error: cannot read " + p.toAbsolutePath());
            // Don’t exit — show helpful info
            try {
                // List files in the working directory to help debug
                System.out.println("Files in CWD:");
                Files.list(Paths.get("")).forEach(fp -> System.out.println(" - " + fp));
            } catch (IOException ignored) {}
            return List.of(); // return empty so the program continues and prints the headers
        }
    }

    // ---------- Parsing ----------
    private static void parse(List<String> lines) {
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("Course:")) {
                String[] parts = splitCSV(line.substring("Course:".length()).trim());
                if (parts.length == 4) {
                    boolean grad = Boolean.parseBoolean(parts[0].trim());
                    int num = Integer.parseInt(parts[1].trim());
                    String dept = parts[2].trim();
                    int credits = Integer.parseInt(parts[3].trim());
                    courses.add(new Course(grad, num, dept, credits));
                }
            } else if (line.startsWith("Faculty:")) {
                // Faculty:
                // Faculty: true
                // Faculty: MAT,false
                // Faculty: Superman,1938,PHY,true
                Faculty f = parseFaculty(line.substring("Faculty:".length()).trim());
                if (f != null) {
                    faculty.add(f);
                    employees.add(f);
                    persons.add(f);
                }
            } else if (line.startsWith("Student:")) {
                // Student:
                // Student: false
                // Student: Math,false
                // Student: Wonderwoman,1941,JST,true
                Student s = parseStudent(line.substring("Student:".length()).trim());
                if (s != null) {
                    students.add(s);
                    persons.add(s);
                }
            } else if (line.startsWith("GeneralStaff:")) {
                // GeneralStaff:
                // GeneralStaff: advise students
                // GeneralStaff: Sanitation,clean
                // GeneralStaff: Flash Gordon,1934,Security,safety
                GeneralStaff g = parseGeneralStaff(line.substring("GeneralStaff:".length()).trim());
                if (g != null) {
                    generalStaff.add(g);
                    employees.add(g);
                    persons.add(g);
                }
            }
        }
    }

    private static Faculty parseFaculty(String data) {
        try {
            if (data.isEmpty()) return new Faculty();
            String[] p = splitCSV(data);
            if (p.length == 1) return new Faculty(Boolean.parseBoolean(p[0]));
            if (p.length == 2) return new Faculty(p[0], Boolean.parseBoolean(p[1]));
            if (p.length == 4) return new Faculty(p[0], Integer.parseInt(p[1]), p[2], Boolean.parseBoolean(p[3]));
        } catch (Exception ignored) {}
        return new Faculty();
    }

    private static Student parseStudent(String data) {
        try {
            if (data.isEmpty()) return new Student();
            String[] p = splitCSV(data);

            if (p.length == 1) {

                return new Student(Boolean.parseBoolean(p[0]));
            } else if (p.length == 2) {

                String major = p[0];
                boolean isGrad = Boolean.parseBoolean(p[1]);
                return new Student(major, isGrad);
            } else if (p.length == 4) {
                String name = p[0];
                int birthYear = Integer.parseInt(p[1]);
                String major = p[2];
                boolean isGrad = Boolean.parseBoolean(p[3]);

                return new Student(name, birthYear, major, isGrad);
            }
        } catch (Exception e) {
            System.err.println("Failed to parse Student: '" + data + "' -> " + e.getMessage());
        }
        return new Student();
    }


    private static GeneralStaff parseGeneralStaff(String data) {
        try {
            if (data.isEmpty()) return new GeneralStaff();
            String[] p = splitCSV(data);
            if (p.length == 1) return new GeneralStaff(p[0]);
            if (p.length == 2) return new GeneralStaff(p[0], p[1]);
            if (p.length == 4) return new GeneralStaff(p[0], Integer.parseInt(p[1]), p[2], p[3]);
        } catch (Exception ignored) {}
        return new GeneralStaff();
    }

    private static String[] splitCSV(String s) {
        // split by commas and trim spaces around values
        if (s.isEmpty()) return new String[0];
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

    private static void printDatabase() {
        System.out.println("**************************************************************");
        System.out.println("SCHOOL DATABASE INFO:\n");

        // COURSES
        System.out.println("************************************************");
        System.out.println("COURSES:");

        for (Course c : courses) {
            System.out.println(c);
        }
        System.out.println("************************************************");

        // PERSONS
        System.out.println("************************************************");
        System.out.println("PERSONS:");
        System.out.println("************************************************");

        // EMPLOYEES
        System.out.println("************************************************");
        System.out.println("EMPLOYEES:");
        System.out.println("************************************************");

        // GENERAL STAFF
        System.out.println("************************************************");
        System.out.println("GENERAL STAFF:");
        for (GeneralStaff g : generalStaff) {
            System.out.println(g);
        }
        System.out.println("************************************************");

        // FACULTY
        System.out.println("************************************************");
        System.out.println("FACULTY:");
        for (Faculty f : faculty) {
            System.out.println(f);
        }
        System.out.println("************************************************");

        // STUDENTS
        System.out.println("************************************************");
        System.out.println("STUDENTS:");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("************************************************");
        System.out.println("**************************************************************");
        System.out.println();
    }
}
