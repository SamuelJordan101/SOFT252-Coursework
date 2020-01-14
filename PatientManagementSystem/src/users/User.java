
package users;

import java.io.*;
import system.Notification;

public class User implements Serializable{
    public static User[] users;
    public static User loggedUser;
    private String ID;
    private String Password;
    private String Forename;
    private String Surname;
    private String Address;
    private Notification Notification;

    public User(String ID, String Password, String Forename, String Surname, String Address, Notification Notification) {
        this.ID = ID;
        this.Password = Password;
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
        this.Notification = Notification;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String Forename) {
        this.Forename = Forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Notification getNotification() {
        return Notification;
    }

    public void setNotification(Notification Notification) {
        this.Notification = Notification;
    }
    
    public void removeUser(User removeUser)
    {
        int i = 0;
        
        User[] store = new User[users.length - 1];
        
        for(User user : users) {
            if(user != removeUser) {
                store[i] = user;
                i++;
            }
        }
        users = store;
        
        saveUsers();
    }
    
    public void addUser(User newUser)
    {
        int i;
        
        User[] store = new User[users.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = users[i];
        
        store[i] = newUser;
        users = store;
        
        saveUsers();
    }

    public static void saveUsers()
    {
        String filename = "info/users.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(users); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex){ 
            System.out.println("Error is caught"); 
        } 
    }
    
    public static void getUsers()
    {
        User[] store = null;
        String filename = "info/users.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (User[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        User.users = store;
        
        sortUsers();
    }
    
    private static void sortUsers()
    {
        int numPatients = 0, numDoctors = 0, numAdmins = 0, numSecretarys = 0, IP = 0, ID = 0, IA = 0, IS = 0;
        
        for(User user : User.users){
            if(user instanceof Patient)
                numPatients++;
            else if(user instanceof Doctor)
                numDoctors++;
            else if(user instanceof Admin)
                numAdmins++;
            else if(user instanceof Secretary)
                numSecretarys++;
        }
        
        Patient[] patients = new Patient[numPatients];
        Doctor[] doctors = new Doctor[numDoctors];
        Admin[] admins = new Admin[numAdmins];
        Secretary[] secretarys = new Secretary[numSecretarys];
        
        for(User user : User.users)
        {
            if(user instanceof Patient) {
                patients[IP] = (Patient) user;
                IP++;
            }
            else if(user instanceof Doctor) {
                doctors[ID] = (Doctor) user;
                ID++;
            }
            else if(user instanceof Admin) {
                admins[IA] = (Admin) user;
                IA++;
            }
            else if(user instanceof Secretary){
                secretarys[IS] = (Secretary) user;
                IS++;
            }
        }
        
        Patient.patients = patients;
        Doctor.doctors = doctors;
        Admin.admins = admins;
        Secretary.secretarys = secretarys;
    }
    
    private static void showUsers()
    {
        for(Patient patient : Patient.patients)
            System.out.println(patient.getID());
        
        for(Doctor doctor : Doctor.doctors)
            System.out.println(doctor.getID());
        
        for(Admin admin : Admin.admins)
            System.out.println(admin.getID());
        
        for(Secretary secretary : Secretary.secretarys)
            System.out.println(secretary.getID());
    }
    
    public static void setUsers()
    {
        User[] temp = {
            new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
            new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
            new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
            new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
            new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
            new Doctor("D003","5f4dcc3b5aa765d61d8327deb882cf99","Fraser","Mcdodal","94 Dalphos,\nBristol,\nBR73RD",null),
            new Admin("A001","5f4dcc3b5aa765d61d8327deb882cf99","Samuel","Jordan","44 Merrifieids,\nTaunton,\nTA41PE",null),
            new Secretary("S001","5f4dcc3b5aa765d61d8327deb882cf99","Katie","Stones","46 Telphos,\nPortsmouth,\nPO23PP",null)
        };
        
        users = temp;
    }
}
