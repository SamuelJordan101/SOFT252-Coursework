
package users;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SecretaryIT {
    
    public SecretaryIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    @Test
    public void testAddSecretary() {
        System.out.println("addSecretary");
        
        Secretary newSecretary = new Secretary("P004", "password", "Sam", "Jordan", "89 Beechwood Avenue,\nPlymouth,\nPL46PW", null);
        newSecretary.addUser(newSecretary);
        
        boolean error;
        
        //tests that adding a user works fully
        
        if(User.users[User.users.length - 1].getID().equals(newSecretary.getID())){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
