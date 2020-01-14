
package users;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoctorIT {
    
    public DoctorIT() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAddDoctor() {
        System.out.println("addDoctor");
        
        Doctor newDoctor = new Doctor("P004", "password", "Sam", "Jordan", "89 Beechwood Avenue,\nPlymouth,\nPL46PW", null);
        newDoctor.addUser(newDoctor);
        
        boolean error;
        if(User.users[User.users.length - 1].getID().equals(newDoctor.getID())){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
