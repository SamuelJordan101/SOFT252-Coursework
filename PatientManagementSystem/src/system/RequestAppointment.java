package system;

import users.*;
import java.io.*;

public class RequestAppointment extends Appointment {

    public static RequestAppointment[] appointmentRequests;

    public RequestAppointment(Doctor Doctor, users.Patient Patient, String Date) {
        super(Doctor, Patient, Date);
    }

    public void addRequestAppointment(RequestAppointment newRequestAppointment) {
        int i;
        
        RequestAppointment[] store = new RequestAppointment[appointmentRequests.length + 1];
        
        for(i = 0;i < store.length - 1; i++)
        {
            store[i] = appointmentRequests[i];
        }
        
        store[i] = newRequestAppointment;
        appointmentRequests = store;
        
        saveRequestAppointments();
    }

    public void removeRequestAppointment(RequestAppointment removeRequestAppointment) {
        int i = 0;
        
        RequestAppointment[] store = new RequestAppointment[appointmentRequests.length - 1];
        
        for(RequestAppointment appointmentRequest : appointmentRequests) {
            if(appointmentRequest != removeRequestAppointment) {
                store[i] = appointmentRequest;
                i++;
            }
        }
        
        appointmentRequests = store;
        saveRequestAppointments();
    }

    public static void saveRequestAppointments() {
        String filename = "files/RequestAppointments.ser"; 
          
        try {   
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(appointmentRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void getRequestAppointments() {
        RequestAppointment[] store = null;
        String filename = "data/appointmentRequests.ser";
        
        try {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            store = (RequestAppointment[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("IOException is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        appointmentRequests = store;
    }

    public static void setRequestAppointments() {
        RequestAppointment[] temp = {
            new RequestAppointment(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P001","Sam","Jordan","password","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "13/01/2020 11:00"),
            new RequestAppointment(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                "09/01/2020 11:45"),
            new RequestAppointment(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P002","Owen","Howarth","password","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                "10/01/2020 16:15"),
        };
        appointmentRequests = temp;
    }
}
