
package guis;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;  
import java.util.Date;
import users.*;
import system.*;


public class PatientFunctions extends javax.swing.JFrame {


    public PatientFunctions() {
        initComponents();
        getUserInfo();
        getDoctors();
        getNextAppointment();
        getPrescription();
        getPastAppointments();
        setRatings();
        
        SetIcon();
    }
    
    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Syringe.png")));
    }
    
    private void getUserInfo()
    {
        Patient patient = (Patient) User.loggedUser;
        String dob = patient.getDOB();
        String gender = patient.getGender();
        Notification notification = patient.getNotification();
        
        if(notification != null)
        {
            JOptionPane.showMessageDialog(this, notification.getMessage(), "Welcome", 
                    JOptionPane.INFORMATION_MESSAGE);
            patient.setNotification(null);
            Patient.saveUsers();
        }
        
        this.txtUserAccountType.setText("Patient");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
        this.txtUserAge.setText(dob);
        this.txtUserGender.setText(gender);
    }
    
    private void getDoctors()
    {
        
        String[] doctors = new String[Doctor.doctors.length + 1];
        doctors[0] = "Select Doctor";
        int i = 1;
        
        for(Doctor doctor : Doctor.doctors)
        {
            String name = doctor.getForename() + " " + doctor.getSurname();
            
            doctors[i] = name;
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(doctors);
        this.cmbDoctor.setModel(model);
        
        DefaultComboBoxModel doctor = new DefaultComboBoxModel(doctors);
        this.cmbRateDoctors.setModel(doctor);
        
        DefaultComboBoxModel rate = new DefaultComboBoxModel(doctors);
        this.cmbDoctorFeedback.setModel(rate);
    }
   
    
    private void getNextAppointment()
    {
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                this.txtNextDate.setText(appointment.getDate());
                String doctor = appointment.getDoctor().getForename() + " " + appointment.getDoctor().getSurname();
                this.txtNextDoctor.setText(doctor);
                break;
            }
        }
    }
    
    private void getPrescription()
    {
        //Prescription newPrescription = new Prescription(DoctorID, PatientID, Notes, Medicine, Quantity, Dosage);
        
        //FOREACH PRESCRIPTION
            //GET PATIENT NAME FROM ID
            //GET DOCTOR NAME FROM ID
            //GET NOTES
            //GET MEDICINE
                //FROM MEDICINE GET NAME
            //GET QUANTITY
            //GET DOSAGE

            //OUTPUT DATA INTO TABLE
        //END FOREACH
        
        for(Prescription prescription : Prescription.prescriptions)
        {
            if(prescription.getPatient().getID().equals(User.loggedUser.getID()))
            {
                Patient patient = prescription.getPatient();
                
                this.txtPatientName.setText(patient.getForename() + " " + patient.getSurname());
                this.txtPatientAddress.setText(patient.getAddress());
                this.txtPatientGender.setText(patient.getGender());
                this.txtPatientDOB.setText(patient.getDOB());
                
                Doctor doctor = prescription.getDoctor();
                
                this.txtDoctorName.setText(doctor.getForename() + " " + doctor.getSurname());
                this.txtDoctorAddress.setText(doctor.getAddress());
                this.txtPrescriptionNotes.setText(prescription.getNotes());
                
                Medicine medicine = prescription.getMedicine();
                
                String[] medicineTbl = {medicine.getName(), Integer.toString(prescription.getQuantity()),
                    prescription.getDosage()};
                
                DefaultTableModel model = (DefaultTableModel) this.tblMedicine.getModel();
                model.addRow(medicineTbl);
                break;
            }
        }
    }
    
    private void getPastAppointments()
    {
        int i = 0;
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                i++;
            }
        }
        
        String[][] pastAppointments = new String[i][3];
        i = 0;
        
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(User.loggedUser.getID()))
            {
                Doctor doctor = pastAppointment.getDoctor();
                String doctorName = doctor.getForename() + " " + doctor.getSurname();
                String date = pastAppointment.getDate();
                String notes = pastAppointment.getPastPrescription().getNotes();
                
                pastAppointments[i][0] = doctorName;
                pastAppointments[i][1] = date;
                pastAppointments[i][2] = notes;
                
                i++;
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) this.tblPastAppointments.getModel();
        for(String[] appointment : pastAppointments)
        {
            model.addRow(appointment);
        }
    }
    
    private void setRatings()
    {
        String[] ratings = {"Select Rating","1","2","3","4","5","6","7","8","9","10"};
        for(String i : ratings)
        {
            DefaultComboBoxModel model = new DefaultComboBoxModel(ratings);
            this.cmbRating.setModel(model);
        }
    }
    
    private void checkPassword(String id, String password)
    {
        
    }
        

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new java.awt.Label();
        menuPatient = new javax.swing.JTabbedPane();
        tabUserInfo = new javax.swing.JPanel();
        lblUserInfo1 = new javax.swing.JLabel();
        lblAccountType1 = new javax.swing.JLabel();
        txtUserAccountType = new javax.swing.JTextField();
        lblUserID1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        lblUserName1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblUserName2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtUserAddress = new javax.swing.JTextArea();
        lblUserAge1 = new javax.swing.JLabel();
        txtUserAge = new javax.swing.JTextField();
        lblUserGender1 = new javax.swing.JLabel();
        txtUserGender = new javax.swing.JTextField();
        tabRequestAccount = new javax.swing.JPanel();
        lblRequestAppointment = new javax.swing.JLabel();
        lblAccountType2 = new javax.swing.JLabel();
        cmbDoctor = new javax.swing.JComboBox<>();
        lblSelectDate = new javax.swing.JLabel();
        txtEnterDate = new javax.swing.JTextField();
        lblSelectTime = new javax.swing.JLabel();
        txtEnterTime = new javax.swing.JTextField();
        lblConfirmAppointment = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        txtDoctor = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        tabViewAppointment = new javax.swing.JPanel();
        lblViewAppointment = new javax.swing.JLabel();
        lblDate3 = new javax.swing.JLabel();
        txtNextDate = new javax.swing.JTextField();
        lblDoctor1 = new javax.swing.JLabel();
        txtNextDoctor = new javax.swing.JTextField();
        tabViewPrescription = new javax.swing.JPanel();
        lblViewPrescription = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        lblPatientAddr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPatientAddress = new javax.swing.JTextArea();
        lblSex = new javax.swing.JLabel();
        txtPatientGender = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtPatientDOB = new javax.swing.JTextField();
        lblDoctorInfo = new javax.swing.JLabel();
        lblDoctorName = new javax.swing.JLabel();
        txtDoctorName = new javax.swing.JTextField();
        lblDoctorAddr = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDoctorAddress = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPrescriptionNotes = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMedicine = new javax.swing.JTable();
        lblDoctorAddr1 = new javax.swing.JLabel();
        tabViewHistory = new javax.swing.JPanel();
        lblViewPrescription1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPastAppointments = new javax.swing.JTable();
        lblPastDoctorName = new javax.swing.JLabel();
        txtPastDoctor = new javax.swing.JTextField();
        lblPastDate = new javax.swing.JLabel();
        txtPastDate = new javax.swing.JTextField();
        lblPastNote = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPastNote = new javax.swing.JTextArea();
        tabDoctorFeedback = new javax.swing.JPanel();
        lblViewAppointment5 = new javax.swing.JLabel();
        lblViewAppointment3 = new javax.swing.JLabel();
        cmbRateDoctors = new javax.swing.JComboBox<>();
        lblRateDoctorName = new javax.swing.JLabel();
        txtRateDoctorName = new javax.swing.JTextField();
        lblOverallRating = new javax.swing.JLabel();
        txtOverallRating = new javax.swing.JTextField();
        lblAddFeedback = new javax.swing.JLabel();
        lblFeedbackDoctor = new javax.swing.JLabel();
        cmbDoctorFeedback = new javax.swing.JComboBox<>();
        lblRateDoctorName1 = new javax.swing.JLabel();
        cmbRating = new javax.swing.JComboBox<>();
        lblRateNote = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtFeedbackNote = new javax.swing.JTextArea();
        btnConfirmFeedback = new javax.swing.JButton();
        tabRequestTermination = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        lblAccountTermination = new javax.swing.JLabel();
        btnTerminate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTerminationID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTerminationPassword = new javax.swing.JPasswordField();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Patient");
        setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        setName("MainMenu"); // NOI18N

        lblMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuPatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblUserInfo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserInfo1.setText("User Information");

        lblAccountType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAccountType1.setText("Account Type:");

        txtUserAccountType.setEditable(false);
        txtUserAccountType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblUserID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserID1.setText("User ID:");

        txtUserID.setEditable(false);
        txtUserID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblUserName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserName1.setText("User Name:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblUserName2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserName2.setText("Address:");

        txtUserAddress.setEditable(false);
        txtUserAddress.setColumns(20);
        txtUserAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserAddress.setRows(5);
        jScrollPane8.setViewportView(txtUserAddress);

        lblUserAge1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserAge1.setText("DOB:");

        txtUserAge.setEditable(false);
        txtUserAge.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblUserGender1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserGender1.setText("User Gender:");

        txtUserGender.setEditable(false);
        txtUserGender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout tabUserInfoLayout = new javax.swing.GroupLayout(tabUserInfo);
        tabUserInfo.setLayout(tabUserInfoLayout);
        tabUserInfoLayout.setHorizontalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserID1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserID))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblAccountType1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserAccountType))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserAge1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserAge))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserInfo1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserGender1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserGender)))
                .addContainerGap())
        );
        tabUserInfoLayout.setVerticalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAccountType1)
                    .addComponent(txtUserAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserID1)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserName1)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName2)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserAge1)
                    .addComponent(txtUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserGender1)
                    .addComponent(txtUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(424, 424, 424))
        );

        menuPatient.addTab("Account Details", tabUserInfo);

        lblRequestAppointment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRequestAppointment.setText("Request Appointment");

        lblAccountType2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAccountType2.setText("Select Doctor:");

        cmbDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDoctor.setSelectedItem(cmbDoctor);
        cmbDoctor.setToolTipText("");
        cmbDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDoctorActionPerformed(evt);
            }
        });

        lblSelectDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectDate.setText("Enter Date:");

        txtEnterDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEnterDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterDateKeyReleased(evt);
            }
        });

        lblSelectTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectTime.setText("Enter Time:");

        txtEnterTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEnterTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterTimeKeyReleased(evt);
            }
        });

        lblConfirmAppointment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblConfirmAppointment.setText("Confirm Appointment");

        lblDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctor.setText("Doctor:");

        txtDoctor.setEditable(false);
        txtDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoctorActionPerformed(evt);
            }
        });

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDate.setText("Date:");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTime.setText("Time:");

        txtTime.setEditable(false);
        txtTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeActionPerformed(evt);
            }
        });

        btnConfirm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnConfirm.setText("Request Appointment");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabRequestAccountLayout = new javax.swing.GroupLayout(tabRequestAccount);
        tabRequestAccount.setLayout(tabRequestAccountLayout);
        tabRequestAccountLayout.setHorizontalGroup(
            tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblAccountType2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblSelectDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnterDate))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblSelectTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnterTime, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRequestAppointment)
                            .addComponent(lblConfirmAppointment))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabRequestAccountLayout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabRequestAccountLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm)))
                .addContainerGap())
        );
        tabRequestAccountLayout.setVerticalGroup(
            tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabRequestAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAccountType2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectDate)
                    .addComponent(txtEnterDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectTime)
                    .addComponent(txtEnterTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfirmAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRequestAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirm)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        menuPatient.addTab("Request Appointment", tabRequestAccount);

        lblViewAppointment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblViewAppointment.setText("View Next Appointment");

        lblDate3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDate3.setText("Date:");

        txtNextDate.setEditable(false);
        txtNextDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNextDate.setText("No Future Appointments\n");
        txtNextDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNextDateActionPerformed(evt);
            }
        });

        lblDoctor1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctor1.setText("Doctor:");

        txtNextDoctor.setEditable(false);
        txtNextDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNextDoctor.setText("No Future Appointments\n");
        txtNextDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNextDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabViewAppointmentLayout = new javax.swing.GroupLayout(tabViewAppointment);
        tabViewAppointment.setLayout(tabViewAppointmentLayout);
        tabViewAppointmentLayout.setHorizontalGroup(
            tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                        .addComponent(lblDate3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextDate, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                    .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                        .addComponent(lblViewAppointment)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabViewAppointmentLayout.createSequentialGroup()
                        .addComponent(lblDoctor1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNextDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabViewAppointmentLayout.setVerticalGroup(
            tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabViewAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNextDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabViewAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor1)
                    .addComponent(txtNextDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(501, Short.MAX_VALUE))
        );

        menuPatient.addTab("View Appointment", tabViewAppointment);

        lblViewPrescription.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblViewPrescription.setText("View Current Prescriptions");

        lblPatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientName.setText("Name:");

        txtPatientName.setEditable(false);
        txtPatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblPatientAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientAddr.setText("Address:");

        txtPatientAddress.setEditable(false);
        txtPatientAddress.setColumns(5);
        txtPatientAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPatientAddress.setRows(5);
        txtPatientAddress.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtPatientAddress);

        lblSex.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSex.setText("Gender:");

        txtPatientGender.setEditable(false);
        txtPatientGender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblDOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDOB.setText("Age:");

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblDoctorInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblDoctorInfo.setText("Prescription Info");

        lblDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctorName.setText("Name:");

        txtDoctorName.setEditable(false);
        txtDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblDoctorAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctorAddr.setText("Address:");

        txtDoctorAddress.setEditable(false);
        txtDoctorAddress.setColumns(5);
        txtDoctorAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDoctorAddress.setRows(5);
        txtDoctorAddress.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtDoctorAddress);

        txtPrescriptionNotes.setEditable(false);
        txtPrescriptionNotes.setColumns(5);
        txtPrescriptionNotes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrescriptionNotes.setRows(5);
        txtPrescriptionNotes.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtPrescriptionNotes);

        tblMedicine.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblMedicine);
        if (tblMedicine.getColumnModel().getColumnCount() > 0) {
            tblMedicine.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblMedicine.getColumnModel().getColumn(1).setPreferredWidth(1);
        }

        lblDoctorAddr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctorAddr1.setText("Notes:");

        javax.swing.GroupLayout tabViewPrescriptionLayout = new javax.swing.GroupLayout(tabViewPrescription);
        tabViewPrescription.setLayout(tabViewPrescriptionLayout);
        tabViewPrescriptionLayout.setHorizontalGroup(
            tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabViewPrescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientName))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblPatientAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblSex, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientGender))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(lblDoctorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoctorName))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblViewPrescription)
                            .addComponent(lblDoctorInfo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabViewPrescriptionLayout.createSequentialGroup()
                        .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDoctorAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDoctorAddr1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        tabViewPrescriptionLayout.setVerticalGroup(
            tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewPrescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewPrescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientAddr)
                    .addGroup(tabViewPrescriptionLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSex))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoctorInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctorName)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDoctorAddr)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewPrescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoctorAddr1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPatient.addTab("View Current Prescriptions", tabViewPrescription);

        lblViewPrescription1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblViewPrescription1.setText("View Appointment History");

        tblPastAppointments.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPastAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor", "Date", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPastAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPastAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPastAppointments);
        if (tblPastAppointments.getColumnModel().getColumnCount() > 0) {
            tblPastAppointments.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblPastAppointments.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblPastAppointments.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        lblPastDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastDoctorName.setText("Doctor:");

        txtPastDoctor.setEditable(false);
        txtPastDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblPastDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastDate.setText("Date:");

        txtPastDate.setEditable(false);
        txtPastDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblPastNote.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastNote.setText("Note:");

        txtPastNote.setEditable(false);
        txtPastNote.setColumns(20);
        txtPastNote.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPastNote.setLineWrap(true);
        txtPastNote.setRows(5);
        jScrollPane6.setViewportView(txtPastNote);

        javax.swing.GroupLayout tabViewHistoryLayout = new javax.swing.GroupLayout(tabViewHistory);
        tabViewHistory.setLayout(tabViewHistoryLayout);
        tabViewHistoryLayout.setHorizontalGroup(
            tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                    .addGroup(tabViewHistoryLayout.createSequentialGroup()
                        .addComponent(lblViewPrescription1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabViewHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastDoctorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPastDoctor))
                    .addGroup(tabViewHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPastDate))
                    .addGroup(tabViewHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6)))
                .addContainerGap())
        );
        tabViewHistoryLayout.setVerticalGroup(
            tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabViewHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewPrescription1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPastDoctorName)
                    .addComponent(txtPastDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPastDate)
                    .addComponent(txtPastDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPastNote)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );

        menuPatient.addTab("View Appointment History", tabViewHistory);

        lblViewAppointment5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblViewAppointment5.setText("View Doctor Ratings");

        lblViewAppointment3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblViewAppointment3.setText("Select Doctor:");

        cmbRateDoctors.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbRateDoctors.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRateDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRateDoctorsActionPerformed(evt);
            }
        });

        lblRateDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRateDoctorName.setText("Name:");

        txtRateDoctorName.setEditable(false);
        txtRateDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRateDoctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRateDoctorNameActionPerformed(evt);
            }
        });

        lblOverallRating.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblOverallRating.setText("Overall Rating:");

        txtOverallRating.setEditable(false);
        txtOverallRating.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtOverallRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOverallRatingActionPerformed(evt);
            }
        });

        lblAddFeedback.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAddFeedback.setText("Give Feedback");

        lblFeedbackDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFeedbackDoctor.setText("Select Doctor:");

        cmbDoctorFeedback.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbDoctorFeedback.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDoctorFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDoctorFeedbackActionPerformed(evt);
            }
        });

        lblRateDoctorName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRateDoctorName1.setText("Rating:");

        cmbRating.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Rating", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRatingActionPerformed(evt);
            }
        });

        lblRateNote.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRateNote.setText("Notes:");

        txtFeedbackNote.setColumns(20);
        txtFeedbackNote.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFeedbackNote.setLineWrap(true);
        txtFeedbackNote.setRows(3);
        jScrollPane7.setViewportView(txtFeedbackNote);

        btnConfirmFeedback.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnConfirmFeedback.setText("Confirm Feedback");
        btnConfirmFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmFeedbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabDoctorFeedbackLayout = new javax.swing.GroupLayout(tabDoctorFeedback);
        tabDoctorFeedback.setLayout(tabDoctorFeedbackLayout);
        tabDoctorFeedbackLayout.setHorizontalGroup(
            tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addComponent(lblViewAppointment3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRateDoctors, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addComponent(lblRateDoctorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRateDoctorName, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE))
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addComponent(lblOverallRating)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOverallRating, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE))
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addComponent(lblRateDoctorName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRating, 0, 1012, Short.MAX_VALUE))
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblViewAppointment5)
                            .addComponent(lblAddFeedback)
                            .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                                .addComponent(lblFeedbackDoctor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDoctorFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabDoctorFeedbackLayout.createSequentialGroup()
                        .addComponent(lblRateNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDoctorFeedbackLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabDoctorFeedbackLayout.setVerticalGroup(
            tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDoctorFeedbackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblViewAppointment5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblViewAppointment3)
                    .addComponent(cmbRateDoctors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRateDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRateDoctorName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverallRating)
                    .addComponent(txtOverallRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddFeedback)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFeedbackDoctor)
                    .addComponent(cmbDoctorFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateDoctorName1)
                    .addComponent(cmbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDoctorFeedbackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRateNote)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmFeedback)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        menuPatient.addTab("Doctor Feedback", tabDoctorFeedback);

        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAccountTermination.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAccountTermination.setText("Account Termination");

        btnTerminate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTerminate.setText("Request Account Termination");
        btnTerminate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("User ID:");

        txtTerminationID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTerminationID.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Password:");

        txtTerminationPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminationID))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(lblAccountTermination)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminationPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTerminate)))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAccountTermination)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTerminationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTerminationPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTerminate)
                .addContainerGap(449, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabRequestTerminationLayout = new javax.swing.GroupLayout(tabRequestTermination);
        tabRequestTermination.setLayout(tabRequestTerminationLayout);
        tabRequestTerminationLayout.setHorizontalGroup(
            tabRequestTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabRequestTerminationLayout.setVerticalGroup(
            tabRequestTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRequestTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuPatient.addTab("Request Account Termination", tabRequestTermination);

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPatient)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDoctorActionPerformed
        String doctor = (String) this.cmbDoctor.getSelectedItem();
        if(doctor != "Select Doctor") {
            this.txtDoctor.setText(doctor);
        }
    }//GEN-LAST:event_cmbDoctorActionPerformed

    private void txtDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoctorActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        String doctorName = this.txtDoctor.getText();
        String enteredDate = this.txtDate.getText();
        String enteredTime = this.txtTime.getText();
        
        if(!(enteredDate.equals("") || enteredTime.equals(""))){
            String date = this.txtDate.getText() + " " + this.txtTime.getText();
        
            try{
                Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:MM").parse(date);

                for(Doctor doctor : Doctor.doctors){
                    String doctorForename = doctor.getForename();
                    String doctorSurname = doctor.getSurname();
                    String name = doctorForename + " " + doctorSurname;

                    if(doctorName.equals(name)){
                        AppointmentRequest newRequest = new AppointmentRequest(doctor, (Patient) User.loggedUser, date);
                        
                        newRequest.addAppointmentRequest(newRequest);
                        
                        for(Secretary secretary : Secretary.secretarys){
                            secretary.setNotification(new Notification("You have new Requests:"
                                + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                        }
                        User.saveUsers();
                        
                        JOptionPane.showMessageDialog(this, "Submitted", "SUCCESS", 
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        this.cmbDoctor.setSelectedIndex(0);
                        this.txtEnterDate.setText("");
                        this.txtEnterTime.setText("");
                        this.txtDoctor.setText("");
                        this.txtDate.setText("");
                        this.txtTime.setText("");
                    }
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Please enter the right format", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the right format", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }   
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void txtNextDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNextDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNextDoctorActionPerformed

    private void txtNextDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNextDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNextDateActionPerformed

    private void tblPastAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPastAppointmentsMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < 3; i++)
        {
            row = this.tblPastAppointments.getSelectedRow();
            String value = tblPastAppointments.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPastDoctor.setText(data[0]);
        this.txtPastDate.setText(data[1]);
        this.txtPastNote.setText(data[2]);
    }//GEN-LAST:event_tblPastAppointmentsMouseClicked

    private void btnTerminateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminateActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "WARNING", 
                JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if(confirm == 0)
        {
            String userID = this.txtTerminationID.getText();
            String password = Login.hashPassword(this.txtTerminationPassword.getText());
            
            if((userID.equals(User.loggedUser.getID())) && (password.equals(User.loggedUser.getPassword())))
            {
                TerminationRequest newTerminationRequest = new TerminationRequest((Patient)User.loggedUser);
                newTerminationRequest.addTerminationRequest(newTerminationRequest);
                
                for(Secretary secretary : Secretary.secretarys)
                {
                    secretary.setNotification(new Notification("You have new Requests:"
                        + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                }
                User.saveUsers();
                
                JOptionPane.showMessageDialog(this, "Complete", "SUCCESS", 
                        JOptionPane.INFORMATION_MESSAGE);
                this.txtTerminationID.setText("");
                this.txtTerminationPassword.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Incorrect username/password", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_btnTerminateActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are You Sure", "Confirmation", JOptionPane.WARNING_MESSAGE);

        if(confirm == 0)
        {
            User.loggedUser = null;

            User.saveUsers();

            new Login().setVisible(true);

            this.setVisible(false);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtEnterDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterDateKeyReleased
        this.txtDate.setText(this.txtEnterDate.getText());
    }//GEN-LAST:event_txtEnterDateKeyReleased

    private void txtEnterTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterTimeKeyReleased
        this.txtTime.setText(this.txtEnterTime.getText());
    }//GEN-LAST:event_txtEnterTimeKeyReleased

    private void cmbRateDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRateDoctorsActionPerformed
        String doctorName = (String) this.cmbRateDoctors.getSelectedItem();

        for(Doctor doctor : Doctor.doctors)
        {
            String name = doctor.getForename() + " " + doctor.getSurname();
            if(name.equals(doctorName))
            {
                float average = 0;
                float i = 0;
                for(Feedback feedback : Feedback.feedback)
                {
                    if(feedback.getDoctor().getID().equals(doctor.getID()))
                    {
                        average += feedback.getRating();
                        i++;
                    }
                }
                average = average / i;
                this.txtRateDoctorName.setText(doctorName);
                this.txtOverallRating.setText(Float.toString(average));
                break;
            }
            else
            {
                this.txtRateDoctorName.setText("");
                this.txtOverallRating.setText("");
            }
        }
    }//GEN-LAST:event_cmbRateDoctorsActionPerformed

    private void txtOverallRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOverallRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOverallRatingActionPerformed

    private void txtRateDoctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRateDoctorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRateDoctorNameActionPerformed

    private void btnConfirmFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmFeedbackActionPerformed

        String doctorName = (String) this.cmbDoctorFeedback.getSelectedItem();
        String rate = (String) this.cmbRating.getSelectedItem();
        String note = this.txtFeedbackNote.getText();
        if(!((rate.equals("Select Rating")) && (doctorName.equals("Select Doctor"))))
        {
            //Add to doctor's record
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "WARNING",
                JOptionPane.YES_NO_CANCEL_OPTION);

            if(confirm == 0)
            {
                for(Doctor doctor : Doctor.doctors)
                {
                    String name = doctor.getForename() + " " + doctor.getSurname();
                    if(name.equals(doctorName))
                    {
                        Feedback newFeedback = new Feedback(doctor, Integer.parseInt(rate), note);
                        newFeedback.addFeedback(newFeedback);

                        for(Admin admin : Admin.admins)
                        {
                            admin.setNotification(new Notification("You have new Feedback Requests"));
                        }
                        User.saveUsers();

                        this.cmbDoctorFeedback.setSelectedIndex(0);
                        this.cmbRating.setSelectedIndex(0);
                        this.txtFeedbackNote.setText("");
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Select A Doctor and Rating.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmFeedbackActionPerformed

    private void cmbRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRatingActionPerformed

    private void cmbDoctorFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDoctorFeedbackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDoctorFeedbackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientFunctions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnConfirmFeedback;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnTerminate;
    private javax.swing.JComboBox<String> cmbDoctor;
    private javax.swing.JComboBox<String> cmbDoctorFeedback;
    private javax.swing.JComboBox<String> cmbRateDoctors;
    private javax.swing.JComboBox<String> cmbRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblAccountTermination;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAccountType2;
    private javax.swing.JLabel lblAddFeedback;
    private javax.swing.JLabel lblConfirmAppointment;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate3;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblDoctor1;
    private javax.swing.JLabel lblDoctorAddr;
    private javax.swing.JLabel lblDoctorAddr1;
    private javax.swing.JLabel lblDoctorInfo;
    private javax.swing.JLabel lblDoctorName;
    private javax.swing.JLabel lblFeedbackDoctor;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblOverallRating;
    private javax.swing.JLabel lblPastDate;
    private javax.swing.JLabel lblPastDoctorName;
    private javax.swing.JLabel lblPastNote;
    private javax.swing.JLabel lblPatientAddr;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblRateDoctorName;
    private javax.swing.JLabel lblRateDoctorName1;
    private javax.swing.JLabel lblRateNote;
    private javax.swing.JLabel lblRequestAppointment;
    private javax.swing.JLabel lblSelectDate;
    private javax.swing.JLabel lblSelectTime;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUserAge1;
    private javax.swing.JLabel lblUserGender1;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JLabel lblViewAppointment;
    private javax.swing.JLabel lblViewAppointment3;
    private javax.swing.JLabel lblViewAppointment5;
    private javax.swing.JLabel lblViewPrescription;
    private javax.swing.JLabel lblViewPrescription1;
    private javax.swing.JTabbedPane menuPatient;
    private javax.swing.JPanel tabDoctorFeedback;
    private javax.swing.JPanel tabRequestAccount;
    private javax.swing.JPanel tabRequestTermination;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JPanel tabViewAppointment;
    private javax.swing.JPanel tabViewHistory;
    private javax.swing.JPanel tabViewPrescription;
    private javax.swing.JTable tblMedicine;
    private javax.swing.JTable tblPastAppointments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextArea txtDoctorAddress;
    private javax.swing.JTextField txtDoctorName;
    private javax.swing.JTextField txtEnterDate;
    private javax.swing.JTextField txtEnterTime;
    private javax.swing.JTextArea txtFeedbackNote;
    private javax.swing.JTextField txtNextDate;
    private javax.swing.JTextField txtNextDoctor;
    private javax.swing.JTextField txtOverallRating;
    private javax.swing.JTextField txtPastDate;
    private javax.swing.JTextField txtPastDoctor;
    private javax.swing.JTextArea txtPastNote;
    private javax.swing.JTextArea txtPatientAddress;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientGender;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextArea txtPrescriptionNotes;
    private javax.swing.JTextField txtRateDoctorName;
    private javax.swing.JTextField txtTerminationID;
    private javax.swing.JPasswordField txtTerminationPassword;
    private javax.swing.JTextField txtTime;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserAge;
    private javax.swing.JTextField txtUserGender;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
