package users;

import java.io.Serializable;
import java.io.*;

public class User implements Serializable{

    public static User[] users;

    public static User loggedUser;

    private String ID;

    private String Firstname;

    private String Lastname;

    private String Password;

    private String Address;

    private Notification Notification;

    public User(String ID, String Firstname, String Lastname, String Password, String Address, Notification Notification) {
        this.ID = ID;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Password = Password;
        this.Address = Address;
        this.Notification = Notification;
    }

    public String getID() {
        return ID;
    }

    public String setID(String ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String setPassword() {
        return Password;
    }

    public String setPassword(String Password) {
        this.Password = Password;
    }

    public String getAddress() {
        return Address;
    }

    public String setAddress(String Address) {
        this.Address = Address;
    }

    public Notification getNotification() {
        Return Notification;
    }

    public Notification setNotification(Notification Notification) {
        this.Notification = Notification;
    }

    public User removeUser(User removeUser) {
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

    public User addUser(User newUser) {
        int i;
        
        User[] store = new User[users.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = users[i];
        
        store[i] = newUser;
        users = store;
        
        saveUsers();
    }

    public static void saveUsers() {
        String filename = "files/users."; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(users); 
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
    }

    public static void getUsers() {
        User[] store = null;
        String filename = "files/users.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (User[])in.readObject(); 
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        
        User.users = store;
        sortUsers();
    }

    private static void sortUsers() {
        int  Admins = 0, Doctors = 0, Patients = 0, Secretarys = 0,  A = 0, D = 0, P = 0, S = 0;
        
        for(User user : User.users){
            if(user instanceof Admin)
                Admins++;
            else if(user instanceof Doctor)
                Doctors++;
            else if(user instanceof Patient)
                Patients++;
            else if(user instanceof Secretary)
                Secretarys++;
        }
        
        Admin[] admins = new Admin[Admins];
        Doctor[] doctors = new Doctor[Doctors];
        Patient[] patients = new Patient[Patients];
        Secretary[] secretarys = new Secretary[Secretarys];
        
        for(User user : User.users){
            if(user instanceof Patient){
                patients[P] = (Patient) user;
                P++;
            }
            else if(user instanceof Doctor){
                doctors[D] = (Doctor) user;
                D++;
            }
            else if(user instanceof Admin){
                admins[A] = (Admin) user;
                A++;
            }
            else if(user instanceof Secretary){
                secretarys[S] = (Secretary) user;
                S++;
            }
        }
        
        Patient.patients = patients;
        Doctor.doctors = doctors;
        Admin.admins = admins;
        Secretary.secretarys = secretarys;
    }

    private static void showUsers() {
        for(Patient patient : Patient.patients)
            System.out.println(patient.getID());
        for(Doctor doctor : Doctor.doctors)
            System.out.println(doctor.getID());
        for(Admin admin : Admin.admins)
            System.out.println(admin.getID());
        for(Secretary secretary : Secretary.secretarys)
            System.out.println(secretary.getID());
    }

    public static void setUsers() {
        User[] store = {
            new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
            new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
            new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
            new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
            new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
            new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
            new Admin("A001","Samuel","Jordan","password","44 Merrifieids,\nTaunton,\nTA41PE",null),
            new Secretary("S001","Katie","Stones","password","46 Telphos,\nPortsmouth,\nPO23PP",null)
        };
        
        users = store;
    }
}
