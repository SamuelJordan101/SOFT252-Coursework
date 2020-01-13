package system;

public class PastAppointment extends Appointment {

    public static PastAppointment[] pastAppointments;

    private Prescription pastPrescription;

    public PastAppointment(Doctor Doctor, Patient Patient, String Date, Prescription PastPrescription) {
    }

    public Prescription getPastPrescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPastPrescription(Prescription pastPrescription) {
    }

    public void addPastAppointment(PastAppointment newPastAppointment) {
    }

    public static void getPastAppointments() {
    }

    public static void savePastAppointments() {
    }

    public static void setPastAppointments() {
    }
}
