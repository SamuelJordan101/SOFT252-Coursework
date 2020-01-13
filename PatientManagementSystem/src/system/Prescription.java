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

    public void setDoctor(Doctor Doctor) {
    }

    public Patient getPatient() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPatient(Patient Patient) {
    }

    public String getNotes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNotes(String Notes) {
    }

    public Medicine getMedicine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMedicine(Medicine Medicine) {
    }

    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQuantity(int Quantity) {
    }

    public String getDosage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDosage(String Dosage) {
    }

    public void removePrescription(Prescription removePrescription) {
    }

    public void addPrescription(Prescription newPrescription) {
    }

    public static void savePrescriptions() {
    }

    public static void getPrescriptions() {
    }

    public static void setPrescriptions() {
    }
}
