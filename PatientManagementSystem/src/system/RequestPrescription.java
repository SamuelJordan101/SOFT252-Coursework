package system;

import users.*;
import java.io.*;

public class RequestPrescription extends Prescription {

    public static RequestPrescription[] RequestPrescriptions;

    public RequestPrescription(Doctor Doctor, Patient Patient, String Notes, Medicine Medicine, int Quantity, String Dosage) {
        super(Doctor, Patient, Notes, Medicine, Quantity, Dosage);
    }

    public void removeRequestPrescription(RequestPrescription removeRequestPrescription) {
        int i = 0;
        
        RequestPrescription[] store = new RequestPrescription[RequestPrescriptions.length - 1];
        
        for(RequestPrescription prescriptionRequest : RequestPrescription.RequestPrescriptions) {
            if(prescriptionRequest != removeRequestPrescription){
                store[i] = prescriptionRequest;
                i++;
            }
        }
        RequestPrescriptions = store;
        
        saveRequestPrescriptions();
        getRequestPrescriptions();
    }

    public void addRequestPrescription(RequestPrescription newRequestPrescription) {
        int i;
        
        RequestPrescription[] store = new RequestPrescription[RequestPrescriptions.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = RequestPrescriptions[i];
        
        store[i] = newRequestPrescription;
        RequestPrescriptions = store;
        
        saveRequestPrescriptions();
        getRequestPrescriptions();
    }

    public static void getRequestPrescriptions() {
        RequestPrescription[] store = null;
        String filename = "files/RequestPrescriptions.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (RequestPrescription[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        RequestPrescriptions = store;
    }

    public static void saveRequestPrescriptions() {
        String filename = "data/RequestPrescriptions.ser"; 
            
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(RequestPrescriptions); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        }
    }

    public static void setRequestPrescriptions() {
        RequestPrescription[] store = {
            new RequestPrescription(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "Worse, More Needed", new Medicine("Amoxicillin", 5), 2, "1 EVERY 24 HOURS"),
            new RequestPrescription(
                new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "Better, Less Needed", new Medicine("Propranalol", 30), 5, "1 EVERY 48 HOURS"),
        };
        
        RequestPrescriptions = store;
    }
}

