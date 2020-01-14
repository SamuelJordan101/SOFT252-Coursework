
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import guis.Login;


public class MedicineIT {
    
    public MedicineIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        
        Medicine instance = Medicine.medicines[0];
        String expResult = "Propranalol";
        String result = instance.getName();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        
        String Name = "Paracetamol";
        Medicine instance = Medicine.medicines[0];
        instance.setName(Name);
 
        assertEquals(Name, instance.getName());
    }

    @Test
    public void testGetStock() {
        Login.defaultData();
        System.out.println("getStock");
        
        Medicine instance = Medicine.medicines[0];
        int expResult = 5;
        int result = instance.getStock();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetStock() {
        System.out.println("setStock");
        
        int Stock = 0;
        Medicine instance = Medicine.medicines[0];
        instance.setStock(Stock);
        
        assertEquals(Stock, instance.getStock());
    }
    
    @Test
    public void testAddMedicine()
    {
        System.out.println("addMedicine");
        
        Medicine newMedicine = new Medicine("drugs", 22);
        newMedicine.addMedicine(newMedicine);
        
        Medicine instance = Medicine.medicines[Medicine.medicines.length -1];
        assertEquals(newMedicine.getName(), instance.getName());
    }

    @Test
    public void testGetMedicine() {
        Login.defaultData();
        System.out.println("getMedicine");
        
        Medicine.getMedicine();
        boolean error;
        
        if(Medicine.medicines != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSaveMedicine() {
        System.out.println("saveMedicine");
        
        Medicine.saveMedicine();
        boolean error;
        
        String filename = "info/medicines.ser";

        File file = new File(filename);
        
        if(file.length() == 0){
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetMedicine() {
        System.out.println("setMedicine");
        
        Medicine.setMedicine();
        boolean error;
        
        if(Medicine.medicines != null) {
            error = false;
        } else {
            error = true;
        }
        assertEquals(false, error);
    }
}
