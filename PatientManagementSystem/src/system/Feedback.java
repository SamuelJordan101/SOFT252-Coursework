package system;

import users.*;
import java.io.*;

public class Feedback implements Serializable{

    public static Feedback[] feedback;
    private Doctor Doctor;
    private float Rating;
    private String Notes;

    public Feedback(Doctor Doctor, float Rating, String Notes) {
        this.Doctor = Doctor;
        this.Rating = Rating;
        this.Notes = Notes;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public void setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public void addFeedback(Feedback newFeedback) {
        int i;
        
        Feedback[] store = new Feedback[feedback.length + 1];
        
        for(i=0;i<store.length-1;i++)
            store[i] = feedback[i];
        
        store[i] = newFeedback;
        feedback = store;
        
        saveFeedback();
        getFeedback();
    }

    public static void getFeedback() {
        Feedback[] store = null;
        String filename = "files/feedback.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Feedback[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        feedback = store;
    }

    public static void saveFeedback() {
        String filename = "files/feedback.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(feedback); 
              
            out.close(); 
            file.close(); 
        } 
          
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void setFeedback() {
        Feedback[] temp = {
            new Feedback(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                10, "Very nice, very helpful"),
            new Feedback(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                5, "Was alright, did their job"),
            new Feedback(
                new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                0, "Was very late and then didn't even help me")
        };
        feedback = temp;
    }
}
