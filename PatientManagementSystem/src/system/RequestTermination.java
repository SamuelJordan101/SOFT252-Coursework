package system;

import java.io.*;
import users.*;

public class RequestTermination {

    public static RequestTermination[] RequestTerminations;
    private Patient Patient;

    public RequestTermination(Patient Patient) {
        this.Patient = Patient;
    }

    public Patient getPatient() {
        return Patient;
    }

    public void setPatientID(Patient Patient) {
        this.Patient = Patient;
    }

    public void removeRequestTermination(RequestTermination removeRequestTermination) {
        int i = 0;
        
        RequestTermination[] store = new RequestTermination[RequestTerminations.length - 1];
        
        for(RequestTermination RequestTermination : RequestTerminations) {
            if(RequestTermination != removeRequestTermination) {
                store[i] = RequestTermination;
                i++;
            }
        }
        RequestTerminations = store;
        
        saveRequestTerminations();
        getRequestTerminations();
    }

    public void addRequestTermination(RequestTermination newRequestTermination) {
        int i;
        
        RequestTermination[] store = new RequestTermination[RequestTerminations.length + 1];
        
        for(i=0;i<store.length-1;i++){
            store[i] = RequestTerminations[i];
        }
        
        store[i] = newRequestTermination;
        RequestTerminations = store;
        
        saveRequestTerminations();
        getRequestTerminations();
    }

    public static void getRequestTerminations() {
        RequestTermination[] store = null;
        String filename = "files/RequestTerminations.ser";
        
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            store = (RequestTermination[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex){ 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex){ 
            System.out.println("ClassNotFoundException"); 
        } 
        
        RequestTerminations = store;
    }

    public static void saveRequestTerminations() {
        String filename = "files/RequestTerminations.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(RequestTerminations); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void setRequestTerminations() {
        RequestTermination[] store = {
            new RequestTermination(new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"))
        };
        RequestTerminations = store;
    }
}
