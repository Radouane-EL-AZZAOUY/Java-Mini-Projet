package model;

public class TeacherData {

    private int ID;
    private String firstName;
    private String lastName;
    private int age;
    private int specialite;
    private String adresse;
    private char gender;
    private int state;
    private int role;

    public TeacherData(int ID, String firstName, String lastName, int age, int specialite, String adresse, char gender, int status, int role) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.specialite = specialite;
        this.adresse = adresse;
        this.gender = gender;
        this.state = status;
        this.role = role;
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

    public int getSpecialite() {
        return specialite;
    }

    public void setSpecialite(int specialite) {
        this.specialite = specialite;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getState() {
        return state;
    }

    public String getStateList() {
        switch (state) {
            case 1 -> {
                return "PA";
            }
            case 4 -> {
                return "PA-PS";
            }
            case 8 -> {
                return "PA-PS-PES";
            }
            default -> {
                return "";
            }
        }
    }

    public void setState(int state) {
        this.state = state;
    }

    public String toSQL() {
        return "(firstName, lastName, age, specialite, adresse, gender, state, role)"
                + "values"
                + "(?,?,?,?,?,?,?,?)";
    }

}
