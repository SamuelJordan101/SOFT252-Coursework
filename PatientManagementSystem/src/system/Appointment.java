package system;

import users.*;
import java.io.*;

public class Appointment {

    public static Appointment[] appointments;
    private Doctor Doctor;
    private Patient Patient;
    private String Date;

    public Appointment(Doctor Doctor, Patient Patient, String Date) {
        this.Doctor = Doctor;
        this.Patient = Patient;
        this.Date = Date;
    }

    public Doctor getDoctor() {
        return Doctor;
    }

    public Doctor setDoctor(Doctor Doctor) {
        this.Doctor = Doctor;
    }

    public Patient getPatient() {
        return Patient;
    }

    public Patient setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    public String getDate() {
        return Date;
    }

    public String setDate(String Date) {
        this.Date = Date;
    }

    public Appointment addAppointment(Appointment newAppointment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Appointment removeAppointment(Appointment removeAppointment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void getAppointments() {
    }

    public static void saveAppointments() {
    }

    public static void setAppointments() {
    }
}
