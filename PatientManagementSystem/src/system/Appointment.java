package system;

import users.*;
import java.io.*;

public class Appointment {

    public static Appointment[] appointments;
    private Doctor Doctor;
    private Patient Patient;
    private String Date;

    public Appointment(Doctor Doctor, Patient Patient, String Date) {
        this.Doctor = Doctor;
        this.Patient = Patient;
        this.Date = Date;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public Doctor setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public Patient getPatient() {
        return Patient;
    }

    public Patient setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public String getDate() {
        return Date;
    }

    public String setDate(String Date) {
        this.Date = Date;
    }

    public Appointment addAppointment(Appointment newAppointment) {
        int i;
        
        Appointment[] store = new Appointment[appointments.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = appointments[i];
        
        store[i] = newAppointment;
        appointments = store;
        
        saveAppointments();
        getAppointments();
    }

    public Appointment removeAppointment(Appointment removeAppointment) {
        int i = 0;
        
        Appointment[] store = new Appointment[appointments.length - 1];
        
        for(Appointment appointment : Appointment.appointments){
            if(appointment != removeAppointment){
                store[i] = appointment;
                i++;
            }
        }
        
        appointments = store;
        
        saveAppointments();
        getAppointments();
    }

    public static void getAppointments() {
        Appointment[] store = null;
        String filename = "files/appointments.ser";
        try
        {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Appointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex){ 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        Appointment.appointments = store;
    }

    public static void saveAppointments() {
        String filename = "files/appointments.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(appointments); 
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void setAppointments() {
        Appointment[] store = {
            new Appointment(
                    new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                    new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                    "3/01/2020 12:45"),
            new Appointment(
                    new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                    new Patient("P003","Imogen","Jones","password","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                    "2/01/2020 16:05"),
            new Appointment(
                    new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null), 
                    new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                    "8/01/2020 13:00"),
        };
        
        appointments = store;
    }
}
