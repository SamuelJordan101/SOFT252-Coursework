package system;

import users.*;
import java.io.*;

public class RequestFeedback extends Feedback {

    public static RequestFeedback[] RequestFeedbacks;

    public RequestFeedback(users.Doctor Doctor, int Rating, String Notes) {
        super(Doctor, Rating, Notes);
    }

    public RequestFeedback addRequestFeedback(RequestFeedback newRequestFeedback) {
    }

    public RequestFeedback removeRequestFeedback(RequestFeedback removeRequestFeedback) {
        int i = 0;
        
        RequestFeedback[] store = new RequestFeedback[RequestFeedbacks.length - 1];
        
        for(RequestFeedback RequestFeedback : RequestFeedbacks) {
            if(RequestFeedback != removeRequestFeedback) {
                store[i] = RequestFeedback;
                i++;
            }
        }
        
        RequestFeedbacks = store;
        
        saveRequestFeedbacks();
        getRequestFeedbacks();
    }

    public static void saveRequestFeedbacks() {
        String filename = "files/RequestFeedbacks.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(RequestFeedbacks); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error: " +  ex); 
        } 
    }

    public static void getRequestFeedbacks() {
        RequestFeedback[] store = null;
        String filename = "data/RequestFeedbacks.ser";
        
        try {    
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (RequestFeedback[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        }  
        catch(IOException ex) { 
            System.out.println("Error: " + ex); 
        } 
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException"); 
        } 
        
        RequestFeedbacks = store;
    }

    public static void setRequestFeedbacks() {
        RequestFeedback[] store ={
            new RequestFeedback(
                new Doctor("D001","Jeffrey","Halbert","password","8 Hillside,\n Plymouth,\nPL63TQ",null),
                9, "Very helpful"),
            new RequestFeedback(
                new Doctor("D002","Stanley","Doorsworth","password","7 Cottages,\n London,\nLN47TS",null),
                6, "Was alright"),
            new RequestFeedback(
                new Doctor("D003","Fraser","Mcdodal","password","94 Dalphos,\nBristol,\nBR73RD",null),
                3, "Was very late")
        };
        RequestFeedbacks = store;
    }
}
