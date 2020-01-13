package system;

public class Prescription {

    public static Prescription[] prescriptions;

    private Doctor Doctor;

    private Patient Patient;

    private String Notes;

    private Medicine Medicine;

    private int Quantity;

    private String Dosage;

    public Prescription(Doctor Doctor, Patient Patient, String Notes, Medicine Medicine, int Quantity, String Dosage) {
    }

    public Doctor getDoctor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Doctor setDoctor(Doctor Doctor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Patient getPatient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Patient setPatient(Patient Patient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setNotes(String Notes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Medicine getMedicine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Medicine setMedicine(Medicine Medicine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int setQuantity(int Quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getDosage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setDosage(String Dosage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Prescription removePrescription(Prescription removePrescription) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Prescription addPrescription(Prescription newPrescription) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void savePrescriptions() {
    }

    public static void getPrescriptions() {
    }

    public static void setPrescriptions() {
    }
}
