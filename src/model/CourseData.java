
package model;


public class CourseData {
    
    private int ID;
    private String title;
    private int duration;
    private int fieldID;
    private int teacherID;

    public CourseData(int ID, String title, int duration, int fieldID, int teacherID) {
        this.ID = ID;
        this.title = title;
        this.duration = duration;
        this.fieldID = fieldID;
        this.teacherID = teacherID;
    }
    
    public CourseData(int ID, String title, int duration) {
        this.ID = ID;
        this.title = title;
        this.duration = duration;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
        return "(title, duration) VALUES (?,?)";
    }
    
}
