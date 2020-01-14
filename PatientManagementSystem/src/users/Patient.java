
package users;

import system.Notification;

public class Patient extends User {
    
    public static Patient[] patients;
    private String Gender;
    private String DOB;

    public Patient(String ID, String Password, String Forename, String Surname, String Address, Notification Message, String Gender, String DOB) {
        super(ID, Password, Forename, Surname, Address, Message);
        this.Gender = Gender;
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    public void removePatient(Patient removePatient)
    {
        int i = 0;
        
        Patient[] store = new Patient[patients.length - 1];
        
        for(Patient patient : patients) {
            if(patient != removePatient) {
                store[i] = patient;
                i++;
            }
        }
        patients = store;
        removeUser(removePatient);
    }
    
    public void addPatient(Patient newPatient){
        int i;
        
        Patient[] store = new Patient[patients.length + 1];
        
        for(i = 0;i < store.length - 1; i++)
            store[i] = patients[i];
        
        store[i] = newPatient;
        patients = store;
        
        addUser(newPatient);
        getUsers();
    }
}
