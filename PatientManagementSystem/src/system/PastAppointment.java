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
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "23/11/2019 10:30",
                new Prescription(
                    new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                    new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                    "Health dramatically decreasing.\nRecommend Dosage Increase", new Medicine("Chlorpromazine", 5), 8, "WHENEVER NEEDED")),
            new PastAppointment(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                "24/11/2019 11:00",
                new Prescription(
                    new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                    new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                    "Condition Impproving.\nRecommend dosage decrease.", new Medicine("Tamoxifen", 3), 9, "1 EVERY 2 DAYS")),
            new PastAppointment(
                new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "26/11/2019 13:15",
                new Prescription(
                    new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                    new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                    "Condition Worsening.\nRecommend Dosage Increase.", new Medicine("Beta Blocker", 15), 25, "1 A DAY"))
        };
        
        pastAppointments = store;
    }
}
