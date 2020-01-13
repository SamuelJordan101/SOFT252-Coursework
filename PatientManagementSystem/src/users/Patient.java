package users;

public class Patient extends User {

    public static Patient[] patients;

    private String Gender;

    private String DoB;

    public Patient(String ID, String Firstname, String Lastname, String Password, String Address, Notification Message, String Gender, String DoB) {
    }

    public String getGender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGender(String Gender) {
    }

    public String getDoB() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDoB(String DoB) {
    }

    public void removePatient(Patient removePatient) {
    }

    public void addPatient(Patient newPatient) {
    }
}
