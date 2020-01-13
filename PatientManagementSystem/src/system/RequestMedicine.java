package system;

import java.io.*;

public class RequestMedicine extends Medicine {

    public static RequestMedicine[] RequestMedicines;

    public RequestMedicine(String Name, int Stock) {
        super(Name, Stock);
    }

    public RequestMedicine removeRequestMedicine(RequestMedicine removeRequestMedicine) {
        int i = 0;
        
        RequestMedicine[] store = new RequestMedicine[RequestMedicines.length - 1];
        
        for(RequestMedicine RequestMedicine : RequestMedicine.RequestMedicines) {
            if(RequestMedicine != removeRequestMedicine) {
                store[i] = RequestMedicine;
                i++;
            }
        }
        
        RequestMedicines = store;
        saveRequestMedicines();
        getRequestMedicines();
    }

    public RequestMedicine addRequestMedicine(RequestMedicine newMedicine) {
        int i;
        
        RequestMedicine[] store = new RequestMedicine[RequestMedicines.length + 1];
        
        for(i = 0;i < store.length - 1; i++) {
            store[i] = RequestMedicines[i];
        }
        
        store[i] = newMedicine;
        RequestMedicines = store;
        
        saveRequestMedicines();
        getRequestMedicines();
    }

    public static void getRequestMedicines() {
        RequestMedicine[] store = null;
        String filename = "files/RequestMedicines.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (RequestMedicine[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        RequestMedicines = store;
    }

    public static void saveRequestMedicines() {
        String filename = "files/RequestMedicines.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(RequestMedicines); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        }
    }

    public static void setRequestMedicines() {
        RequestMedicine[] store = {
            new RequestMedicine("Amoxicillin", 50),
            new RequestMedicine("Omeprazole", 70)
        };
        
        RequestMedicines = store;
    }
}
