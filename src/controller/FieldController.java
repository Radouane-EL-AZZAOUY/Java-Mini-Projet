package controller;

import database.LocalDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FieldData;

public class FieldController extends LocalDatabase {

    private final String tableName = "fields";
    private final String colNames = "fieldID INT PRIMARY KEY AUTO_INCREMENT, fieldName TEXT, maxLimit INT";

    public FieldController() {
        super();
        createTable(tableName, colNames);
    }

    public boolean saveFieldData(FieldData fieldData, MODES MODE) {
        try {
            if (MODE == MODES.INSERT_MODE) {
                ps = con.prepareStatement("SELECT COUNT(fieldID) as total FROM " + tableName + " WHERE fieldName LIKE ?");
                ps.setString(1, fieldData.getTitle());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt("total") > 0) {
                    JOptionPane.showMessageDialog(null, "Ce cours est déja enregistré!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    ps = con.prepareStatement("INSERT INTO " + tableName + fieldData.toSQL());
                }
            } else {
                ps = con.prepareStatement("UPDATE " + tableName + " SET fieldName = ?, maxLimit = ? WHERE fieldID = ?");
                ps.setInt(3, fieldData.getID());
            }
            ps.setString(1, fieldData.getTitle());
            ps.setInt(2, fieldData.getMaxLimit());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int getFieldID(String fieldName) {
        int fieldID = -1;
        try {
            ps = con.prepareStatement("SELECT fieldID FROM " + tableName + " WHERE fieldName LIKE ?");
            ps.setString(1, fieldName);
            rs = ps.executeQuery();
            rs.next();
            fieldID = rs.getInt("fieldID");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return fieldID;
    }

    public String getFieldName(int fieldID) {
        String fieldName = "";
        try {
            ps = con.prepareStatement("SELECT fieldName FROM " + tableName + " WHERE fieldID = ?");
            ps.setInt(1, fieldID);
            rs = ps.executeQuery();
            rs.next();
            fieldName = rs.getString("fieldName");
            return fieldName;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return fieldName;
    }

    public boolean deleteField(int fieldID) {
        try {

            ps = con.prepareStatement("DELETE FROM " + tableName + " WHERE fieldID = ?");
            ps.setInt(1, fieldID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<FieldData> getAllFields() {
        ArrayList<FieldData> fieldsData = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                fieldsData.add(new FieldData(
                        rs.getInt("fieldID"),
                        rs.getString("fieldName"),
                        rs.getInt("maxLimit")
                ));
            }
            return fieldsData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return fieldsData;
        }
    }

    public FieldData getFieldData(int fieldID) {
        try {
            ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE fieldID = ?");
            ps.setInt(1, fieldID);
            rs = ps.executeQuery();
            rs.next();
            return new FieldData(
                    rs.getInt("fieldID"),
                    rs.getString("fieldName"),
                    rs.getInt("maxLimit")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new FieldData(0, "", 0);
        }
    }

    private void prepareSearchSQLStatement(int searchMethod, String query) {

        try {
            switch (searchMethod) {
                case 0 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE fieldName LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }
                case 1 -> {
                    ps = con.prepareStatement("SELECT * FROM " + tableName + " WHERE maxLimit LIKE ?");
                    ps.setString(1, "%" + query + "%");
                }

                default ->
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<FieldData> searchedFields(String query, int searchMethod) {
        ArrayList<FieldData> fieldsData = new ArrayList<>();
        try {
            prepareSearchSQLStatement(searchMethod, query);

            rs = ps.executeQuery();
            while (rs.next()) {
                fieldsData.add(new FieldData(
                        rs.getInt("fieldID"),
                        rs.getString("fieldName"),
                        rs.getInt("maxLimit")
                ));
            }
            return fieldsData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return fieldsData;
        }
    }

    public void initTableViewModel(ArrayList<FieldData> fieldsData, DefaultTableModel tableModel) {
        for (FieldData fieldData : fieldsData) {
            tableModel.addRow(new String[]{
                fieldData.getTitle(),
                fieldData.getMaxLimit() + ""
            });
        }
    }
}
