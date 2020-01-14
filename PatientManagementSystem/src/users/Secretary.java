package users;

import system.Notification;

public class Secretary extends User {
    public static Secretary[] secretarys;
    
    public Secretary(String ID, String Password, String Forename, String Surname, String Address, Notification Message) {
        super(ID, Password, Forename, Surname, Address, Message);
    }
    
    public void addSecretary(Secretary newSecretary) {
        Secretary[] store = new Secretary[secretarys.length + 1];
        int i;
        
        for(i=0;i<store.length-1;i++)
            store[i] = secretarys[i];
        
        store[i] = newSecretary;
        secretarys = store;
        
        addUser(newSecretary);
    }
}

