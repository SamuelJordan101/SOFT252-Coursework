package system;

import java.io.*;
import users.*;

public class PastAppointment extends Appointment {

    public static PastAppointment[] pastAppointments;
    private Prescription pastPrescription;

    public PastAppointment(Doctor Doctor, Patient Patient, String Date, Prescription PastPrescription) {
        super(Doctor, Patient, Date);
        this.pastPrescription = PastPrescription;
    }

    public Prescription getPastPrescription() {
        return pastPrescription;
    }

    public Prescription setPastPrescription(Prescription pastPrescription) {
        this.pastPrescription = pastPrescription;
    }

    public PastAppointment addPastAppointment(PastAppointment newPastAppointment) {
        int i;
        
        PastAppointment[] store = new PastAppointment[pastAppointments.length + 1];
        
        for(i = 0;i < store.length - 1; i++)
            store[i] = pastAppointments[i];
        
        store[i] = newPastAppointment;
        pastAppointments = store;
        
        savePastAppointments();
        getPastAppointments();
    }

    public static void getPastAppointments() {
        PastAppointment[] store = null;
        String filename = "files/pastAppointments.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (PastAppointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        
        pastAppointments = store;
    }

    public static void savePastAppointments() {
        String filename = "files/pastAppointments.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(pastAppointments); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void setPastAppointments() {
        PastAppointment[] store = {
            new PastAppointment(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "13/01/2020 11:00",
                new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Better, Less Needed", new Medicine("Amoxicillin", 5), 2, "1 EVERY 24 HOURS")),
            new PastAppointment(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "09/01/2020 11:45",
                new Prescription(
                    new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Worse, More Needed", new Medicine("Propranalol", 30), 5, "1 EVERY 48 HOURS")),
            new PastAppointment(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "10/01/2020 16:15",
                new Prescription(
                    new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                    new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                    "Worse, More Needed", new Medicine("Nifedipine", 25), 1, "4 EVERY 12 HOURS"))
        };
        
        pastAppointments = store;
    }
}
