
package model;


public class FieldData {
    
    private int ID;
    private String title;
    private int maxLimit;

    public FieldData(int filedID, String title, int maxLimit) {
        this.ID = filedID;
        this.title = title;
        this.maxLimit = maxLimit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int filedID) {
        this.ID = filedID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }
    
    public String toSQL() {
        return "(fieldName, maxLimit) VALUES (?,?)";
    }
    
    
}
