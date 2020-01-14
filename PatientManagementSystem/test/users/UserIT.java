
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
        //testing that the id getting works properly
        
        System.out.println("getID");
        
        User instance = User.users[0];
        String expResult = "P001";
        String result = instance.getID();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetID() {
        //testing that the id setting works properly
        
        System.out.println("setID");
        
        String ID = "ID";
        User instance = User.users[0];
        instance.setID(ID);
        
        assertEquals(ID, User.users[0].getID());
    }

    @Test
    public void testGetPassword() {
        //testing that the password getting works properly
        
        System.out.println("getPassword");
        
        User instance = User.users[0];
        String expResult = "5f4dcc3b5aa765d61d8327deb882cf99";
        String result = instance.getPassword();
        
        assertEquals(expResult, result);
        
        
    }

    @Test
    public void testSetPassword() {
        //testing that the password setting works properly
        
        System.out.println("setPassword");
        
        String Password = Login.hashPassword("password");
        User instance = User.users[0];
        
        instance.setPassword(Password);
        
        assertEquals(Password, User.users[0].getPassword());
    }

    @Test
    public void testGetForename() {
        //testing that the forename getting works properly
        
        Login.defaultData();
        System.out.println("getForename");
        
        User instance = User.users[0];
        String expResult = "Evan";
        String result = instance.getForename();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetForename() {
        //testing that the forename setting works properly
        
        System.out.println("setForename");
        
        String Forename = "Jack";
        User instance = User.users[0];
        instance.setForename(Forename);
        
        assertEquals(Forename, User.users[0].getForename());
    }

    @Test
    public void testGetSurname() {
        //testing that the surname getting works properly
        
        System.out.println("getSurname");
        
        User instance = User.users[0];
        String expResult = "Ward";
        
        String result = instance.getSurname();
        
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetSurname() {
        //testing that the surname setting works properly
        
        System.out.println("setSurname");
        String Surname = "Jones";
        User instance = User.users[0];
        
        instance.setSurname(Surname);
        
        assertEquals(Surname, User.users[0].getSurname());
    }

    @Test
    public void testGetAddress() {
        //testing that the address getting works properly
        
        System.out.println("getAddress");
        User instance = User.users[0];
        String expResult = "Flat 5,\n58 North Road East,\nPlymouth,\nPL4 6AJ";
        
        String result = instance.getAddress();
        
        assertEquals(expResult, result); 
    }

    @Test
    public void testSetAddress() {
        //testing that the address setting works properly
        
        System.out.println("setAddress");
        String Address = "89 Beechwood Avenue,\nPlymouth,\nPL46PW";
        User instance = User.users[0];
        instance.setAddress(Address);
        
        assertEquals(Address, User.users[0].getAddress());
    }

    @Test
    public void testRemoveUser() {
        //testing that the user removing works properly
        
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
        //testing that the user adding works properly 
        
        Login.defaultData();
        System.out.println("addUser");
        User newUser = new User("A002", "password", "Sam", "Jordan", "89 Beechwood Avenue,\nPlymouth,\nPL46PW", null);
        
        newUser.addUser(newUser);
        
        assertEquals(newUser.getID(), User.users[User.users.length - 1].getID());
    }

    @Test
    public void testSaveUsers() {
        //testing that the user saving to file works properly
        
        System.out.println("saveUsers");
        User.saveUsers();
        boolean error;
        
        String filename = "info/users.ser";

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
        //testing that the user getting works properly
        
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
        //testing that the user setting works properly
        
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
