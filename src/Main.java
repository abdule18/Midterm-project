//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
////    public static void main(String[] args) {
////
////        Course cr = new Course(true, 45, "Java", 35);
////        Employee e1 = new Employee("Alice", 1990, "Engineering");
////        Employee e2 = new Employee("Bob", 1985, "Finance");
////        Employee e3 = new Employee("Charlie", 1992, "Engineering");
////        GeneralStaff g = new GeneralStaff("Alice", 1990, "Finance", "Clerical");
////        System.out.println(g);
////
////
////        System.out.println(e1);
////        System.out.println(e2);
////        System.out.println(e3);
////        System.out.println("Total Employees: " + Employee.getNumEmployees());
////
////        System.out.println(cr.toString());
////    }
//
//    public static class Driver_SchoolDB {
//
//        /* ===== Catalogs (in-memory “tables”) ===== */
//        private final List<Course> courses = new ArrayList<>();
//        private final List<Person> persons = new ArrayList<>();
//        private final List<Employee> employees = new ArrayList<>();
//        private final List<GeneralStaff> staff = new ArrayList<>();
//        private final List<Faculty> faculty = new ArrayList<>();
//        private final List<Student> students = new ArrayList<>();
//
//        private final Scanner scnr = new Scanner(System.in);
//
//        public static void main(String[] args) {
//            new Driver_SchoolDB().run();
//        }
//
//        public void run() {
//            final String inputPath = "SchoolDB_Initial.txt";
//            final String outputPath = "SchoolDB_Updated.txt";
//
//            /* 1) Read + echo file; 2) Build objects */
//            readInitialFileAndBuildObjects(inputPath);
//
//            /* 3) Display all existing objects */
//            System.out.println();
//            printAllObjects();
//
//            /* 4) Interactive menu (covers all rubric actions) */
//            runMenu();
//
//            /* 5) Display all objects again (existing + newly added) */
//            System.out.println();
//            System.out.println("****************************************************************");
//            System.out.println("AFTER MENU – ALL OBJECTS:");
//            System.out.println("****************************************************************");
//            printAllObjects();
//
//            /* 6) Write out all objects to output file (same format) */
//            writeAllObjectsToFile(outputPath);
//            System.out.println("\nWrote all object details to: " + outputPath);
//        }
//
//
//        private void readInitialFileAndBuildObjects(String path) {
//            System.out.println("****************************************************************");
//            System.out.println("SCHOOL DATABASE INFO:");
//            System.out.println("****************************************************************");
//            System.out.println();
//            System.out.println("------------------------- RAW FILE -----------------------------");
//
//            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    // Echo the line exactly
//                    System.out.println(line);
//
//                    // Skip blank lines
//                    if (line.trim().isEmpty()) continue;
//
//                    // Parse
//                    parseAndCreate(line.trim());
//                }
//            } catch (IOException e) {
//                System.out.println("ERROR reading file \"" + path + "\": " + e.getMessage());
//            }
//
//            System.out.println("----------------------------------------------------------------");
//        }
//
//        private void parseAndCreate(String line) {
//            // Each section starts with a label:
//            // Course: <csv> | Faculty: <csv> | Student: <csv> | GeneralStaff: <csv>
//            // The CSV for each case mirrors your initial file format.
//            try {
//                if (line.startsWith("Course:")) {
//                    String payload = line.substring("Course:".length()).trim();
//                    // Formats:
//                    // Course: isGrad(boolean), courseNum(int), courseDept(String), numCredits(int)
//                    String[] parts = payload.split(",");
//                    boolean isGrad = parseBoolean(parts[0].trim());
//                    int cnum = Integer.parseInt(parts[1].trim());
//                    String dept = parts[2].trim();
//                    int credits = Integer.parseInt(parts[3].trim());
//                    Course c = new Course(isGrad, cnum, dept, credits);
//                    courses.add(c);
//                } else if (line.startsWith("Faculty:")) {
//                    String payload = line.substring("Faculty:".length()).trim();
//
//                    Faculty f;
//                    if (payload.isEmpty()) {
//                        f = new Faculty();
//                    } else {
//                        String[] parts = payload.split(",");
//                        if (parts.length == 1) {
//                            boolean ten = parseBoolean(parts[0].trim());
//                            f = new Faculty(ten);
//                        } else if (parts.length == 2) {
//                            String dept = parts[0].trim();
//                            boolean ten = parseBoolean(parts[1].trim());
//                            f = new Faculty(dept, ten);
//                        } else {
//                            String name = parts[0].trim();
//                            int byear = Integer.parseInt(parts[1].trim());
//                            String dept = parts[2].trim();
//                            boolean ten = parseBoolean(parts[3].trim());
//                            f = new Faculty(name, byear, dept, ten);
//                        }
//                    }
//                    faculty.add(f);
//                    employees.add(f);
//                    persons.add(f);
//                } else if (line.startsWith("Student:")) {
//                    String payload = line.substring("Student:".length()).trim();
//
//                    Student s;
//                    if (payload.isEmpty()) {
//                        s = new Student();
//                    } else {
//                        String[] parts = payload.split(",");
//                        if (parts.length == 1) {
//                            boolean grad = parseBoolean(parts[0].trim());
//                            s = new Student(grad);
//                        } else if (parts.length == 2) {
//                            String major = parts[0].trim();
//                            boolean grad = parseBoolean(parts[1].trim());
//                            s = new Student(major, grad);
//                        } else {
//                            String name = parts[0].trim();
//                            int byear = Integer.parseInt(parts[1].trim());
//                            String major = parts[2].trim();
//                            boolean grad = parseBoolean(parts[3].trim());
//                            s = new Student(name, byear,major, grad);
//                        }
//                    }
//                    students.add(s);
//                    persons.add(s);
//                } else if (line.startsWith("GeneralStaff:")) {
//                    String payload = line.substring("GeneralStaff:".length()).trim();
//
//                    GeneralStaff gs;
//                    if (payload.isEmpty()) {
//                        gs = new GeneralStaff();
//                    } else {
//                        String[] parts = payload.split(",");
//                        if (parts.length == 1) {
//                            gs = new GeneralStaff(parts[0].trim());
//                        } else if (parts.length == 2) {
//                            gs = new GeneralStaff(parts[0].trim(), parts[1].trim());
//                        } else {
//                            String name = parts[0].trim();
//                            int byear = Integer.parseInt(parts[1].trim());
//                            String dept = parts[2].trim();
//                            String duty = parts[3].trim();
//                            gs = new GeneralStaff(name, byear, dept, duty);
//                        }
//                    }
//                    staff.add(gs);
//                    employees.add(gs);
//                    persons.add(gs);
//                }
//            } catch (Exception ex) {
//                System.out.println("Parse warning for line: \"" + line + "\" -> " + ex.getMessage());
//            }
//        }
//
//        private boolean parseBoolean(String s) {
//            return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t") || s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y");
//        }
//
//        private void writeAllObjectsToFile(String outPath) {
//            try (PrintWriter pw = new PrintWriter(new FileWriter(outPath))) {
//                // Same order/format as input file
//                for (Course c : courses) {
//                    pw.printf("Course: %s, %d, %s, %d%n",
//                            c.isGraduateCourse(), c.getCourseNum(), c.getCourseDept(), c.getNumCredits());
//                }
//
//                for (Faculty f : faculty) {
//                    // Choose “most informative” encoding (full if name set; else dept/tenure; else single flag; else blank)
//                    String name = f.getName();
//                    boolean hasName = (name != null && !name.isEmpty());
//                    String dept = f.getDeptName();
//                    boolean hasDept = (dept != null && !dept.isEmpty());
//                    boolean ten = f.isTenured();
//
//                    if (hasName) {
//                        pw.printf("Faculty: %s,%d,%s,%s%n",
//                                f.getName(), f.getBirthYear(), dept == null ? "" : dept, ten);
//                    } else if (hasDept) {
//                        pw.printf("Faculty: %s,%s%n", dept, ten);
//                    } else {
//                        pw.printf("Faculty: %s%n", ten);
//                    }
//                }
//
//                for (Student s : students) {
//                    String name = s.getName();
//                    boolean hasName = (name != null && !name.isEmpty());
//                    String major = s.getMajor();
//                    boolean grad = s.isGraduate();
//
//                    if (hasName) {
//                        pw.printf("Student: %s,%d,%s,%s%n",
//                                s.getName(), s.getBirthYear(), major, grad);
//                    } else if (major != null && !major.isEmpty() && !"undeclared".equalsIgnoreCase(major)) {
//                        pw.printf("Student: %s,%s%n", major, grad);
//                    } else {
//                        pw.printf("Student: %s%n", grad);
//                    }
//                }
//
//                for (GeneralStaff gs : staff) {
//                    String name = gs.getName();
//                    boolean hasName = (name != null && !name.isEmpty());
//                    String dept = gs.getDeptName();
//                    String duty = gs.getDuty();
//
//                    if (hasName) {
//                        pw.printf("GeneralStaff: %s,%d,%s,%s%n",
//                                gs.getName(), gs.getBirthYear(), dept == null ? "" : dept, duty == null ? "" : duty);
//                    } else if (dept != null && !dept.isEmpty()) {
//                        pw.printf("GeneralStaff: %s,%s%n", dept, duty == null ? "" : duty);
//                    } else if (duty != null) {
//                        pw.printf("GeneralStaff: %s%n", duty);
//                    } else {
//                        pw.println("GeneralStaff:");
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("ERROR writing file \"" + outPath + "\": " + e.getMessage());
//            }
//        }
//
//
//        private void printAllObjects() {
//            System.out.println("****************************************************************");
//            System.out.println("COURSES:");
//            System.out.println("****************************************************************");
//            for (Course c : courses) System.out.println(c);
//
//            System.out.println("****************************************************************");
//            System.out.println("PERSONS:");
//            System.out.println("****************************************************************");
//            for (Person p : persons) System.out.println(p);
//
//            System.out.println("****************************************************************");
//            System.out.println("EMPLOYEES:");
//            System.out.println("****************************************************************");
//            for (Employee e : employees) System.out.println(e);
//
//            System.out.println("****************************************************************");
//            System.out.println("GENERAL STAFF:");
//            System.out.println("****************************************************************");
//            for (GeneralStaff g : staff) System.out.println(g);
//
//            System.out.println("****************************************************************");
//            System.out.println("FACULTY:");
//            System.out.println("****************************************************************");
//            for (Faculty f : faculty) System.out.println(f);
//
//            System.out.println("****************************************************************");
//            System.out.println("STUDENTS:");
//            System.out.println("****************************************************************");
//            for (Student s : students) System.out.println(s);
//            System.out.println("****************************************************************");
//        }
//
//    /* ============================================================
//       MENU
//       ============================================================ */
//
//        private void runMenu() {
//            while (true) {
//                System.out.println("\n===== MENU =====");
//                System.out.println("1) Create 3 Courses");
//                System.out.println("2) Create 3 Faculty");
//                System.out.println("3) Create 3 GeneralStaff");
//                System.out.println("4) Create 3 Students");
//                System.out.println("5) Add 2 Courses to a Faculty");
//                System.out.println("6) Add 2 Courses to a Student");
//                System.out.println("7) Add array[2] Courses to a Faculty");
//                System.out.println("8) Add array[2] Courses to a Student");
//                System.out.println("9) Faculty: getCourseTaught(index) (valid & invalid)");
//                System.out.println("10) Student: getCourseTaken(index) (valid & invalid)");
//                System.out.println("11) Check if a selected Faculty teaches a selected Course");
//                System.out.println("12) Faculty teaching most & least courses");
//                System.out.println("13) Minimum & Maximum Course (by compareTo)");
//                System.out.println("14) Student with most & least credits");
//                System.out.println("15) Show all objects");
//                System.out.println("0) Exit menu");
//                System.out.print("Choice: ");
//
//                int choice = safeInt();
//                switch (choice) {
//                    case 1 -> createNCourses(3);
//                    case 2 -> createNFaculty(3);
//                    case 3 -> createNStaff(3);
//                    case 4 -> createNStudents(3);
//                    case 5 -> addTwoCoursesToFaculty();
//                    case 6 -> addTwoCoursesToStudent();
//                    case 7 -> addArrayTwoCoursesToFaculty();
//                    case 8 -> addArrayTwoCoursesToStudent();
//                    case 9 -> demoFacultyGetCourseIndex();
//                    case 10 -> demoStudentGetCourseIndex();
//                    case 11 -> facultyTeachesCourseQuery();
//                    case 12 -> facultyMostLeastCourses();
//                    case 13 -> minMaxCourse();
//                    case 14 -> studentMostLeastCredits();
//                    case 15 -> printAllObjects();
//                    case 0 -> { return; }
//                    default -> System.out.println("Invalid choice.");
//                }
//            }
//        }
//
//        /* ---------- creation helpers ---------- */
//
//        private void createNCourses(int n) {
//            for (int i = 0; i < n; i++) {
//                System.out.println("New Course #" + (i+1));
//                System.out.print(" Graduate? (true/false): "); boolean g = scnr.nextBoolean();
//                System.out.print(" Course number (int): ");     int num = scnr.nextInt();
//                System.out.print(" Dept (e.g., MAT, CMP): ");   String dept = scnr.next();
//                System.out.print(" Credits (int): ");           int cr = scnr.nextInt();
//                courses.add(new Course(g, num, dept, cr));
//            }
//        }
//
//        private void createNFaculty(int n) {
//            for (int i = 0; i < n; i++) {
//                System.out.println("New Faculty #" + (i+1));
//                System.out.print(" Name: ");            String name = readLineToken();
//                System.out.print(" Birth year: ");      int by = scnr.nextInt();
//                System.out.print(" Dept: ");            String dept = scnr.next();
//                System.out.print(" Is Tenured? (true/false): "); boolean ten = scnr.nextBoolean();
//                Faculty f = new Faculty(name, by, dept, ten);
//                faculty.add(f); employees.add(f); persons.add(f);
//            }
//        }
//
//        private void createNStaff(int n) {
//            for (int i = 0; i < n; i++) {
//                System.out.println("New GeneralStaff #" + (i+1));
//                System.out.print(" Name: ");       String name = readLineToken();
//                System.out.print(" Birth year: "); int by = scnr.nextInt();
//                System.out.print(" Dept: ");       String dept = scnr.next();
//                System.out.print(" Duty: ");       String duty = readLineToken();
//                GeneralStaff g = new GeneralStaff(name, by, dept, duty);
//                staff.add(g); employees.add(g); persons.add(g);
//            }
//        }
//
//        private void createNStudents(int n) {
//            for (int i = 0; i < n; i++) {
//                System.out.println("New Student #" + (i+1));
//                System.out.print(" Name: ");        String name = readLineToken();
//                System.out.print(" Birth year: ");  int by = scnr.nextInt();
//                System.out.print(" Major: ");       String major = scnr.next();
//                System.out.print(" Graduate? (true/false): "); boolean grad = scnr.nextBoolean();
//                Student s = new Student(name, by, grad, major);
//                students.add(s); persons.add(s);
//            }
//        }
//
//        /* ---------- add courses ---------- */
//
//        private Course pickCourse(String prompt) {
//            if (courses.isEmpty()) { System.out.println("No courses available."); return null; }
//            System.out.println(prompt);
//            for (int i = 0; i < courses.size(); i++) {
//                System.out.printf("%2d) %s%n", i, courses.get(i));
//            }
//            System.out.print("Select index: ");
//            int idx = safeInt();
//            if (idx < 0 || idx >= courses.size()) { System.out.println("Invalid index."); return null; }
//            return courses.get(idx);
//        }
//
//        private Faculty pickFaculty(String prompt) {
//            if (faculty.isEmpty()) { System.out.println("No faculty available."); return null; }
//            System.out.println(prompt);
//            for (int i = 0; i < faculty.size(); i++) {
//                System.out.printf("%2d) %s%n", i, faculty.get(i));
//            }
//            System.out.print("Select index: ");
//            int idx = safeInt();
//            if (idx < 0 || idx >= faculty.size()) { System.out.println("Invalid index."); return null; }
//            return faculty.get(idx);
//        }
//
//        private Student pickStudent(String prompt) {
//            if (students.isEmpty()) { System.out.println("No students available."); return null; }
//            System.out.println(prompt);
//            for (int i = 0; i < students.size(); i++) {
//                System.out.printf("%2d) %s%n", i, students.get(i));
//            }
//            System.out.print("Select index: ");
//            int idx = safeInt();
//            if (idx < 0 || idx >= students.size()) { System.out.println("Invalid index."); return null; }
//            return students.get(idx);
//        }
//
//        private void addTwoCoursesToFaculty() {
//            Faculty f = pickFaculty("Pick Faculty:");
//            if (f == null) return;
//            for (int i = 0; i < 2; i++) {
//                Course c = pickCourse("Pick Course #" + (i+1) + ":");
//                if (c != null) f.addCourseTaught(c);
//            }
//            System.out.println("Updated: " + f);
//        }
//
//        private void addTwoCoursesToStudent() {
//            Student s = pickStudent("Pick Student:");
//            if (s == null) return;
//            for (int i = 0; i < 2; i++) {
//                Course c = pickCourse("Pick Course #" + (i+1) + ":");
//                if (c != null) s.addCourseTaken(c);
//            }
//            System.out.println("Updated: " + s);
//        }
//
//        private void addArrayTwoCoursesToFaculty() {
//            Faculty f = pickFaculty("Pick Faculty:");
//            if (f == null) return;
//            Course c1 = pickCourse("Pick Course #1:");
//            Course c2 = pickCourse("Pick Course #2:");
//            if (c1 != null && c2 != null) f.addCoursesTaught(new Course[]{c1, c2});
//            System.out.println("Updated: " + f);
//        }
//
//        private void addArrayTwoCoursesToStudent() {
//            Student s = pickStudent("Pick Student:");
//            if (s == null) return;
//            Course c1 = pickCourse("Pick Course #1:");
//            Course c2 = pickCourse("Pick Course #2:");
//            if (c1 != null && c2 != null) s.addCoursesTaken(new Course[]{c1, c2});
//            System.out.println("Updated: " + s);
//        }
//
//        /* ---------- index demos ---------- */
//
//        private void demoFacultyGetCourseIndex() {
//            Faculty f = pickFaculty("Pick Faculty for index demo:");
//            if (f == null) return;
//            System.out.print("Enter an index to fetch: ");
//            int idx = safeInt();
//            Course c = f.getCourseTaught(idx);
//            System.out.println("getCourseTaught(" + idx + ") -> " + (c == null ? "null" : c));
//            System.out.println("getCourseTaughtAsString(" + idx + ") -> \"" + f.getCourseTaughtAsString(idx) + "\"");
//        }
//
//        private void demoStudentGetCourseIndex() {
//            Student s = pickStudent("Pick Student for index demo:");
//            if (s == null) return;
//            System.out.print("Enter an index to fetch: ");
//            int idx = safeInt();
//            Course c = s.getCourseTaken(idx);
//            System.out.println("getCourseTaken(" + idx + ") -> " + (c == null ? "null" : c));
//            System.out.println("getCourseTakenAsString(" + idx + ") -> \"" + s.getCourseTakenAsString(idx) + "\"");
//        }
//
//        /* ---------- queries ---------- */
//
//        private void facultyTeachesCourseQuery() {
//            Faculty f = pickFaculty("Select a Faculty:");
//            Course c = pickCourse("Select a Course:");
//            if (f == null || c == null) return;
//            boolean teaches = false;
//            for (int i = 0; i < f.getNumCoursesTaught(); i++) {
//                Course ct = f.getCourseTaught(i);
//                if (c.equals(ct)) { teaches = true; break; }
//            }
//            System.out.println(f.getName() + (teaches ? " teaches " : " does NOT teach ") + c.getCourseDept() + "-" + c.getCourseNum());
//        }
//
//        private void facultyMostLeastCourses() {
//            if (faculty.isEmpty()) { System.out.println("No faculty."); return; }
//            Faculty most = faculty.get(0), least = faculty.get(0);
//            for (Faculty f : faculty) {
//                if (f.getNumCoursesTaught() > most.getNumCoursesTaught()) most = f;
//                if (f.getNumCoursesTaught() < least.getNumCoursesTaught()) least = f;
//            }
//            System.out.println("Most courses:  " + most.getName() + " -> " + most.getNumCoursesTaught());
//            System.out.println("Least courses: " + least.getName() + " -> " + least.getNumCoursesTaught());
//        }
//
//        private void minMaxCourse() {
//            if (courses.isEmpty()) { System.out.println("No courses."); return; }
//            Course min = courses.get(0), max = courses.get(0);
//            for (Course c : courses) {
//                if (c.compareTo(min) < 0) min = c;
//                if (c.compareTo(max) > 0) max = c;
//            }
//            System.out.println("Min course: " + min);
//            System.out.println("Max course: " + max);
//        }
//
//        private int totalCredits(Student s) {
//            int sum = 0;
//            for (int i = 0; i < s.getNumCoursesTaken(); i++) {
//                Course c = s.getCourseTaken(i);
//                if (c != null) sum += c.getNumCredits();
//            }
//            return sum;
//        }
//
//        private void studentMostLeastCredits() {
//            if (students.isEmpty()) { System.out.println("No students."); return; }
//            Student most = students.get(0), least = students.get(0);
//            int mostCr = totalCredits(most), leastCr = mostCr;
//
//            for (int i = 1; i < students.size(); i++) {
//                Student s = students.get(i);
//                int cr = totalCredits(s);
//                if (cr > mostCr) { most = s; mostCr = cr; }
//                if (cr < leastCr) { least = s; leastCr = cr; }
//            }
//            System.out.println("Most credits:  " + most.getName() + " -> " + mostCr);
//            System.out.println("Least credits: " + least.getName() + " -> " + leastCr);
//        }
//
//        /* ---------- small utils ---------- */
//
//        private int safeInt() {
//            while (!scnr.hasNextInt()) { System.out.print("Enter an integer: "); scnr.next(); }
//            return scnr.nextInt();
//        }
//
//        // Reads a token possibly containing spaces until newline (use after printing a prompt)
//        private String readLineToken() {
//            scnr.nextLine(); // consume pending newline if any
//            String s = scnr.nextLine();
//            if (s == null) return "";
//            return s.isEmpty() ? "" : s.trim();
//        }
//    }
//
//}