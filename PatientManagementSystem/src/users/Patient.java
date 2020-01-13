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

    public String setGender(String Gender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDoB() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setDoB(String DoB) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Patient removePatient(Patient removePatient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Patient addPatient(Patient newPatient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
