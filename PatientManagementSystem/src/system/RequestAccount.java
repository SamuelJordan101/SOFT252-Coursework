package system;

import java.io.*;

public class RequestAccount implements Serializable {

    public static RequestAccount[] RequestAccounts;
    private String Firstname;
    private String Lastname;
    private String Password;
    private String Address;
    private String Gender;
    private String DoB;

    public RequestAccount(String Firstname, String Lastname, String Password, String Address, String Gender, String DoB) {
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Password = Password;
        this.Address = Address;
        this.Gender = Gender;
        this.DoB = DoB;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;
    }

    public void addRequestAccount(RequestAccount newRequestAccount) {
        int i;
        
        RequestAccount[] store = new RequestAccount[RequestAccounts.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = RequestAccounts[i];
        
        store[i] = newRequestAccount;
        RequestAccounts = store;
        
        saveRequestAccounts();
        getRequestAccounts();
    }

    public void removeRequestAccount(RequestAccount removeRequestAccount) {
        int i = 0;
        
        RequestAccount[] store = new RequestAccount[RequestAccounts.length - 1];
        
        for(RequestAccount RequestAccount : RequestAccount.RequestAccounts) {
            if(RequestAccount != removeRequestAccount) {
                store[i] = RequestAccount;
                i++;
            }
        }
        RequestAccounts = store;
        
        saveRequestAccounts();
        getRequestAccounts();
    }

    public static void getRequestAccounts() {
        RequestAccount[] store = null;
        String filename = "data/RequestAccounts.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (RequestAccount[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        RequestAccounts = store;
    }

    public static void saveRequestAccounts() {
        String filename = "files/RequestAccounts.ser"; 
          
        try
        {    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            out.writeObject(RequestAccounts); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("IOException is caught: " +  ex); 
        } 
    }

    public static void setRequestAccounts() {
        RequestAccount[] store = {
            new RequestAccount("password","Jeff", "Gibbs", "Tinyhouse,\nPlyouth,\nPL63TL", "M", "17/07/1984"),
            new RequestAccount("password", "Jane", "Handle", "Empire,\nLondon,\nLN25DN", "F", "09/06/2000")
        };
        
        RequestAccounts = store;
    }
}
