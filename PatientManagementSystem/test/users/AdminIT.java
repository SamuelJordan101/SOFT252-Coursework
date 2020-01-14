
package users;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminIT {
    
    public AdminIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAddAdmin() {
        System.out.println("addAdmin");
        
        Admin newAdmin = new Admin("P004", "password", "Sam", "Jordan", "89 Beechwood Avenue,\nPlymouth,\nPL46PW", null);
        newAdmin.addUser(newAdmin);
        
        boolean error;

        if(User.users[User.users.length - 1].getID().equals(newAdmin.getID())) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
