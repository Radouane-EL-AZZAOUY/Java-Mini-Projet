
package model;


public class NoteData {
    
    private int ID;
    private int studentID;
    private int examID;
    private float note;

    public NoteData(int ID, int studentID, int examID, float note) {
        this.studentID = studentID;
        this.examID = examID;
        this.note = note;
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
    
    public String toSQL() {
        return "(studentID, examID, note) VALUES (?,?,?)";
    }
    
}
