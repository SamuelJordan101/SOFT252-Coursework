
package system;

import users.*;
import java.io.*;

public class AppointmentRequest extends Appointment {
    public static AppointmentRequest[] appointmentRequests;
    
    public AppointmentRequest(Doctor Doctor, users.Patient Patient, String Date) {
        super(Doctor, Patient, Date);
    }
    
    public void addAppointmentRequest(AppointmentRequest newAppointmentRequest) {
        int i;
        
        AppointmentRequest[] store = new AppointmentRequest[appointmentRequests.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = appointmentRequests[i];
        
        store[i] = newAppointmentRequest;
        appointmentRequests = store;
        
        saveAppointmentRequests();
    }
    
    public void removeAppointmentRequest(AppointmentRequest removeAppointmentRequest)
    {
        int i = 0;
        
        AppointmentRequest[] store = new AppointmentRequest[appointmentRequests.length - 1];
        
        for(AppointmentRequest appointmentRequest : appointmentRequests) {
            if(appointmentRequest != removeAppointmentRequest) {
                store[i] = appointmentRequest;
                i++;
            }
        }
        
        appointmentRequests = store;
        saveAppointmentRequests();
    }
    
    public static void saveAppointmentRequests() {
        String filename = "info/appointmentRequests.ser"; 

        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(appointmentRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void getAppointmentRequests() {
        AppointmentRequest[] store = null;
        String filename = "info/appointmentRequests.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (AppointmentRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        appointmentRequests = store;
    }
    
    public static void setAppointmentRequests()
    {
        AppointmentRequest[] store = {
            new AppointmentRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "03/01/2020 12:45"),
            new AppointmentRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                new Patient("P002", "5f4dcc3b5aa765d61d8327deb882cf99", "Chloe", "Jones", "31 Clarence Place,\nPlymouth,\nPL2 3JP", null, "F", "13/06/1998"),
                "05/01/2020 13:00"),
            new AppointmentRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                "11/01/2020 11:30")
        };
        appointmentRequests = store;
    }
}
