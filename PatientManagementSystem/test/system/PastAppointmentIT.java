
package system;

import guis.Login;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;


public class PastAppointmentIT {
    
    public PastAppointmentIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    @Test
    public void testGetPastPrescription() {
        System.out.println("getPastPrescription");
        
        Prescription expResult = new Prescription(
                    new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                    new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Penicillin", 5), 6, "1 EVERY 48 HOURS");
        PastAppointment instance = PastAppointment.pastAppointments[0];
        
        Prescription result = instance.getPastPrescription();
        
        assertEquals(expResult.getDoctor().getID(), result.getDoctor().getID());
    }

    @Test
    public void testSetPastPrescription() {
        System.out.println("setPastPrescription");
        PastAppointment pastAppointment = PastAppointment.pastAppointments[0];
        Prescription pastPrescription = new Prescription(new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null), 
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "Health is looking good.", new Medicine("Paracetemol", 15), 19, "2 EVERY 12 HOURS");
        pastAppointment.setPastPrescription(pastPrescription);
        
        assertEquals(pastAppointment.getPastPrescription().getDoctor().getID(), pastPrescription.getDoctor().getID());
    }

    @Test
    public void testAddPastAppointment() {
        System.out.println("addPastAppointment");
        PastAppointment newPastAppointment = new PastAppointment(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
            new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
            "06/01/2020",
            new Prescription(new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null), 
                new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99", "Evan", "Ward", "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ", null, "M", "29/02/2000"),
                "Health is looking good.", new Medicine("Paracetemol", 15), 19, "2 EVERY 12 HOURS"));
        
        newPastAppointment.addPastAppointment(newPastAppointment);
        
        assertEquals(newPastAppointment.getPatient().getID(), 
                PastAppointment.pastAppointments[PastAppointment.pastAppointments.length - 1].getPatient().getID());
    }

    @Test
    public void testGetPastAppointments() {
        Login.defaultData();
        System.out.println("getPastAppointments");
        
        PastAppointment.getPastAppointments();
        boolean error;
        
        if(PastAppointment.pastAppointments != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSavePastAppointments() {
        System.out.println("savePastAppointments");
        
        PastAppointment.savePastAppointments();
        boolean error;
        
        String filename = "info/pastAppointments.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetPastAppointments() {
        System.out.println("setPastAppointments");
        PastAppointment.setPastAppointments();
        boolean error;
        
        if(PastAppointment.pastAppointments != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
