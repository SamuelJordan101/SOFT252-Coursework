
package system;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Doctor;
import users.Patient;
import guis.Login;


public class PrescriptionIT {
    
    public PrescriptionIT() {
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
        
        Prescription instance = Prescription.prescriptions[0];
        Doctor expResult = new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null);
        Doctor result = instance.getDoctor();
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetDoctor() {
        System.out.println("setDoctor");
        
        Doctor Doctor = new Doctor("D002","5f4dcc3b5aa765d61d8327deb882cf99","Stanley","Doorsworth","7 Cottages,\n London,\nLN47TS",null);
        Prescription instance = Prescription.prescriptions[0];
        instance.setDoctor(Doctor);
        
        assertEquals(Doctor, instance.getDoctor());
    }

    @Test
    public void testGetPatient() {
        Login.defaultData();
        System.out.println("getPatient");
        
        Prescription instance = Prescription.prescriptions[0];
        Patient expResult = new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000");
        Patient result = instance.getPatient();
        
        
        assertEquals(expResult.getID(), result.getID());
    }

    @Test
    public void testSetPatient() {
        System.out.println("setPatient");
        
        Patient Patient = new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000");
        Prescription instance = Prescription.prescriptions[0];
        instance.setPatient(Patient);
        
        assertEquals(Patient, instance.getPatient());
    }

    @Test
    public void testGetNotes() {
        Login.defaultData();
        System.out.println("getNotes");
        
        Prescription instance = Prescription.prescriptions[0];
        String expResult = "Showing signs of health improving.\nOn course for a full recovery.";
        String result = instance.getNotes();

        assertEquals(expResult, result);
    }

    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        
        String Notes = "Very bad Doctor!";
        Prescription instance = Prescription.prescriptions[0];
        instance.setNotes(Notes);
        
        assertEquals(Notes, instance.getNotes());
    }

    @Test
    public void testGetMedicine() {
        Login.defaultData();
        System.out.println("getMedicine");
        
        Prescription instance = Prescription.prescriptions[0];
        Medicine expResult = new Medicine("Chlorpromazine", 5);
        Medicine result = instance.getMedicine();
        
        assertEquals(expResult.getName(), result.getName());
    }

    @Test
    public void testSetMedicine() {
        System.out.println("setMedicine");
        
        Medicine Medicine = new Medicine("Iburprofen", 8);
        Prescription instance = Prescription.prescriptions[0];
        instance.setMedicine(Medicine);

        assertEquals(Medicine.getName(), instance.getMedicine().getName());
    }

    @Test
    public void testGetQuantity() {
        Login.defaultData();
        System.out.println("getQuantity");
        
        Prescription instance = Prescription.prescriptions[0];
        int expResult = 4;
        int result = instance.getQuantity();
        
        assertEquals(expResult, result);
    }


    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        
        int Quantity = 0;
        Prescription instance = Prescription.prescriptions[0];
        instance.setQuantity(Quantity);
        
        assertEquals(Quantity, instance.getQuantity());
    }

    @Test
    public void testGetDosage() {
        Login.defaultData();
        System.out.println("getDosage");
        
        Prescription instance = Prescription.prescriptions[0];
        String expResult = "1 EVERY 24 HOURS";
        String result = instance.getDosage();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDosage() {
        System.out.println("setDosage");
        
        String Dosage = "1 every 10 hours";
        Prescription instance = Prescription.prescriptions[0];
        instance.setDosage(Dosage);

        assertEquals(Dosage, instance.getDosage());
    }

    @Test
    public void testRemovePrescription() {
        System.out.println("removePrescription");
        Prescription removePrescription = Prescription.prescriptions[0];
        boolean error;
        
        removePrescription.removePrescription(removePrescription);
        
        if(!(Prescription.prescriptions[0].getPatient().getID().equals(removePrescription.getPatient().getID()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testAddPrescription() {
        Login.defaultData();
        System.out.println("addPrescription");
        Prescription newPrescription = new Prescription(
                    new Doctor("D001","5f4dcc3b5aa765d61d8327deb882cf99","Jeffrey","Halbert","8 Hillside,\n Plymouth,\nPL63TQ",null),
                    new Patient("P001","5f4dcc3b5aa765d61d8327deb882cf99","Sam","Jordan","42 Beechwood Avenue,\n Plymouth,\nPL46PW",null,"M","30/06/2000"),
                    "Health in perfect condition.\nRecommend dosage decrease.", new Medicine("Propranalol", 4), 1, "1 EVERY 48 HOURS");
        newPrescription.addPrescription(newPrescription);
        
        Prescription instance = Prescription.prescriptions[Prescription.prescriptions.length - 1];
        
        boolean error;
        if(((newPrescription.getDoctor().getID()).equals(instance.getDoctor().getID())) && (newPrescription.getPatient().getID().equals(instance.getPatient().getID()))) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSavePrescriptions() {
        System.out.println("savePrescriptions");
        
        Prescription.savePrescriptions();
        boolean error;
        
        String filename = "info/prescriptions.ser";

        File file = new File(filename);
        
        if(file.length() == 0) {
            error = true;
        } else {
            error = false;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testGetPrescriptions() {
        System.out.println("getPrescriptions");
        
        Prescription.getPrescriptions();
        boolean error;
        
        if(Prescription.prescriptions != null) {
            error = false;
        } else {
            error = true;
        }
        
        assertEquals(false, error);
    }

    @Test
    public void testSetPrescriptions() {
        System.out.println("setPrescriptions");
        Prescription.setPrescriptions();
        boolean error;
       
        if(Prescription.prescriptions != null) {
            error = false;
        } else {
            error = true;
        }
        assertEquals(false, error);
    }
}
