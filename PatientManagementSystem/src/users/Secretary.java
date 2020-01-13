package users;

import system.Notification;

public class Secretary extends User {

    public static Secretary[] secretarys;

    public Secretary(String ID, String Firstname, String Lastname, String Password, String Address, Notification Message) {
        super(ID,Firstname,Lastname,Password,Address,Message);
    }

    public Secretary addSecretary(Secretary newSecretary) {
        int i;
        
        Secretary[] store = new Secretary[secretarys.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = secretarys[i];
        
        store[i] = newSecretary;
        secretarys = store;
        
        addUser(newSecretary);
    }
}
