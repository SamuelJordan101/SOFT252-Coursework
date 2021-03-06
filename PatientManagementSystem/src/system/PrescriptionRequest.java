
package system;

import users.*;
import java.io.*;

public class PrescriptionRequest extends Prescription {
    public static PrescriptionRequest[] prescriptionRequests;
    
    public PrescriptionRequest(Doctor Doctor, Patient Patient, String Notes, Medicine Medicine, int Quantity, String Dosage) {
        super(Doctor, Patient, Notes, Medicine, Quantity, Dosage);
    }
    
    public void removePrescriptionRequest(PrescriptionRequest removePrescriptionRequest) {
        int i = 0;
        
        PrescriptionRequest[] store = new PrescriptionRequest[prescriptionRequests.length - 1];
        
        for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests) {
            if(prescriptionRequest != removePrescriptionRequest) {
                store[i] = prescriptionRequest;
                i++;
            }
        }
    
        prescriptionRequests = store;
        
        savePrescriptionRequests();
        getPrescriptionRequests();
    }
    
    public void addPrescriptionRequest(PrescriptionRequest newPrescriptionRequest){
        int i;
        
        PrescriptionRequest[] store = new PrescriptionRequest[prescriptionRequests.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = prescriptionRequests[i];
        
        store[i] = newPrescriptionRequest;
        prescriptionRequests = store;
        
        savePrescriptionRequests();
        getPrescriptionRequests();
    }
    
    public static void getPrescriptionRequests() {
        //gets prescription requests from file
        
        PrescriptionRequest[] store = null;
        String filename = "info/prescriptionRequests.ser";
        
        try {
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (PrescriptionRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        prescriptionRequests = store;
    }
    
    public static void savePrescriptionRequests() {
        //saves prescription requests to file
        
        String filename = "info/prescriptionRequests.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(prescriptionRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        }
    }
    
    public static void setPrescriptionRequests() {
        //sets default data
        
        PrescriptionRequest[] store = {
            new PrescriptionRequest(
                new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "Getting better", new Medicine("Omeprazole", 70), 1, "1 a day"),
            new PrescriptionRequest(
                new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "Worsening", new Medicine("Paracetamol", 15), 2, "1 every 4 hours")
        };
        
        prescriptionRequests = store;
    }
}
