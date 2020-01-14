
package users;

import system.Notification;

public class Admin extends User {
    public static Admin[] admins;

    public Admin(String ID, String Password, String Forename, String Surname, String Address, Notification Message) {
        super(ID, Password, Forename, Surname, Address, Message);
    }
    
    public void addAdmin(Admin newAdmin) {
        int i;
        
        Admin[] store = new Admin[admins.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = admins[i];
        
        store[i] = newAdmin;
        admins = store;
        
        addUser(newAdmin);
    }
}
