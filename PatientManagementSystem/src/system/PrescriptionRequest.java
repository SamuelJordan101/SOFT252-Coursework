
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
    
    public static void savePrescriptionRequests()
    {
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
    
    public static void setPrescriptionRequests()
    {
        PrescriptionRequest[] store = {
            new PrescriptionRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", null, "F", "13/06/1998"),
                "Health increasing", new Medicine("Penicillin", 3), 5, "1 EVERY 12 HOURS"),
            new PrescriptionRequest(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                "Healthy individual", new Medicine("Penicillin", 3), 5, "1 EVERY 12 HOURS")
        };
        
        prescriptionRequests = store;
    }
}
