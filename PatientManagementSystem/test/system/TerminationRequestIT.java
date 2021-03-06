
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Patient;
import guis.Login;

public class TerminationRequestIT {
    
    public TerminationRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

  
    @Test
    public void testGetPatient() {
        System.out.println("getPatient");
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        Patient expResult = new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999");
        Patient result = instance.getPatient();
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetPatientID() {
        Login.defaultData();
        System.out.println("setPatientID");
        Patient Patient = new Patient("P003","5f4dcc3b5aa765d61d8327deb882cf99","Imogen","Jones","3 Macklesworth,\n Totnes,\nTO32BK",null,"M","19/02/1999");
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        instance.setPatientID(Patient);
        
        assertEquals(Patient.getID(), instance.getPatient().getID());
    }

    @Test
    public void testRemoveTerminationRequest() {
        System.out.println("removeTerminationRequest");
        TerminationRequest removeTerminationRequest = TerminationRequest.terminationRequests[0];
        boolean error;
        
        removeTerminationRequest.removeTerminationRequest(removeTerminationRequest);
        
        TerminationRequest instance = TerminationRequest.terminationRequests[0];
        
        if(!(removeTerminationRequest.getPatient().getID().equals(instance.getPatient().getID()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testAddTerminationRequest() {
        System.out.println("addTerminationRequest");
        
        TerminationRequest newTerminationRequest = new TerminationRequest(
            new Patient("P002","5f4dcc3b5aa765d61d8327deb882cf99","Owen","Howarth","56 Clothworth,\n Plymouth,\nPL94RU",null,"M","13/10/2000"));
        newTerminationRequest.addTerminationRequest(newTerminationRequest);
       
        int i = TerminationRequest.terminationRequests.length - 1;
        TerminationRequest instance = TerminationRequest.terminationRequests[i];
        
        assertEquals(newTerminationRequest.getPatient().getID(), instance.getPatient().getID());
    }

    @Test
    public void testGetTerminationRequests() {
        System.out.println("getTerminationRequests");
        
        TerminationRequest.getTerminationRequests();
        boolean error;
        
        if(TerminationRequest.terminationRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSaveTerminationRequests() {
        System.out.println("saveTerminationRequests");
        
        TerminationRequest.saveTerminationRequests();
        boolean error;
        
        String filename = "info/terminationRequests.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetTerminationRequests() {
        System.out.println("setTerminationRequests");
        

        TerminationRequest.setTerminationRequests();
        boolean error;
        
        if(TerminationRequest.terminationRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
}
