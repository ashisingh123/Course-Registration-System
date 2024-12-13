package Intern;
import java.sql.*;
import java.util.Scanner;

public class Student_course_registration_system {
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String username = "system";
    private static final String password = "ashi1008";
    static Connection conn;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();

        // Drop and create tables
        setupDatabase(stmt);

        Boolean exit = false;

        while (!exit) {
            System.out.println("COURSE REGISTRATION SYSTEM");
            System.out.println("1. List Courses\n2. Register for a Course\n3. Drop a Course\n4. Show student Information\n5. Exit");
            System.out.println("Enter your choice: ");

            switch (sc.nextInt()) {
                case 1:
                    courseList();
                    break;
                case 2:
                    registration();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    StudentInfo();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for Registration!\nHave a nice day!");
                    break;
                default:
                    System.out.println("Enter a valid choice!");
            }
        }
    }

    private static void setupDatabase(Statement stmt) throws SQLException {
//         Drop the table if it exists
        String dropTableQuery = "DROP TABLE CourseINFO2";
        stmt.executeUpdate(dropTableQuery);
//        System.out.println("Existing 'CourseINFO2' table dropped successfully.");

        // Create the course information table
        String query = "CREATE TABLE CourseINFO2 ( Course_Name VARCHAR(100) NOT NULL, Course_id INT PRIMARY KEY, Course_code VARCHAR(10) NOT NULL, description VARCHAR(50), capacity INT NOT NULL, schedule VARCHAR(50) NOT NULL)";
        stmt.executeUpdate(query);
//        System.out.println("Table 'CourseINFO2' created successfully.");

        String dropTableQuery2 = "DROP TABLE Registration";
        stmt.executeUpdate(dropTableQuery2);
//        System.out.println("Existing 'Registration' table dropped successfully.");

        // Create registration table
        String query2 = "CREATE TABLE Registration ( Name VARCHAR(100) NOT NULL, id INT NOT NULL, Course INT NOT NULL, PRIMARY KEY (id, Course))";
        stmt.executeUpdate(query2);
//        System.out.println("Table 'Registration' created successfully.");

        // Insert data into the table
        String insertDataQuery = "INSERT INTO CourseINFO2 (Course_Name, Course_id, Course_code, description, capacity, schedule) VALUES ('Introduction to Java', 1, 'CS101', 'Basic programming concepts', 30, 'Mon-Wed-Fri')";
        stmt.executeUpdate(insertDataQuery);
        String insertDataQuery1 = "INSERT INTO CourseINFO2 (Course_Name, Course_id, Course_code, description, capacity, schedule) VALUES ('Data Structures', 2, 'CS102', 'Advanced programming concepts', 15, 'Tue-Thu')";
        stmt.executeUpdate(insertDataQuery1);
        String insertDataQuery2 = "INSERT INTO CourseINFO2 (Course_Name, Course_id, Course_code, description, capacity, schedule) VALUES ('Operating Systems', 3, 'CS103', 'Understanding OS', 50, 'Wed-Fri')";
        stmt.executeUpdate(insertDataQuery2);
        String insertDataQuery3 = "INSERT INTO CourseINFO2 (Course_Name, Course_id, Course_code, description, capacity, schedule) VALUES ('Data Analytics', 4, 'CS104', 'Data Forming using Python', 25, 'Mon-Tue-Wed')";
        stmt.executeUpdate(insertDataQuery3);
//        System.out.println("Data inserted into 'CourseINFO2' table successfully.");

    }

    private static void courseList() throws SQLException {
        Statement stmt = conn.createStatement();
        String selectQuery = "SELECT C.Course_id, C.Course_Name, C.Course_code, C.description, C.capacity, C.schedule, (C.capacity - COUNT(R.id)) AS available_slots FROM CourseINFO2 C LEFT JOIN Registration R ON C.Course_id = R.Course GROUP BY C.Course_id, C.Course_Name, C.Course_code, C.description, C.capacity, C.schedule ORDER BY C.Course_id";
        ResultSet rs = stmt.executeQuery(selectQuery);
        while (rs.next()) {
            String courseName = rs.getString("Course_Name");
            int courseId = rs.getInt("Course_id");
            String courseCode = rs.getString("Course_code");
            String description = rs.getString("description");
            int capacity = rs.getInt("capacity");
            String schedule = rs.getString("schedule");
            int availableSlots = rs.getInt("available_slots");
            System.out.printf("Course ID: %d\nCourse Name: %s\nCourse Code: %s\nDescription: %s\nCapacity: %d\nSchedule: %s\nAvailable Slots: %d\n-----------------------------\n", courseId, courseName, courseCode, description, capacity, schedule, availableSlots);
        }
    }

    private static void registration() throws Exception {
        sc.nextLine(); // Consume the newline character after nextInt()
        System.out.println("Enter your name:");
        String name = sc.nextLine();

        System.out.println("Enter your ID:");
        int id = sc.nextInt();
        sc.nextLine(); // Consume the newline character after nextInt()

        System.out.println("Enter the course ID:(1:Java  2:DS  3:OS  4:DA)");
        int course = sc.nextInt();

        String checkSlotsQuery = "SELECT capacity - COUNT(R.id) AS available_slots FROM CourseINFO2 C LEFT JOIN Registration R ON C.Course_id = R.Course WHERE C.Course_id = ? GROUP BY C.capacity";
        PreparedStatement checkStmt = conn.prepareStatement(checkSlotsQuery);
        checkStmt.setInt(1, course);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt("available_slots") > 0) {
            String insertQuery = "INSERT INTO Registration (Name, id, Course) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.setInt(3, course);
            int rows = pstmt.executeUpdate();
            System.out.println("Data inserted successfully. Rows affected: " + rows);
        } else {
            System.out.println("No available slots for this course.");
        }
    }

    private static void dropCourse() throws Exception {
        System.out.print("Enter student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter course ID:(1:Java  2:DS  3:OS  4:DA)");
        int courseId = sc.nextInt();
        String query = "DELETE FROM Registration WHERE id = ? AND Course = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course dropped successfully.");
            } else {
                System.out.println("No registration found to drop.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void StudentInfo() throws Exception{
        Statement stmt = conn.createStatement();
        String selectQuery = "SELECT * FROM Registration";
        ResultSet rs = stmt.executeQuery(selectQuery);
        while (rs.next()) {
            String name = rs.getString("Name");
            int id = rs.getInt("id");
            String course = rs.getString("Course");
            System.out.printf("Name: %s\tID: %d\nCourse: %s\n-----------------------------\n",name,id,course);
        }

    }
}
