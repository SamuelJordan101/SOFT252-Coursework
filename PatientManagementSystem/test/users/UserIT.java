
package users;

import guis.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.DEFAULT)
public class UserIT {
    
    public UserIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetID() {
        System.out.println("getID");
        
        User instance = User.users[0];
        String expResult = "P001";
        String result = instance.getID();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetID() {
        System.out.println("setID");
        
        String ID = "ID";
        User instance = User.users[0];
        instance.setID(ID);
        
        assertEquals(ID, User.users[0].getID());
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        User instance = User.users[0];
        String expResult = "5f4dcc3b5aa765d61d8327deb882cf99";
        String result = instance.getPassword();
        
        assertEquals(expResult, result);
        
        
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        
        String Password = Login.hashPassword("password");
        User instance = User.users[0];
        
        instance.setPassword(Password);
        
        assertEquals(Password, User.users[0].getPassword());
    }

    @Test
    public void testGetForename() {
        Login.defaultData();
        System.out.println("getForename");
        
        User instance = User.users[0];
        String expResult = "Evan";
        String result = instance.getForename();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetForename() {
        System.out.println("setForename");
        
        String Forename = "Jack";
        User instance = User.users[0];
        instance.setForename(Forename);
        
        assertEquals(Forename, User.users[0].getForename());
    }

    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        User instance = User.users[0];
        String expResult = "Ward";
        
        String result = instance.getSurname();
        
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String Surname = "Jones";
        User instance = User.users[0];
        
        instance.setSurname(Surname);
        
        assertEquals(Surname, User.users[0].getSurname());
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        User instance = User.users[0];
        String expResult = "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ";
        
        String result = instance.getAddress();
        
        assertEquals(expResult, result); 
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String Address = "89 Beechwood Avenue,\nPlymouth,\nPL46PW";
        User instance = User.users[0];
        instance.setAddress(Address);
        
        assertEquals(Address, User.users[0].getAddress());
    }

    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        User removeUser = User.users[2];
        boolean error;
        
        removeUser.removeUser(removeUser);
        
        if(!(User.users[2].getID().equals(removeUser.getID()))){
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
        
    }

    @Test
    public void testAddUser() {
        Login.defaultData();
        System.out.println("addUser");
        User newUser = new User("A002", "password", "Evan", "Ward", "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH", null);
        
        newUser.addUser(newUser);
        
        assertEquals(newUser.getID(), User.users[User.users.length - 1].getID());
    }

    @Test
    public void testSaveUsers() {
        System.out.println("saveUsers");
        User.saveUsers();
        boolean error;
        
        String filename = "data/users.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        User.getUsers();
        boolean error;
        
        if(User.users != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        User.setUsers();
        boolean error;
        
        if(User.users != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
}
