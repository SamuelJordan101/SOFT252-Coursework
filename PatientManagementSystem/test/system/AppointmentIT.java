
package system;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;
import java.io.*;


public class AppointmentIT {
    
    public AppointmentIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetDoctor() {
        Login.defaultData();
        System.out.println("getDoctor");
        
        Appointment instance = Appointment.appointments[0];
        Doctor expResult = new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null);
        Doctor result = instance.getDoctor();
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        
        Doctor Doctor = new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null); 
        Appointment instance = Appointment.appointments[0];
        instance.setDoctor(Doctor);
        boolean error;
        
        if(instance.getDoctor().getID().equals(Doctor.getID())){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testGetPatient() {
        Login.defaultData();
        System.out.println("getPatient");
        
        Appointment instance = Appointment.appointments[0];
        Patient expResult = new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000");
        Patient result = instance.getPatient();
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetPatient() {
        System.out.println("setPatient");
        Patient Patient = new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000");
        Appointment instance = Appointment.appointments[0];
        instance.setPatient(Patient);
        boolean error;
        
        if(instance.getPatient().getID().equals(Patient.getID())){
            error = false;
        } else {
            error = true;
        }
    }

    @Test
    public void testGetDate() {
        Login.defaultData();
        System.out.println("getDate");
        
        Appointment instance = Appointment.appointments[0];
        String expResult = "27/12/2019 12:45";
        
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDate() {
        System.out.println("setDate");
        
        String date = "06/01/2020 13:00";
        Appointment instance = Appointment.appointments[0];
        instance.setDate(date);
        
        Appointment newInstance = Appointment.appointments[0];
        assertEquals(date, newInstance.getDate());
    }

    @Test
    public void testAddAppointment() {
        Login.defaultData();
        System.out.println("addAppointment");
        Appointment newAppointment = new Appointment(
            new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
            new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"),
            "06/01/2020");
        
        newAppointment.addAppointment(newAppointment);
        
        assertEquals(newAppointment.getPatient().getID(), 
                Appointment.appointments[Appointment.appointments.length - 1].getPatient().getID());
    }

    @Test
    public void testRemoveAppointment() {
        Login.defaultData();
        System.out.println("removeAppointment");
        
        Appointment removeAppointment = Appointment.appointments[0];
        boolean error;
        
        removeAppointment.removeAppointment(removeAppointment);
        
        if(!(Appointment.appointments[0].getPatient().getID().equals(removeAppointment.getPatient().getID()))){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testGetAppointments() {
        System.out.println("getAppointments");
        
        Appointment.getAppointments();
        boolean error;
        
        if(Appointment.appointments != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
    @Test
    public void testSaveAppointments() {
        System.out.println("saveAppointments");
        
        Appointment.getAppointments();
        boolean error;
        
        String filename = "info/appointments.ser";

        File file = new File(filename);
        
        if(file.length() == 0)
        {
            error = true;
        } 
        else
        {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetAppointments() {
        System.out.println("setAppointments");
        
        Appointment.setAppointments();
        boolean error;
        
        if(Appointment.appointments != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
