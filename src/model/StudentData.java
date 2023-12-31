package model;

public class StudentData {

    private int ID;
    private String firstName;
    private String lastName;
    private int age;
    private int fieldID;
    private String adresse;
    private char gender;
    private String inscriptionDate;
    private int state;

    public StudentData(int ID, String firstName, String lastName, int age, int fieldID, String adresse, char gender, String inscriptionDate, int state) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.fieldID = fieldID;
        this.adresse = adresse;
        this.gender = gender;
        this.inscriptionDate = inscriptionDate;
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String toSQL() {
        return "(firstName, lastName, age, field, adresse, gender,inscriptionDate, state)"
                + "values"
                + "(?,?,?,?,?,?,?,?)";
    }

}
