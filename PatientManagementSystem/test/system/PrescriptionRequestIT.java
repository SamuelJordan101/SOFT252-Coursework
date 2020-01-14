
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;
import guis.Login;


public class PrescriptionRequestIT {
    
    public PrescriptionRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testRemovePrescriptionRequest() {
        Login.defaultData();
        System.out.println("removePrescriptionRequest");
        
        PrescriptionRequest removePrescriptionRequest = PrescriptionRequest.prescriptionRequests[0];
        boolean error;
        
        removePrescriptionRequest.removePrescriptionRequest(removePrescriptionRequest);
        
        PrescriptionRequest instance = PrescriptionRequest.prescriptionRequests[0];
        
        if(!(removePrescriptionRequest.getDoctor().getID().equals(instance.getDoctor().getID()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testAddPrescriptionRequest() {
        Login.defaultData();
        System.out.println("addPrescriptionRequest");
        PrescriptionRequest newPrescriptionRequest = new PrescriptionRequest(
            new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
            new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99", "Linda", "Bennett", "66 Neswick Street,\nPlymouth,\nPL1 5JN", null, "F", "10/08/1992"),
            "Health slowly decreasing.", new Medicine("Paracetemol", 18), 10, "2 EVERY 12 HOURS");
        newPrescriptionRequest.addPrescriptionRequest(newPrescriptionRequest);
        
        PrescriptionRequest instance = PrescriptionRequest.prescriptionRequests[PrescriptionRequest.prescriptionRequests.length - 1];
        
        assertEquals(newPrescriptionRequest.getDoctor().getID(), instance.getDoctor().getID());
    }

    @Test
    public void testGetPrescriptionRequests() {
        System.out.println("getPrescriptionRequests");
        
        PrescriptionRequest.getPrescriptionRequests();
        boolean error;
        
        if(PrescriptionRequest.prescriptionRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSavePrescriptionRequests() {
        System.out.println("savePrescriptionRequests");
        
        PrescriptionRequest.savePrescriptionRequests();
        boolean error;
        
        String filename = "data/prescriptionRequests.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetPrescriptionRequests() {
        System.out.println("setPrescriptionRequests");
        
        PrescriptionRequest.setPrescriptionRequests();
        boolean error;
        
        if(PrescriptionRequest.prescriptionRequests != null){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
