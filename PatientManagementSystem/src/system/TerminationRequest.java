
package system;

import java.io.*;
import users.*;

public class TerminationRequest implements Serializable{
    public static TerminationRequest[] terminationRequests;
    private Patient Patient;

    public TerminationRequest(Patient Patient) {
        this.Patient = Patient;
    }

    public Patient getPatient() {
        return Patient;
    }

    public void setPatientID(Patient Patient) {
        this.Patient = Patient;
    }
    
    public void removeTerminationRequest(TerminationRequest removeTerminationRequest) {
        int i = 0;
        
        TerminationRequest[] store = new TerminationRequest[terminationRequests.length - 1];
        
        for(TerminationRequest terminationRequest : terminationRequests) {
            if(terminationRequest != removeTerminationRequest) {
                store[i] = terminationRequest;
                i++;
            }
        }
        
        terminationRequests = store;
        
        saveTerminationRequests();
        getTerminationRequests();
    }
    
    public void addTerminationRequest(TerminationRequest newTerminationRequest) {
        int i;
        
        TerminationRequest[] temp = new TerminationRequest[terminationRequests.length + 1];
        
        for(i=0;i<temp.length-1;i++)
            temp[i] = terminationRequests[i];
        
        temp[i] = newTerminationRequest;
        terminationRequests = temp;
        
        saveTerminationRequests();
        getTerminationRequests();
    }
    
    public static void getTerminationRequests() {
        TerminationRequest[] store = null;
        String filename = "info/terminationRequests.ser";
        
        try {  
            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            store = (TerminationRequest[])in.readObject(); 
              
            in.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error  is caught: " + ex); 
        }  
        catch(ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        terminationRequests = store;
    }
    
    public static void saveTerminationRequests()
    {
        String filename = "info/terminationRequests.ser"; 
          
        try {    
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(terminationRequests); 
              
            out.close(); 
            file.close(); 
        } 
        catch(IOException ex) { 
            System.out.println("Error is caught: " +  ex); 
        } 
    }
    
    public static void setTerminationRequests() {
        TerminationRequest[] store = {
            new TerminationRequest(new Patient("P003", "5f4dcc3b5aa765d61d8327deb882cf99","Sam", "Jordan", "66 Merrifields,\nPlymouth,\nPL36JN", null, "F", "10/08/1992")),
            new TerminationRequest(new Patient("P001", "5f4dcc3b5aa765d61d8327deb882cf99","Katie", "Evans", "574 Mutley Plain,\nPlymouth,\nPL93GH", null, "M", "29/02/2000"))
        };
        terminationRequests = store;
    }
}
