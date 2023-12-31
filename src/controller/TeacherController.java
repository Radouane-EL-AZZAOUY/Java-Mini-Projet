package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TeacherData;

public class TeacherController extends LocalDatabase {

    private final String tableName = "teachers";
    private final String tableCols = "teacherID INT PRIMARY KEY AUTO_INCREMENT, firstName TEXT, lastName TEXT,"
            + "age INT, specialite INT, adresse TEXT, gender VARCHAR(2), state INT, role INT";

    public TeacherController() {
        super();
        createTable(tableName, tableCols);
    }

    public boolean saveTeacherData(TeacherData teacherData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(teacherID) as total FROM " + tableName + " WHERE firstName LIKE ? AND lastName LIKE ?");
                ps.setString(1, teacherData.getFirstName());
                ps.setString(2, teacherData.getLastName());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Cet enseignant est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + " " + teacherData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET firstName = ?, lastName = ?, age = ?, specialite = ?, adresse = ?, gender = ?, state = ?, role = ? WHERE teacherID = ?");
                ps.setInt(9, teacherData.getID());
            }
            ps.setString(1, teacherData.getFirstName());
            ps.setString(2, teacherData.getLastName());
            ps.setInt(3, teacherData.getAge());
            ps.setInt(4, teacherData.getSpecialite());
            ps.setString(5, teacherData.getAdresse());
            ps.setString(6, teacherData.getGender() + "");
            ps.setInt(7, teacherData.getState());
            ps.setInt(8, teacherData.getRole());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteTeacher(int ID) {
        try {
            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE teacherID=?");
            ps.setInt(1, ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public TeacherData getTeacherData(int ID) {
        TeacherData teacherData = null;
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE teacherID=?");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            rs.next();
            teacherData = new TeacherData(rs.getInt("teacherID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getInt("specialite"),
                    rs.getString("adresse"),
                    rs.getString("gender").charAt(0),
                    rs.getInt("state"),
                    rs.getInt("role"));
        } catch (SQLException ex) {
            System.err.println("Failed");
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teacherData;
    }

    public ArrayList<TeacherData> getAllTeachers() {
        ArrayList<TeacherData> teachersName = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * from teachers");
            rs = ps.executeQuery();

            while (rs.next()) {
                teachersName.add(new TeacherData(rs.getInt("teacherID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getInt("specialite"),
                        rs.getString("adresse"),
                        rs.getString("gender").charAt(0),
                        rs.getInt("state"),
                        rs.getInt("role")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return teachersName;
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {

        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * from teachers WHERE firstName LIKE ? OR LastName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                    ps.setString(2, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * from teachers WHERE age LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 2 -> {
                    ps = con.prepareStatement("SELECT t.* from teachers as t JOIN courses as c ON t.specialite = c.courseID WHERE c.title LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 3 -> {
                    ps = con.prepareStatement("SELECT * from teachers WHERE adresse LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 4 -> {
                    ps = con.prepareStatement("SELECT * from teachers WHERE gender LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<TeacherData> searchTeachers(String query, int searchMethod) {
        ArrayList<TeacherData> teachersData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);
            rs = ps.executeQuery();

            while (rs.next()) {
                teachersData.add(new TeacherData(rs.getInt("teacherID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getInt("specialite"),
                        rs.getString("adresse"),
                        rs.getString("gender").charAt(0),
                        rs.getInt("state"),
                        rs.getInt("role")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return teachersData;
    }

    private String getStateName(int stateIndex) {
        String state = "";
        if (stateIndex > 4) {
            state = "PA-PS-PES";
        } else if (stateIndex > 2) {
            state = "PA-PS";
        } else {
            state = "PA";
        }
        return state;
    }

    private String getRoleName(int roleIndex) {
        String role = "";
        if (roleIndex == 1) {
            role = "Chef de fillière";
        } else if (roleIndex > 2) {
            role = "Chef de département";
        }
        return role;
    }

    public void initTableViewModel(ArrayList<TeacherData> teachersData, DefaultTableModel tableModel, CourseController courseController) {
        for (TeacherData teacherData : teachersData) {

            tableModel.addRow(new String[]{teacherData.getFirstName(),
                teacherData.getLastName(),
                teacherData.getAge() + "",
                courseController.getCourseTitle(teacherData.getSpecialite()),
                teacherData.getAdresse(),
                (teacherData.getGender() == 'm') ? "Homme" : "Femme",
                getStateName(teacherData.getState()),
                getRoleName(teacherData.getRole())});

        }
    }

}
