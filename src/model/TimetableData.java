
package model;


public class TimetableData {
    
    private int ID;
    private int fieldID;
    //private int type;
    private int day;
    private int part1;
    private int part2;
    private int part3;
    private int part4;

    public TimetableData(int ID, int fieldID, int day, int part1, int part2, int part3, int part4) {
        this.ID = ID;
        this.fieldID = fieldID;
        //this.type = type;
        this.day = day;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

     public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
     
    /*public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }*/

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPart1() {
        return part1;
    }

    public void setPart1(int part1) {
        this.part1 = part1;
    }

    public int getPart2() {
        return part2;
    }

    public void setPart2(int part2) {
        this.part2 = part2;
    }

    public int getPart3() {
        return part3;
    }

    public void setPart3(int part3) {
        this.part3 = part3;
    }

    public int getPart4() {
        return part4;
    }

    public void setPart4(int part4) {
        this.part4 = part4;
    }
    
    public String toSQL() {
        return "(fieldID, day, part1, part2, part3, part4) VALUES (?,?,?,?,?,?)";
    }
    
    
    
}
