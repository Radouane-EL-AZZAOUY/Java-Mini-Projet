package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.StudentData;
import model.TeacherData;

public class StudentController extends LocalDatabase {

    private final String tableName = "students";
    private final String tableCols = "studentID INT PRIMARY KEY AUTO_INCREMENT, firstName TEXT, lastName TEXT,"
            + "age INT, field INT, adresse TEXT, gender VARCHAR(2), inscriptionDate TEXT, state INT";

    public StudentController() {
        super();
        createTable(tableName, tableCols);
    }

    public boolean saveStudentData(StudentData studentData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(studentID) as total FROM " + tableName + " WHERE firstName LIKE ? AND lastName LIKE ?");
                ps.setString(1, studentData.getFirstName());
                ps.setString(2, studentData.getLastName());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Cet étudiant est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + " " + studentData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET firstName = ?, lastName = ?, age = ?, field = ?, adresse = ?, gender = ?, inscriptionDate = ?, state = ? WHERE studentID = ?");
                ps.setInt(9, studentData.getID());
            }
            ps.setString(1, studentData.getFirstName());
            ps.setString(2, studentData.getLastName());
            ps.setInt(3, studentData.getAge());
            ps.setInt(4, studentData.getFieldID());
            ps.setString(5, studentData.getAdresse());
            ps.setString(6, studentData.getGender() + "");
            ps.setString(7, studentData.getInscriptionDate());
            ps.setInt(8, studentData.getState());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteStudent(int ID) {
        try {
            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE studentID=?");
            ps.setInt(1, ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public StudentData getStudentData(int ID) {
        StudentData studentData = null;
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE studentID=?");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            rs.next();
            studentData = new StudentData(rs.getInt("studentID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getInt("field"),
                    rs.getString("adresse"),
                    rs.getString("gender").charAt(0),
                    rs.getString("inscriptionDate"),
                    rs.getInt("state")
            );
        } catch (SQLException ex) {
            System.err.println("Failed");
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentData;
    }

    public ArrayList<StudentData> getAllStudents() {
        ArrayList<StudentData> teachersName = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * from " + tableName);
            rs = ps.executeQuery();

            while (rs.next()) {
                teachersName.add(new StudentData(rs.getInt("studentID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getInt("field"),
                        rs.getString("adresse"),
                        rs.getString("gender").charAt(0),
                        rs.getString("inscriptionDate"),
                        rs.getInt("state"))
                );
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
                    ps = con.prepareStatement("SELECT * from " + tableName + " WHERE firstName LIKE ? OR LastName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                    ps.setString(2, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " WHERE age LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 2 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " as s JOIN fields as f ON s.field = f.fieldID WHERE f.fieldName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 3 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " WHERE adresse LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 4 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " WHERE gender LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 5 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " WHERE inscriptionDate LIKE ?");
                    ps.setString(1, query + "%");
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<StudentData> searchStudents(String query, int searchMethod) {
        ArrayList<StudentData> studentsData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);

            rs = ps.executeQuery();

            while (rs.next()) {
                studentsData.add(new StudentData(rs.getInt("studentID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getInt("field"),
                        rs.getString("adresse"),
                        rs.getString("gender").charAt(0),
                        rs.getString("inscriptionDate"),
                        rs.getInt("state")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return studentsData;
    }

    public void initTableViewModel(ArrayList<StudentData> studentsData, DefaultTableModel tableModel, FieldController fieldController) {
        for (StudentData studentData : studentsData) {
            tableModel.addRow(new String[]{
                studentData.getFirstName(),
                studentData.getLastName(),
                studentData.getAge() + "",
                fieldController.getFieldName(studentData.getFieldID()),
                studentData.getAdresse(),
                (studentData.getGender() == 'm') ? "Homme" : "Femme",
                studentData.getInscriptionDate(),
                (studentData.getState() == 1) ? "Actif" : "Inactif"}
            );
        }
    }

}
