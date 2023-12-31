package database;

import java.sql.*;
import javax.swing.JOptionPane;

public class LocalDatabase {

    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;
    public static enum MODES {INSERT_MODE, UPDATE_MODE, DISPLAY_MODE};

    public LocalDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:sqlite:shcool.db");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            if (con != null) {
                createDatabase();
                System.out.println("Connected successfuly.");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Please run or restart the server!!!", "Server connection Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createDatabase() {

        try {
            String createDatabaseSQl = "CREATE DATABASE IF NOT EXISTS school";
            String useDatabaseSQL = "USE school";
            ps = con.prepareStatement(createDatabaseSQl);
            ps.executeUpdate();
            ps = con.prepareStatement(useDatabaseSQL);
            ps.executeUpdate();
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
            System.out.println("Database created successfuly");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to create database it already exists.");
        }
    }

    public void createTable(String tableName, String tableCols) {

        try {
            String createTeacherTableSQl = "CREATE TABLE IF NOT EXISTS " + tableName
                    + " (" + tableCols + ")";

            ps = con.prepareStatement(createTeacherTableSQl);
            if (ps.execute()) {
                System.out.println("Teachers table created successfuly");
            } else {
                System.out.println("Failed to create Teachers table or it already exists.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCon() {
        return (Connection) con;
    }

}
