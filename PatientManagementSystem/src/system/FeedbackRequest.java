
package system;

import users.*;
import java.io.*;

public class FeedbackRequest extends Feedback{
    public static FeedbackRequest[] feedbackRequests;
        
    public FeedbackRequest(users.Doctor Doctor, int Rating, String Notes) {
        super(Doctor, Rating, Notes);
    }
    
    public void addFeedbackRequest(FeedbackRequest newFeedbackRequest) {}
    
    public void removeFeedbackRequest(FeedbackRequest removeFeedbackRequest) {
        int i = 0;
        
        FeedbackRequest[] store = new FeedbackRequest[feedbackRequests.length - 1];
        
        for(FeedbackRequest feedbackRequest : feedbackRequests) {
            if(feedbackRequest != removeFeedbackRequest) {
                store[i] = feedbackRequest;
                i++;
            }
        }
        feedbackRequests = store;
        
        saveFeedbackRequests();
        getFeedbackRequests();
    }
    
    public static void saveFeedbackRequests()
    {
        String filename = "info/feedbackRequests.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(feedbackRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void getFeedbackRequests()
    {
        FeedbackRequest[] store = null;
        String filename = "info/feedbackRequests.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (FeedbackRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        feedbackRequests = store;
    }
    
    public static void setFeedbackRequests()
    {
        FeedbackRequest[] store ={
            new FeedbackRequest(
                new Doctor("D002", "5f4dcc3b5aa765d61d8327deb882cf99", "Shirley", "Jones", "5 Admirals Hard,\nPlymouth,\nPL1 3RJ", null),
                7, "Very nice doctor."),
            new FeedbackRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                9, "Very kind and friendly doctor."),
            new FeedbackRequest(
                new Doctor("D003", "5f4dcc3b5aa765d61d8327deb882cf99", "Henry", "Brooks", "66 Neswick Street,\nPlymouth,\nPL2 5JN", null),
                7, "Always a pleasure to have Dr. Brooks!")
        };
        feedbackRequests = store;
    }
}
