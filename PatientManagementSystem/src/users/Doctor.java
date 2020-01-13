package users;

import system.Notification;

public class Doctor extends User {

    public static Doctor[] doctors;

    public Doctor(String ID, String Firstname, String Lastname, String Password, String Address, Notification Message) {
        super(ID,Firstname,Lastname,Password,Address,Message);
    }

    public void addDoctor(Doctor newDoctor) {
        int i;
        
        Doctor[] store = new Doctor[doctors.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = doctors[i];
        
        store[i] = newDoctor;
        doctors = store;
        
        addUser(newDoctor);
    }
}
