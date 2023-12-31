
package model;


public class ExamData {
    
    private int ID;
    private String description;
    private String date;
    private float duration;
    private int fieldID;
    private int teacherID;

    public ExamData(int examID, String description, String date, float duration, int fieldID, int teacherID) {
        this.ID = examID;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.fieldID = fieldID;
        this.teacherID = teacherID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
    
    public int getTeacherID() {
        return teacherID;
    }
    
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
    
    public String toSQL() {
        return "(description, date, duration, fieldID, teacherID) VALUES (?,?,?,?,?)";
    }
    
}
