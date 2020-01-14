
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
    
    public static void saveFeedbackRequests() {
        //saves feedback requests to file
        
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
    
    public static void getFeedbackRequests() {
        //gets feedback requests from file
        
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
        //setss default data
        
        FeedbackRequest[] store ={
            new FeedbackRequest(
                new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                10, "very helpful."),
            new FeedbackRequest(
                new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null),
                9, "Very nice."),
            new FeedbackRequest(
                new Doctor("D003","5f4dcc3b5aa765d61d8327deb882cf99","Fraser","Mcdodal","94 Dalphos,\nBristol,\nBR73RD",null),
                5, "Didnt even help!")
        };
        feedbackRequests = store;
    }
}
