
package system;

import guis.Login;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;


public class AppointmentRequestIT {
    
    public AppointmentRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAddAppointmentRequest() {
        Login.defaultData();
        System.out.println("addAppointmentRequest");
        AppointmentRequest newAppointmentRequest = new AppointmentRequest(
            new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
            new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
            "06/01/2020");
        
        newAppointmentRequest.addAppointmentRequest(newAppointmentRequest);
        
        assertEquals(newAppointmentRequest.getPatient().getID(), 
                AppointmentRequest.appointmentRequests[AppointmentRequest.appointmentRequests.length - 1].getPatient().getID());
    }

    @Test
    public void testRemoveAppointmentRequest() {
        System.out.println("removeAppointmentRequest");
        
        AppointmentRequest removeAppointmentRequest = AppointmentRequest.appointmentRequests[0];
        boolean error;
        
        removeAppointmentRequest.removeAppointmentRequest(removeAppointmentRequest);
        
        if(!(AppointmentRequest.appointmentRequests[0].getPatient().getID().equals(removeAppointmentRequest.getPatient().getID()))) {
            error = false;
        } else {
            error = true;
        }
    }

    @Test
    public void testSaveAppointmentRequests() {
        System.out.println("saveAppointmentRequests");
        
        AppointmentRequest.getAppointmentRequests();
        boolean error;
        
        String filename = "info/appointmentRequests.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }


    @Test
    public void testGetAppointmentRequests() {
        System.out.println("getAppointmentRequests");
       
        AppointmentRequest.getAppointmentRequests();
        boolean error;
        
        if(AppointmentRequest.appointmentRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetAppointmentRequests() {
        System.out.println("setAppointmentRequests");
        
        AppointmentRequest.setAppointmentRequests();
        boolean error;
        
        if(AppointmentRequest.appointmentRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
