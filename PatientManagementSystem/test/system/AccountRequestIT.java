
package system;

import guis.Login;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountRequestIT {
    
    public AccountRequestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Login.defaultData();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }


    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "5f4dcc3b5aa765d61d8327deb882cf99";
        String result = instance.getPassword();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        
        String Password = Login.hashPassword("password");
        AccountRequest instance = AccountRequest.accountRequests[0];
        
        instance.setPassword(Password);
        
        assertEquals(Password, AccountRequest.accountRequests[0].getPassword());
    }

    @Test
    public void testGetForename() {
        Login.defaultData();
        System.out.println("getForename");
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "George";
        String result = instance.getForename();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetForename() {
        System.out.println("setName");
        String Forename = "Sam";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setForename(Forename);

        assertEquals(Forename, AccountRequest.accountRequests[0].getForename());
    }

    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "Jordan";
        
        String result = instance.getSurname();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String Surname = "Jones";
        AccountRequest instance = AccountRequest.accountRequests[0];
        
        instance.setSurname(Surname);
        
        assertEquals(Surname, AccountRequest.accountRequests[0].getSurname());
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "91 New George St,\nPlymouth,\nPL1 1RQ";
        
        String result = instance.getAddress();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        
        String Address = "81 Greenwood Avenue,\nPontnewydd,\nCwmbran,\nNP44 5LH";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setAddress(Address);
        
        assertEquals(Address, AccountRequest.accountRequests[0].getAddress());
    }

    @Test
    public void testGetGender() {
        System.out.println("getGender");
        Login.defaultData();
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "M";
        
        String result = instance.getGender();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetGender() {
        System.out.println("setGender");
        
        String Gender = "F";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setGender(Gender);
        
        AccountRequest newInstance = AccountRequest.accountRequests[0];
        assertEquals(Gender, newInstance.getGender());
    }

    @Test
    public void testGetDOB() {
        Login.defaultData();
        System.out.println("getDOB");
        
        AccountRequest instance = AccountRequest.accountRequests[0];
        String expResult = "16/05/1994";
        
        String result = instance.getDOB();
        assertEquals(expResult, result);
    }


    @Test
    public void testSetDOB() {
        System.out.println("setDOB");

        String DOB = "01/03/2000";
        AccountRequest instance = AccountRequest.accountRequests[0];
        instance.setDOB(DOB);
        
        AccountRequest newInstance = AccountRequest.accountRequests[0];
        assertEquals(DOB, newInstance.getDOB());
    }

    @Test
    public void testAddAccountRequest() {
        System.out.println("addAccountRequest");
        AccountRequest newAccountRequest = new AccountRequest("password", "Sam", "Jordan", 
            "84 Beechwood Avenue,\n Plymouth,\nPL75LH", "M", "29/02/2000");
        
        newAccountRequest.addAccountRequest(newAccountRequest);
        
        assertEquals(newAccountRequest.getForename(), 
                AccountRequest.accountRequests[AccountRequest.accountRequests.length - 1].getForename());
    }

    @Test
    public void testRemoveAccountRequest() {
        System.out.println("removeAccountRequest");
        AccountRequest removeAccountRequest = AccountRequest.accountRequests[0];
        boolean error;
        
        removeAccountRequest.removeAccountRequest(removeAccountRequest);
        
        if(!(AccountRequest.accountRequests[0].getForename().equals(removeAccountRequest.getForename()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testGetAccountRequests() {
        System.out.println("getAccountRequests");
        
        AccountRequest.getAccountRequests();
        boolean error;
        
        if(AccountRequest.accountRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSaveAccountRequests() {
        System.out.println("saveAccountRequests");
        AccountRequest.saveAccountRequests();
        
        String filename = "info/accountRequests.ser";
        boolean error;

        File file = new File(filename);
        
        if(file.length() == 0){
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetAccountRequests() {
        System.out.println("setAccountRequests");
        
        AccountRequest.setAccountRequests();
        boolean error;
        
        if(AccountRequest.accountRequests != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }
    
}
