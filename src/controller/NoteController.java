package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ExamData;
import model.NoteData;
import model.StudentData;

/**
 *
 * @author user
 */
public class NoteController extends LocalDatabase {

    private final String tableName = "notes";
    private final String colNames = "noteID INT PRIMARY KEY AUTO_INCREMENT, studentID INT, examID INT, note FLOAT";

    public NoteController() {
        super();
        createTable(tableName, colNames);
    }

    public boolean saveNoteData(NoteData noteData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(noteID) as total FROM " + tableName + " WHERE studentID = ? AND examID = ?");
                ps.setInt(1, noteData.getStudentID());
                ps.setInt(2, noteData.getExamID());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Cette note est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + noteData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET studentID = ?, examID = ?, note = ? WHERE noteID = ?");
                ps.setInt(4, noteData.getID());
            }
            ps.setInt(1, noteData.getStudentID());
            ps.setInt(2, noteData.getExamID());
            ps.setFloat(3, noteData.getNote());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteNote(int noteID) {
        try {

            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE noteID = ?");
            ps.setInt(1, noteID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<NoteData> getAllNotes() {
        ArrayList<NoteData> notesData = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                notesData.add(new NoteData(
                        rs.getInt("noteID"),
                        rs.getInt("studentID"),
                        rs.getInt("examID"),
                        rs.getFloat("note")
                ));
            }
            return notesData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return notesData;
        }
    }

    public NoteData getNoteData(int noteID) {
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE noteID = ?");
            ps.setInt(1, noteID);
            rs = ps.executeQuery();
            rs.next();
            return new NoteData(
                    rs.getInt("noteID"),
                    rs.getInt("studentID"),
                    rs.getInt("examID"),
                    rs.getFloat("note")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new NoteData(0, 0, 0, 0);
        }
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {

        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * from " + tableName + " as n JOIN exams as e ON n.examID = e.examID WHERE e.description LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT n.* from " + tableName + " as n JOIN students as s ON n.studentID = s.studentID WHERE s.firstName LIKE ? OR s.lastName LIKE ?");
                    ps.setString(1, "%" + query+ "%");
                    ps.setString(2, "%" + query + "%");
                }
                case 2 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE note LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<NoteData> searchedNotes(String query, int searchMethod) {
        ArrayList<NoteData> notesData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);

            rs = ps.executeQuery();
            while (rs.next()) {
                notesData.add(new NoteData(
                        rs.getInt("noteID"),
                        rs.getInt("studentID"),
                        rs.getInt("examID"),
                        rs.getFloat("note")
                ));
            }
            return notesData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return notesData;
        }
    }

    public void initTableViewModel(ArrayList<NoteData> notesData, ExamController examController, StudentController studentController, DefaultTableModel tableModel) {
        for (NoteData noteData : notesData) {
            String exam = "";
            String student = "";
            for (ExamData examData : examController.getAllExams()) {
                if (examData.getID() == noteData.getExamID()) {
                    exam = examData.getDate() + " " + examData.getDescription();
                    break;
                }
            }

            for (StudentData studentData : studentController.getAllStudents()) {
                if (studentData.getID() == noteData.getStudentID()) {
                    student = studentData.getFirstName() + " " + studentData.getLastName();
                }
            }
            tableModel.addRow(new String[]{
                exam,
                student,
                noteData.getNote() + "",});
        }
    }
}
