
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import guis.Login;


public class MedicineRequestIT {
    
    public MedicineRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void removeMedicineRequest()
    {
        Login.defaultData();
        System.out.println("removeMedicineRequest");
        
        MedicineRequest removeMedicineRequest = MedicineRequest.medicineRequests[0];
        boolean error;
        
        removeMedicineRequest.removeMedicineRequest(removeMedicineRequest);
        
        if(!(MedicineRequest.medicineRequests[0].getName().equals(removeMedicineRequest.getName()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testAddMedicineRequest() {
        System.out.println("addMedicineRequest");
        
        MedicineRequest newMedicine = new MedicineRequest("DRUGS", 34);
        newMedicine.addMedicineRequest(newMedicine);
        
        MedicineRequest instance = MedicineRequest.medicineRequests[MedicineRequest.medicineRequests.length - 1];
        
        assertEquals(newMedicine.getName(), instance.getName());
       
    }

    @Test
    public void testGetMedicineRequests() {
        Login.defaultData();
        System.out.println("getMedicineRequests");
        
        MedicineRequest.getMedicineRequests();
        boolean error;
        
        if(MedicineRequest.medicineRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSaveMedicineRequests() {
        System.out.println("saveMedicineRequests");
        
        MedicineRequest.saveMedicineRequests();
        boolean error;
        
        String filename = "data/medicineRequests.ser";

        File file = new File(filename);
        
        if(file.length() == 0){
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetMedicineRequests() {
        System.out.println("setMedicineRequests");
        MedicineRequest.setMedicineRequests();
        boolean error;
        
        if(MedicineRequest.medicineRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
