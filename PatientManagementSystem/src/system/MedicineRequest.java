
package system;

import java.io.*;

public class MedicineRequest extends Medicine{
    public static MedicineRequest[] medicineRequests;
    
    public MedicineRequest(String Name, int Stock) {
        super(Name, Stock);
    }
    
    public void removeMedicineRequest(MedicineRequest removeMedicineRequest) {
        int i = 0;
        
        MedicineRequest[] store = new MedicineRequest[medicineRequests.length - 1];
        
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests) {
            if(medicineRequest != removeMedicineRequest) {
                store[i] = medicineRequest;
                i++;
            }
        }
        
        medicineRequests = store;
        saveMedicineRequests();
        getMedicineRequests();
    }
    
    public void addMedicineRequest(MedicineRequest newMedicine) {
        int i;
        
        MedicineRequest[] store = new MedicineRequest[medicineRequests.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = medicineRequests[i];
        
        store[i] = newMedicine;
        medicineRequests = store;
        
        saveMedicineRequests();
        getMedicineRequests();
    }
    
    public static void getMedicineRequests() {
        MedicineRequest[] store = null;
        String filename = "info/medicineRequests.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (MedicineRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("IOException is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        medicineRequests = store;
    }
    
    public static void saveMedicineRequests()
    {
        String filename = "info/medicineRequests.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(medicineRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        }
    }
    
    public static void setMedicineRequests()
    {
        MedicineRequest[] store = {
            new MedicineRequest("Paracetamol", 45),
            new MedicineRequest("Iburprofen", 33)
        };
        
        medicineRequests = store;
    }
}
