
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
                new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
                "03/01/2020 12:45"),
            new AppointmentRequest(
                new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
                new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
                "05/01/2020 13:00"),
            new AppointmentRequest(
                new Doctor("D003","5f4dcc3b5aa765d61d8327deb882cf99","Fraser","Mcdodal","94 Dalphos,\nBristol,\nBR73RD",null),
                new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                "11/01/2020 11:30")
        };
        appointmentRequests = store;
    }
}
