package users;

import system.Notification;

public class Patient extends User {

    public static Patient[] patients;

    private String Gender;

    private String DoB;

    public Patient(String ID, String Firstname, String Lastname, String Password, String Address, Notification Message, String Gender, String DoB) {
        super(ID,Firstname,Lastname,Password,Address,Message,Gender,DoB);
        this.Gender = Gender;
        this.DoB = DoB;
    }

    public String getGender() {
        return Gender;
    }

    public String setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDoB() {
        return DoB;
    }

    public String setDoB(String DoB) {
        this.DoB = DoB;
    }

    public Patient removePatient(Patient removePatient) {
        int i = 0;
        
        Patient[] store = new Patient[patients.length - 1];
        
        for(Patient patient : patients){
            if(patient != removePatient){
                store[i] = patient;
                i++;
            }
        }
        patients = store;
        removeUser(removePatient);
    }

    public Patient addPatient(Patient newPatient) {
        int i;
        
        Patient[] store = new Patient[patients.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = patients[i];
        
        store[i] = newPatient;
        patients = store;
        
        addUser(newPatient);
        getUsers();
    }
}
