
package system;

import java.io.*;

public class Medicine implements Serializable{
    
    public static Medicine[] medicines;
    private String Name;
    private int Stock;

    public Medicine(String Name, int Stock) {
        this.Name = Name;
        this.Stock = Stock;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    
    public void addMedicine(Medicine newMedicine) {
        int i;
        
        Medicine[] store = new Medicine[medicines.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = medicines[i];
        
        store[i] = newMedicine;
        medicines = store;
        
        saveMedicine();
        getMedicine();
    }
    
    public static void getMedicine() {
        //gets medicines from file
        
        Medicine[] store = null;
        String filename = "info/medicines.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Medicine[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        medicines = store;
    }
    
    public static void saveMedicine() {
        //saves medicines to file
        
        String filename = "info/medicines.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(medicines); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void setMedicine() {
        //sets default data
        
        Medicine[] store = {
            new Medicine("Propranalol", 4),
            new Medicine("Paracetamol", 15),
            new Medicine("Amoxicillin", 50),
            new Medicine("Omeprazole", 70),
            new Medicine("Cetirizine Hydrochloride", 30),
            new Medicine("Propranalol", 30),
            new Medicine("Citalopram", 15),
            new Medicine("Nifedipine", 25),
        };
        
        medicines = store;
    }
}
