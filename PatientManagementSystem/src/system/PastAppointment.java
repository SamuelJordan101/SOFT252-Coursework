
package system;

import java.io.*;
import users.*;

public class PastAppointment extends Appointment{
    public static PastAppointment[] pastAppointments;
    private Prescription pastPrescription;
    
    public PastAppointment(Doctor Doctor, Patient Patient, String Date, Prescription PastPrescription) {
        super(Doctor, Patient, Date);
        this.pastPrescription = PastPrescription;
    }   

    public Prescription getPastPrescription() {
        return pastPrescription;
    }

    public void setPastPrescription(Prescription pastPrescription) {
        this.pastPrescription = pastPrescription;
    }
    
    public void addPastAppointment(PastAppointment newPastAppointment) {
        int i;
        
        PastAppointment[] store = new PastAppointment[pastAppointments.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = pastAppointments[i];
        
        store[i] = newPastAppointment;
        pastAppointments = store;
        
        savePastAppointments();
        getPastAppointments();
    }
    
    public static void getPastAppointments() {
        //gets past appointments from file
        
        PastAppointment[] store = null;
        String filename = "info/pastAppointments.ser";
        
        try { 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (PastAppointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error  is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        pastAppointments = store;
    }
    
    public static void savePastAppointments() {
        //saves past appointments to file
        
        String filename = "info/pastAppointments.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(pastAppointments); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void setPastAppointments() {
        //sets default data
        
        PastAppointment[] store = {
            new PastAppointment(
                new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "17/01/2020 12:05",
                new Prescription(
                    new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                    new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Propranalol", 4), 1, "1 every 4 hours")),
            new PastAppointment(
                new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                "13/01/2020 14:00",
                new Prescription(
                    new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
                    new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                    "Health dramatically decreasing.\nRecommend Dosage Increase", new Medicine("Nifedipine", 25), 1, "5 a day")),
            new PastAppointment(
                new Doctor("D003","5f4dcc3b5aa765d61d8327deb882cf99","Fraser","Mcdodal","94 Dalphos,\nBristol,\nBR73RD",null),
                new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "14/01/2020 11:30",
                new Prescription(
                    new Doctor("D003","5f4dcc3b5aa765d61d8327deb882cf99","Fraser","Mcdodal","94 Dalphos,\nBristol,\nBR73RD",null),
                    new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                    "Condition Impproving.\nRecommend dosage decrease.", new Medicine("Omeprazole", 70), 2, "1 every day"))
        };
        
        pastAppointments = store;
    }
}
