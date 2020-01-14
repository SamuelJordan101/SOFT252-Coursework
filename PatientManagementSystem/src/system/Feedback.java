
package system;

import users.*;
import java.io.*;

public class Feedback implements Serializable {
    public static Feedback[] feedback;
    private Doctor Doctor;
    private double Rating;
    private String Notes;

    public Feedback(Doctor Doctor, double Rating, String Notes) {
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

    public double getRating() {
        return Rating;
    }

    public void setRating(double Rating) {
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
        String filename = "info/feedback.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (Feedback[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        feedback = store;
    }
    
    public static void saveFeedback()
    {
        String filename = "info/feedback.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(feedback); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void setFeedback() {
        Feedback[] store = {
            new Feedback(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                7, "Very kind and gentle person."),
            new Feedback(
                new Doctor("D001", "5f4dcc3b5aa765d61d8327deb882cf99", "Joe", "Bloggs", "3 Charles Darwin Road,\nPlymouth,\nPL3 4GU", null),
                5, "Nice person but hard to understand."),
            new Feedback(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                3, "Seems very rough and confused half the time"),
            new Feedback(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                7, "Very nice doctor."),
            new Feedback(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                9, "Very kind and friendly doctor."),
            new Feedback(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                7, "Always a pleasure to have Dr. Brooks!")
        };
        feedback = store;
    }
}
