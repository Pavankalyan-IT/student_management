package dao;
import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, age, course) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getCourse());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    public void viewAllStudents() {
        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            System.out.println("\n===== Student Records =====");

            while (rs.next()) {
                found = true;
                System.out.println("ID     : " + rs.getInt("id"));
                System.out.println("Name   : " + rs.getString("name"));
                System.out.println("Age    : " + rs.getInt("age"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("----------------------------");
            }

            if (!found) {
                System.out.println("No students found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching students: " + e.getMessage());
        }
    }

    public void searchStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent found:");
                System.out.println("ID     : " + rs.getInt("id"));
                System.out.println("Name   : " + rs.getString("name"));
                System.out.println("Age    : " + rs.getInt("age"));
                System.out.println("Course : " + rs.getString("course"));
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while searching student: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, int age, String course) {
        String query = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting student: " + e.getMessage());
        }
    }
}