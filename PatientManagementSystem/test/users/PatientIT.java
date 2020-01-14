
package users;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PatientIT {
    
    public PatientIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetGender() {
        System.out.println("getGender");
        Login.defaultData();
        
        Patient instance = (Patient) User.users[0];
        String expResult = "M"; 
        
        String result = instance.getGender();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGender() {
        System.out.println("setGender");
        
        String Gender = "F";
        Patient instance = (Patient)User.users[0];
        instance.setGender(Gender);
        
        Patient newInstance = (Patient)User.users[0];
        assertEquals(Gender, newInstance.getGender());
    }

    @Test
    public void testGetDOB() {
        System.out.println("getDOB");
        
        Patient instance = (Patient)User.users[0];
        String expResult = "29/02/2000";
        
        String result = instance.getDOB();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetDOB() {
        System.out.println("setDOB");
        
        String DOB = "30/06/2000";
        Patient instance = (Patient)User.users[0];
        instance.setDOB(DOB);
        
        Patient newInstance = (Patient)User.users[0];
        assertEquals(DOB, newInstance.getDOB());
    }

    @Test
    public void testRemovePatient() {
        System.out.println("removePatient");
        
        Patient removePatient = (Patient)User.users[0];
        removePatient.removeUser(removePatient);
        
        boolean error;
        if(!(removePatient.getID().equals(User.users[0].getID()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testAddPatient() {
        System.out.println("addPatient");
        
        Patient newPatient = new Patient("P004", "password", "Sam", "Jordan", "89 Beechwood Avenue,\nPlymouth,\nPL46PW", null, "M", "30/06/2000");
        newPatient.addUser(newPatient);
        
        boolean error;
        if(User.users[User.users.length - 1].getID().equals(newPatient.getID())){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
        
    }
    
}
