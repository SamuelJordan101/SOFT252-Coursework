
package system;

import java.io.*;

public class AccountRequest implements Serializable {
    
    public static AccountRequest[] accountRequests;
    private String Password;
    private String Forename;
    private String Surname;
    private String Address;
    private String Gender;
    private String DOB;

    public AccountRequest(String Password, String Forename, String Surname, String Address, String Gender, String DOB) {
        this.Password = Password;
        this.Forename = Forename;
        this.Surname = Surname;
        this.Address = Address;
        this.Gender = Gender;
        this.DOB = DOB;
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
    
    public void addAccountRequest(AccountRequest newAccountRequest) {
        int i;
        
        AccountRequest[] store = new AccountRequest[accountRequests.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = accountRequests[i];
        
        store[i] = newAccountRequest;
        accountRequests = store;
        
        saveAccountRequests();
        getAccountRequests();
    }
    
    public void removeAccountRequest(AccountRequest removeAccountRequest) {
        int i = 0;
        
        AccountRequest[] store = new AccountRequest[accountRequests.length - 1];
        
        for(AccountRequest accountRequest : AccountRequest.accountRequests) {
            if(accountRequest != removeAccountRequest) {
                store[i] = accountRequest;
                i++;
            }
        }
        
        accountRequests = store;
        
        saveAccountRequests();
        getAccountRequests();
    }
    
    public static void getAccountRequests()
    {
        AccountRequest[] store = null;
        String filename = "info/accountRequests.ser";
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (AccountRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        accountRequests = store;
    }
    
    public static void saveAccountRequests()
    {
        String filename = "info/accountRequests.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(accountRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void setAccountRequests()
    {
        AccountRequest[] store = {
            new AccountRequest("5f4dcc3b5aa765d61d8327deb882cf99","George", "Humphries", "91 New George St,\nPlymouth,\nPL1 1RQ", "M", "16/05/1994"),
            new AccountRequest("5f4dcc3b5aa765d61d8327deb882cf99", "Emilie", "Huntley", "14-16,\nUnion St,\nPlymouth,\nPL1 2SR", "F", "13/11/1998")
        };
        
        accountRequests = store;
    }
}
