package system;

import java.io.*;
import users.*;

public class Prescription implements Serializable {

    public static Prescription[] prescriptions;

    private Doctor Doctor;
    private Patient Patient;
    private String Notes;
    private Medicine Medicine;
    private int Quantity;
    private String Dosage;

    public Prescription(Doctor Doctor, Patient Patient, String Notes, Medicine Medicine, int Quantity, String Dosage) {
        this.Doctor = Doctor;
        this.Patient = Patient;
        this.Notes = Notes;
        this.Medicine = Medicine;
        this.Quantity = Quantity;
        this.Dosage = Dosage;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public Patient getPatient() {
        return Patient;
    }

    public void setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public Medicine getMedicine() {
        return Medicine;
    }

    public void setMedicine(Medicine Medicine) {
        this.Medicine = Medicine;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String Dosage) {
        this.Dosage = Dosage;
    }

    public void removePrescription(Prescription removePrescription) {
        int i = 0;
        
        Prescription[] store = new Prescription[prescriptions.length - 1];
        
        for(Prescription prescription : Prescription.prescriptions) {
            if(prescription != removePrescription) {
                store[i] = prescription;
                i++;
            }
        }
        
        prescriptions = store;
        
        savePrescriptions();
        getPrescriptions();
    }

    public void addPrescription(Prescription newPrescription) {
        int i;
        
        Prescription[] store = new Prescription[prescriptions.length + 1];
        
        for(i = 0;i < store.length - 1; i++) {
            store[i] = prescriptions[i];
        }
        
        store[i] = newPrescription;
        prescriptions = store;
        
        savePrescriptions();
        getPrescriptions();
    }

    public static void savePrescriptions() {
        String filename = "data/prescriptions.ser"; 
           
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(prescriptions); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void getPrescriptions() {
        Prescription[] store = null;
        String filename = "files/prescriptions.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Prescription[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        prescriptions = store;
    }

    public static void setPrescriptions(){
        Prescription[] store = {
            new Prescription(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "Better, Less Needed", new Medicine("Amoxicillin", 5), 2, "1 EVERY 24 HOURS"),
            new Prescription(
                new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "Worse, More Needed", new Medicine("Propranalol", 30), 5, "1 EVERY 48 HOURS"),
            new Prescription(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "Worse, More Needed", new Medicine("Nifedipine", 25), 1, "4 EVERY 12 HOURS")
        };
        
        prescriptions = store;
    }
}
