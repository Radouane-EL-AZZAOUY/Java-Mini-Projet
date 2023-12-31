package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CourseData;
import model.FieldData;
import model.TimetableData;

/**
 *
 * @author Radouane El Azzaouy
 */
public class TimetableController extends LocalDatabase {

    private final String tableName = "timetables";
    private final String colNames = "timetableID INT PRIMARY KEY AUTO_INCREMENT, fieldID INT, day INT, part1 INT, part2 INT, part3 INT, part4 INT";

    public TimetableController() {
        super();
        createTable(tableName, colNames);
    }

    public boolean saveTimetableData(TimetableData timetableData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(timetableID) as total FROM " + tableName + " WHERE fieldID = ? AND day = ?");
                ps.setInt(1, timetableData.getFieldID());
                ps.setInt(2, timetableData.getDay());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Cette date ou partie est déja enregistreé!", "Error de conflit", JOptionPane.WARNING_MESSAGE);
                    return false;
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + " " + timetableData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET fieldID = ?, day = ?, part1 = ?, part2 = ?, part3 = ?, part4 = ? WHERE timetableID = ?");
                ps.setInt(7, timetableData.getID());
            }
            ps.setInt(1, timetableData.getFieldID());
            //ps.setInt(2, timetableData.getType());
            ps.setInt(2, timetableData.getDay());
            ps.setInt(3, timetableData.getPart1());
            ps.setInt(4, timetableData.getPart2());
            ps.setInt(5, timetableData.getPart3());
            ps.setInt(6, timetableData.getPart4());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteTimetable(int ID) {
        try {
            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE timetableID=?");
            ps.setInt(1, ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public TimetableData getTimetableData(int ID) {
        TimetableData timetableData = null;
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE timetableID=?");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            rs.next();
            timetableData = new TimetableData(
                    rs.getInt("timetableID"),
                    rs.getInt("fieldID"),
                    //rs.getInt("type"),
                    rs.getInt("day"),
                    rs.getInt("part1"),
                    rs.getInt("part2"),
                    rs.getInt("part3"),
                    rs.getInt("part4"));
        } catch (SQLException ex) {
            System.err.println("Failed");
            Logger.getLogger(TimetableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timetableData;
    }

    public ArrayList<TimetableData> getAllTimetables() {
        ArrayList<TimetableData> timetablesName = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * from timetables");
            rs = ps.executeQuery();

            while (rs.next()) {
                timetablesName.add(new TimetableData(
                        rs.getInt("timetableID"),
                        rs.getInt("fieldID"),
                        //rs.getInt("type"),
                        rs.getInt("day"),
                        rs.getInt("part1"),
                        rs.getInt("part2"),
                        rs.getInt("part3"),
                        rs.getInt("part4")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return timetablesName;
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {
        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " as t JOIN fields as f ON t.fieldID = f.fieldID WHERE f.fieldName LIKE ?");
                    ps.setString(1, query);
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE day LIKE ?");
                    ps.setInt(1, getDayID(query));
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<TimetableData> searchTimetables(String query, int searchMethod) {
        ArrayList<TimetableData> timetablesName = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);
            // ps.setString(2, "%" + query + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                timetablesName.add(new TimetableData(
                        rs.getInt("timetableID"),
                        rs.getInt("fieldID"),
                        //rs.getInt("type"),
                        rs.getInt("day"),
                        rs.getInt("part1"),
                        rs.getInt("part2"),
                        rs.getInt("part3"),
                        rs.getInt("part4")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return timetablesName;
    }

    public String getDayName(TimetableData timetableData) {
        String day;
        switch (timetableData.getDay()) {
            case 0 ->
                day = "Lundi";
            case 1 ->
                day = "Mardi";
            case 2 ->
                day = "Mercredi";
            case 3 ->
                day = "Jeudi";
            case 4 ->
                day = "Vendredi";
            case 5 ->
                day = "Samedi";
            default ->
                throw new AssertionError();
        }
        return day;
    }

    public int getDayID(String query) {
        int dayID = -1;
        String[] days = new String[]{"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
        for (int i = 0; i < days.length; i++) {
            if (days[i].contains(query.trim().toLowerCase())) {
                dayID = i;
                break;
            }
        }
        return dayID;
    }

    public void initTableViewModel(ArrayList<TimetableData> timetablesData, FieldController fieldController, CourseController courseController, DefaultTableModel tableModel) {
        for (TimetableData timetableData : timetablesData) {
            String field = "Vide";
            String part1 = "Vide";
            String part2 = "Vide";
            String part3 = "Vide";
            String part4 = "Vide";
            for (FieldData fieldData : fieldController.getAllFields()) {
                if (fieldData.getID() == timetableData.getFieldID()) {
                    field = fieldData.getTitle();
                    break;
                }
            }

            for (CourseData courseData : courseController.getAllCourses()) {
                if (courseData.getID() == timetableData.getPart1()) {
                    part1 = courseData.getTitle();
                }
                if (courseData.getID() == timetableData.getPart2()) {
                    part2 = courseData.getTitle();
                }

                if (courseData.getID() == timetableData.getPart3()) {
                    part3 = courseData.getTitle();
                }

                if (courseData.getID() == timetableData.getPart4()) {
                    part4 = courseData.getTitle();
                }

            }

            tableModel.addRow(new String[]{
                getDayName(timetableData),
                field,
                part1,
                part2,
                part3,
                part4
            });
        }
    }

}
