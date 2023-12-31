package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CourseData;
import model.ExamData;

/**
 *
 * @author user
 */
public class CourseController extends LocalDatabase {

    private final String tableName = "courses";
    private final String colNames = "courseID INT PRIMARY KEY AUTO_INCREMENT, title TEXT, duration INT";

    public CourseController() {
        super();
        createTable(tableName, colNames);
    }

    public boolean saveCourseData(CourseData courseData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(courseID) as total FROM " + tableName + " WHERE title LIKE ?");
                ps.setString(1, courseData.getTitle());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Ce cours est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + courseData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET title = ?, duration = ? WHERE courseID = ?");
                ps.setInt(3, courseData.getID());
            }
            ps.setString(1, courseData.getTitle());
            ps.setInt(2, courseData.getDuration());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteCourse(int courseID) {
        try {

            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE courseID = ?");
            ps.setInt(1, courseID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<CourseData> getAllCourses() {
        ArrayList<CourseData> coursesData = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                coursesData.add(new CourseData(
                        rs.getInt("courseID"),
                        rs.getString("title"),
                        rs.getInt("duration")
                ));
            }
            return coursesData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return coursesData;
        }
    }

    public CourseData getCourseData(int courseID) {
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE courseID = ?");
            ps.setInt(1, courseID);
            rs = ps.executeQuery();
            rs.next();
            return new CourseData(
                    rs.getInt("courseID"),
                    rs.getString("title"),
                    rs.getInt("duration")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new CourseData(0, "", 0);
        }
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {

        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE title LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE duration LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<CourseData> searchedCourses(String query, int searchMethod) {
        ArrayList<CourseData> coursesData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);

            rs = ps.executeQuery();
            while (rs.next()) {
                coursesData.add(new CourseData(
                        rs.getInt("courseID"),
                        rs.getString("title"),
                        rs.getInt("duration")
                ));
            }
            return coursesData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return coursesData;
        }
    }

    public String getCourseTitle(int courseID) {
        String courseTitle = "";
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE courseID = ?");
            ps.setInt(1, courseID);
            rs = ps.executeQuery();
            rs.next();
            courseTitle = rs.getString("title");
            return courseTitle;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return courseTitle;
        }
    }

    public int getCourseIndex(int courseID) {
        ArrayList<CourseData> courses = getAllCourses();
        int i = -1;
        for (i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID() == courseID) {
                break;
            }
        }
        return i;
    }

    public void initTableViewModel(ArrayList<CourseData> coursesData, DefaultTableModel tableModel) {
        for (CourseData courseData : coursesData) {
            tableModel.addRow(new String[]{
                courseData.getTitle(),
                courseData.getDuration() + ""
            });
        }
    }
}
