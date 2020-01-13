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
        String filename = "data/medicines.ser";
        
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
        String filename = "data/medicines.ser"; 
          
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
        Medicine[] temp = {
            new Medicine("Chlorpromazine", 5),
            new Medicine("Polio Vaccine", 7),
            new Medicine("Oral Contraceptives", 3),
            new Medicine("Penicillin", 3),
            new Medicine("Beta Blocker", 15),
            new Medicine("Beta2 Agonists", 25),
            new Medicine("Tamoxifen", 3),
            new Medicine("Immunosuppressants", 27),
            new Medicine("HIV/AIDS Antiretrovirals", 18),
            new Medicine("MMR Vaccine", 11)
        };
        
        medicines = temp;
    }
}
