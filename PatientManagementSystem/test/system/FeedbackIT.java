
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import guis.Login;


public class FeedbackIT {
    
    public FeedbackIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testGetDoctor() {
        Login.defaultData();
        System.out.println("getDoctor");
        
        Feedback instance = Feedback.feedback[0];
        Doctor expResult = new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null);
        Doctor result = instance.getDoctor();
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        Doctor Doctor = new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null);
        Feedback instance = Feedback.feedback[0];
        instance.setDoctor(Doctor);
        
        assertEquals(Doctor.getID(), instance.getDoctor().getID());
    }

    @Test
    public void testGetRating() {
        Login.defaultData();
        System.out.println("getRating");
        
        Feedback instance = Feedback.feedback[0];
        String expResult = "7.0";
        String result = Double.toString(instance.getRating());
         
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetRating() {
        System.out.println("setRating");
        
        double Rating = 0.0;
        Feedback instance = Feedback.feedback[0];
        instance.setRating(Rating);
        
        assertEquals(Double.toString(Rating), Double.toString(instance.getRating()));
    }

    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        
        Feedback instance = Feedback.feedback[0];
        String expResult = "Very kind and gentle person.";
        
        String result = instance.getNotes();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        
        String note = "Very bad doctor!";
        Feedback instance = Feedback.feedback[0];
        instance.setNotes(note);
        
        Feedback newInstance = Feedback.feedback[0];
        assertEquals(note, newInstance.getNotes());
    }

    @Test
    public void testAddFeedback() {
        Login.defaultData();
        System.out.println("addFeedback");
        Feedback newFeedback = new Feedback(
                                    new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                                    0, "Very nice Doctor");

        newFeedback.addFeedback(newFeedback);
        
        assertEquals(Feedback.feedback[Feedback.feedback.length - 1].getDoctor().getID(),
                newFeedback.getDoctor().getID());
        
    }

    @Test
    public void testGetFeedback() {
        Login.defaultData();
        System.out.println("getFeedback");
        
        Feedback.getFeedback();
        boolean error;
        
        if(Feedback.feedback != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSaveFeedback() {
        System.out.println("saveFeedback");
        
        Feedback.saveFeedback();
        boolean error;
        
        String filename = "info/feedback.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        assertEquals(false, error);
    }

    @Test
    public void testSetFeedback() {
        System.out.println("setFeedback");
        
        Feedback.setFeedback();
        boolean error;
        
        if(Feedback.feedback != null) {
            error = false;
        } else {
            error = true;
        }
        assertEquals(false, error);
    } 
}
