package system;

import java.io.*;

public class Medicine {

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

    public String setName(String Name) {
        this.Name = Name;
    }

    public int getStock() {
        return Stock;
    }

    public int setStock(int Stock) {
        this.Stock = Stock;
    }

    public Medicine addMedicine(Medicine newMedicine) {
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
        Medicine[] store = null;
        String filename = "files/medicines.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Medicine[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        
        medicines = store;
    }

    public static void saveMedicine() {
        String filename = "files/medicines.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(medicines); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void setMedicine() {
        Medicine[] store = {
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
