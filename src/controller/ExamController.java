package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ExamData;
import model.FieldData;
import model.TeacherData;

/**
 *
 * @author user
 */
public class ExamController extends LocalDatabase {

    private final String tableName = "exams";
    private final String colNames = "examID INT PRIMARY KEY AUTO_INCREMENT,description TEXT, date TEXT, duration FLOAT, fieldID INT, teacherID INT";

    public ExamController() {
        super();
        createTable(tableName, colNames);
    }

    public boolean saveExamData(ExamData examData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(examID) as total FROM " + tableName + " WHERE description LIKE ? AND fieldID = ? AND teacherID = ?");
                ps.setString(1, examData.getDescription());
                ps.setInt(2, examData.getFieldID());
                ps.setInt(3, examData.getTeacherID());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Cet examen est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + examData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET description = ?, date = ?, duration = ?, fieldID = ?, teacherID = ? WHERE examID = ?");
                ps.setInt(6, examData.getID());
            }
            ps.setString(1, examData.getDescription());
            ps.setString(2, examData.getDate());
            ps.setFloat(3, examData.getDuration());
            ps.setInt(4, examData.getFieldID());
            ps.setInt(5, examData.getTeacherID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteExam(int examID) {
        try {

            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE examID = ?");
            ps.setInt(1, examID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<ExamData> getAllExams() {
        ArrayList<ExamData> examsData = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                examsData.add(new ExamData(
                        rs.getInt("examID"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getFloat("duration"),
                        rs.getInt("fieldID"),
                        rs.getInt("teacherID")
                ));
            }
            return examsData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return examsData;
        }
    }

    public ExamData getExamData(int examID) {
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE examID = ?");
            ps.setInt(1, examID);
            rs = ps.executeQuery();
            rs.next();
            return new ExamData(
                    rs.getInt("examID"),
                    rs.getString("description"),
                    rs.getString("date"),
                    rs.getFloat("duration"),
                    rs.getInt("fieldID"),
                    rs.getInt("teacherID")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ExamData(0, "", "", 0, 0, 0);
        }
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {

        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE description LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE date LIKE ?");
                    ps.setString(1, query + "%");
                }
                case 2 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE duration LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 3 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " as e JOIN fields as f ON e.fieldID = f.fieldID WHERE fieldName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 4 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " as e JOIN teachers as t ON e.teacherID = t.teacherID WHERE t.firstName LIKE ? OR t.lastName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                    ps.setString(2, "%" + query + "%");
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<ExamData> searchedExams(String query, int searchMethod) {
        ArrayList<ExamData> examsData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);

            rs = ps.executeQuery();
            while (rs.next()) {
                examsData.add(new ExamData(
                        rs.getInt("examID"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getFloat("duration"),
                        rs.getInt("fieldID"),
                        rs.getInt("teacherID")
                ));
            }
            return examsData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return examsData;
        }
    }

    public void initTableViewModel(ArrayList<ExamData> examsData, DefaultTableModel tableModel, TeacherController teacherController, FieldController fieldController) {
        for (ExamData examData : examsData) {
            String field = "", teacher = "";
            for (TeacherData teacherData : teacherController.getAllTeachers()) {
                if (teacherData.getID() == examData.getTeacherID()) {
                    teacher = teacherData.getFirstName() + " " + teacherData.getLastName();
                    break;
                }
            }
            for (FieldData fieldData : fieldController.getAllFields()) {
                if (fieldData.getID() == examData.getFieldID()) {
                    field = fieldData.getTitle();
                    break;
                }
            }
            tableModel.addRow(new String[]{
                examData.getDescription(),
                examData.getDate(),
                examData.getDuration() + "",
                field,
                teacher
            });
        }
    }
}
