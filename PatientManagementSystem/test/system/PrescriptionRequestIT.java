
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
            new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
            new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999"),
            "Health slowly decreasing.", new Medicine("Propranalol", 4), 10, "1 EVERY 24 HOURS");
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
        
        String filename = "info/prescriptionRequests.ser";

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
