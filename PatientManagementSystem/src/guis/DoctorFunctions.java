
package guis;

import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import users.*;
import system.*;


public class DoctorFunctions extends javax.swing.JFrame {


    public DoctorFunctions() {
        initComponents();
        getUserInfo();
        getAppointments();
        setAppointments();
        getPastAppointments();
        getPatients();
        setMedicine();
        
        SetIcon();
    }
    
    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Syringe.png")));
    }
    
    private void getUserInfo()
    {
        Notification notification = User.loggedUser.getNotification();
        
        if(notification != null)
        {
            JOptionPane.showMessageDialog(this, notification.getMessage(), "WELCOME", 
                    JOptionPane.INFORMATION_MESSAGE);
            User.loggedUser.setNotification(null);
            User.saveUsers();
        }
        
        this.txtUserAccountType.setText("Doctor");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
    }
    
    private void getAppointments()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblAppointments.getModel();
        int rows = model.getRowCount();
        if(rows > 0)
        {
            for (int i = rows - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }
        }
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                String patientName = "";
                
                for(Patient patient : Patient.patients)
                {
                    if(patient.getID().equals(appointment.getPatient().getID()))
                    {
                        patientName = patient.getForename() + " " + patient.getSurname();
                    }
                }          
                String[] data = {
                    appointment.getPatient().getID(),
                    patientName,
                    appointment.getDate()
                };
                model.addRow(data);
            }
        }
    }
    
    private void setAppointments()
    {  
        int i = 0;
        
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                i++;
            }
        }
        
        Appointment[] doctorAppointments = new Appointment[i];
        i = 0;
        for(Appointment appointment : Appointment.appointments)
        {
            if(appointment.getDoctor().getID().equals(User.loggedUser.getID()))
            {
                doctorAppointments[i] = appointment;
                i++;
            }
        }
        
        String[] appointments = new String[doctorAppointments.length + 1];
        appointments[0] = "Select Appointment";
        i = 1;
        for(Appointment appointment : doctorAppointments)
        {
            for(Patient patient : Patient.patients)
            {   
                if(patient.getID().equals(appointment.getPatient().getID()))
                {
                    appointments[i] = patient.getID();
                    i++;
                    break;
                } 
            }
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(appointments);
        this.cmbSelectAppointment.setModel(model);
    }
    
    private void getPastAppointments()
    {
        String[] patients = new String[Patient.patients.length + 1];
        patients[0] = "Select Patient";
        int i = 1;
        for(Patient patient : Patient.patients)
        {
            patients[i] = patient.getID();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(patients);
        this.cmbSelectPatientID.setModel(model);
    }
    
    private void setPastAppointments(String id)
    {
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
        {
            if(pastAppointment.getPatient().getID().equals(id))
            {   
                String patientName = pastAppointment.getPatient().getForename() + " " + pastAppointment.getPatient().getSurname();
                this.txtPatientName.setText(patientName);
                this.txtPatientDOB.setText(pastAppointment.getPatient().getDOB());
                String[] data = {
                    pastAppointment.getDoctor().getID(),
                    pastAppointment.getDate(),
                    pastAppointment.getPastPrescription().getNotes()
                };
                model.addRow(data);
            }
        }  
    }
    
    private void getPatients()
    {
        String[] patients = new String[Patient.patients.length + 1];
        patients[0] = "Select Patient";
        int i = 1;
        for(Patient patient : Patient.patients)
        {
            patients[i] = patient.getID();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(patients);
        this.cmbSelectPatient.setModel(model);
    }
    
    private void removeAppointment(String id)
    {
        for(Appointment appointment : Appointment.appointments)
        {
            if((appointment.getPatient().getID().equals(id)) && (appointment.getDoctor().getID().equals(User.loggedUser.getID())))
            {
                appointment.removeAppointment(appointment);
            }
        }
        this.txtPrescriptionPatientID.setText("");
        this.txtPrescriptionPatientName.setText("");
        this.txtPrescriptionPatientAddress.setText("");
        this.txtPrescriptionPatientGender.setText("");
        this.txtPrescriptionPatientDOB.setText("");
        this.txtPrescriptionNotes.setText("");
        this.txtSelectedAppointmentDate.setText("");
        
        DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
        int rows = model.getRowCount();
        if(rows > 0)
        {
            for (int i = rows - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }
        }
    }
    
    private void prescribeMedicine(Prescription prescription)
    {
        this.menuDoctor.setSelectedIndex(5);
        this.txtMedicinePatientID.setText(prescription.getPatient().getID());
        String patientName = prescription.getPatient().getForename() + " " + prescription.getPatient().getSurname();
        this.txtMedicinePatientName.setText(patientName);
    }
    
    private void setMedicine()
    {
        String[] medicines = new String[Medicine.medicines.length + 1];
        medicines[0] = "Select Medicine";
        int i = 1;
        for(Medicine medicine : Medicine.medicines)
        {
            medicines[i] = medicine.getName();
            i++;
        }
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(medicines);
        this.cmbMedicineName.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMain = new java.awt.Label();
        menuDoctor = new javax.swing.JTabbedPane();
        tabUserInfo = new javax.swing.JPanel();
        lblUserInfo1 = new javax.swing.JLabel();
        lblAccountType1 = new javax.swing.JLabel();
        txtUserAccountType = new javax.swing.JTextField();
        lblUserID1 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        lblUserName1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblUserName2 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtUserAddress = new javax.swing.JTextArea();
        tabViewAppointments = new javax.swing.JPanel();
        lblAppointments = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        lblNextDate = new javax.swing.JLabel();
        lblNextPatientID = new javax.swing.JLabel();
        txtAppointmentPatientID = new javax.swing.JTextField();
        txtAppointmentDate = new javax.swing.JTextField();
        lblNextPatientName = new javax.swing.JLabel();
        txtAppointmentPatientName = new javax.swing.JTextField();
        tabMakeNotes = new javax.swing.JPanel();
        lblAppointments1 = new javax.swing.JLabel();
        lblSelectAppointment = new javax.swing.JLabel();
        cmbSelectAppointment = new javax.swing.JComboBox<>();
        lblSelectedAppointmentDate = new javax.swing.JLabel();
        txtSelectedAppointmentDate = new javax.swing.JTextField();
        lblPrescription = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        txtPrescriptionPatientID = new javax.swing.JTextField();
        lblPrescriptionPatientName = new javax.swing.JLabel();
        txtPrescriptionPatientName = new javax.swing.JTextField();
        lblPrescriptionPatientAddress = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPrescriptionPatientAddress = new javax.swing.JTextArea();
        lblPrescriptionPatientGender = new javax.swing.JLabel();
        txtPrescriptionPatientGender = new javax.swing.JTextField();
        lblPrescriptionPatientGender1 = new javax.swing.JLabel();
        txtPrescriptionPatientDOB = new javax.swing.JTextField();
        lblPrescriptionNotes = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPrescriptionNotes = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPrescriptionMedicine = new javax.swing.JTable();
        btnConcludeAppointment = new javax.swing.JButton();
        btnSaveNotes = new javax.swing.JButton();
        tabPatientHistory = new javax.swing.JPanel();
        lblPatientHistory = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPatientHistory = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearchPatientID = new javax.swing.JTextField();
        cmbSelectPatientID = new javax.swing.JComboBox<>();
        lblPatientName = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPatientDOB = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblDoctorName = new javax.swing.JLabel();
        txtDoctorName = new javax.swing.JTextField();
        lblPastDate = new javax.swing.JLabel();
        txtPastDate = new javax.swing.JTextField();
        lblPastNotes = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtPastNotes = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPastPrescription = new javax.swing.JTable();
        lblPastDate1 = new javax.swing.JLabel();
        tabCreateAppointment = new javax.swing.JPanel();
        lblRequestAppointment = new javax.swing.JLabel();
        lblAccountType2 = new javax.swing.JLabel();
        cmbSelectPatient = new javax.swing.JComboBox<>();
        lblSelectDate = new javax.swing.JLabel();
        txtEnterDate = new javax.swing.JTextField();
        lblSelectTime = new javax.swing.JLabel();
        txtEnterTime = new javax.swing.JTextField();
        lblConfirmAppointment = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        txtSelectedPatient = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        txtEnteredDate = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();
        txtEnteredTime = new javax.swing.JTextField();
        btnConfirmAppointment = new javax.swing.JButton();
        tabPrescribeMedicine = new javax.swing.JPanel();
        lblPrescribeMedicine = new javax.swing.JLabel();
        lblMedicineName = new javax.swing.JLabel();
        cmbMedicineName = new javax.swing.JComboBox<>();
        lblMedicineName2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lblMedicineName3 = new javax.swing.JLabel();
        txtDosage = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblMedicinePatientID = new javax.swing.JLabel();
        txtMedicinePatientID = new javax.swing.JTextField();
        lblMedicinePatientName = new javax.swing.JLabel();
        txtMedicinePatientName = new javax.swing.JTextField();
        lblMedicineSelected = new javax.swing.JLabel();
        txtMedicineSelected = new javax.swing.JTextField();
        lblMedicineSelected1 = new javax.swing.JLabel();
        txtMedicineQuantity = new javax.swing.JTextField();
        lblMedicineSelected2 = new javax.swing.JLabel();
        txtMedicineDosage = new javax.swing.JTextField();
        btnPrescribe = new javax.swing.JButton();
        tabAddNewMedicine = new javax.swing.JPanel();
        lblPrescribeMedicine1 = new javax.swing.JLabel();
        lblAddMedicineName = new javax.swing.JLabel();
        txtAddMedicineName = new javax.swing.JTextField();
        lblAddMedicineQuantity = new javax.swing.JLabel();
        txtAddMedicineQauntity = new javax.swing.JTextField();
        btnAddMedicine = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doctor");

        lblMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuDoctor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblUserInfo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserInfo1.setText("Account Details");

        lblAccountType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAccountType1.setText("Account Type:");

        txtUserAccountType.setEditable(false);
        txtUserAccountType.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblUserID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserID1.setText("User ID:");

        txtUserID.setEditable(false);
        txtUserID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblUserName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserName1.setText("User Name:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblUserName2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUserName2.setText("Address:");

        txtUserAddress.setEditable(false);
        txtUserAddress.setColumns(20);
        txtUserAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtUserAddress.setRows(5);
        jScrollPane10.setViewportView(txtUserAddress);

        javax.swing.GroupLayout tabUserInfoLayout = new javax.swing.GroupLayout(tabUserInfo);
        tabUserInfo.setLayout(tabUserInfoLayout);
        tabUserInfoLayout.setHorizontalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblAccountType1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserAccountType))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserID1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserID))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserInfo1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName2)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabUserInfoLayout.setVerticalGroup(
            tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUserInfoLayout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName2)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(356, Short.MAX_VALUE))
        );

        menuDoctor.addTab("Account Details", tabUserInfo);

        lblAppointments.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAppointments.setText("Appointments");

        tblAppointments.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Patient Name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        lblNextDate.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextDate.setText("Date:");

        lblNextPatientID.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextPatientID.setText("Patient ID:");

        txtAppointmentPatientID.setEditable(false);
        txtAppointmentPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        txtAppointmentDate.setEditable(false);
        txtAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblNextPatientName.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lblNextPatientName.setText("Patient Name:");

        txtAppointmentPatientName.setEditable(false);
        txtAppointmentPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout tabViewAppointmentsLayout = new javax.swing.GroupLayout(tabViewAppointments);
        tabViewAppointments.setLayout(tabViewAppointmentsLayout);
        tabViewAppointmentsLayout.setHorizontalGroup(
            tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                        .addComponent(lblNextDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentDate))
                    .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                        .addComponent(lblNextPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentPatientName))
                    .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                        .addComponent(lblNextPatientID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentPatientID))
                    .addComponent(lblAppointments)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        tabViewAppointmentsLayout.setVerticalGroup(
            tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabViewAppointmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAppointments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNextPatientID)
                    .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNextPatientName)
                    .addComponent(txtAppointmentPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabViewAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNextDate)
                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        menuDoctor.addTab("View Appointments", tabViewAppointments);

        lblAppointments1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAppointments1.setText("Appointment Notes");

        lblSelectAppointment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectAppointment.setText("Select Appointment:");

        cmbSelectAppointment.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbSelectAppointment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectAppointmentActionPerformed(evt);
            }
        });

        lblSelectedAppointmentDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectedAppointmentDate.setText("Appointment Date:");

        txtSelectedAppointmentDate.setEditable(false);
        txtSelectedAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescription.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblPrescription.setText("Prescription");

        lblPatientID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientID.setText("Patient ID:");

        txtPrescriptionPatientID.setEditable(false);
        txtPrescriptionPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescriptionPatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPrescriptionPatientName.setText("Patient Name:");

        txtPrescriptionPatientName.setEditable(false);
        txtPrescriptionPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescriptionPatientAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPrescriptionPatientAddress.setText("Address:");

        txtPrescriptionPatientAddress.setEditable(false);
        txtPrescriptionPatientAddress.setColumns(20);
        txtPrescriptionPatientAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPrescriptionPatientAddress.setRows(5);
        jScrollPane2.setViewportView(txtPrescriptionPatientAddress);

        lblPrescriptionPatientGender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPrescriptionPatientGender.setText("Gender:");

        txtPrescriptionPatientGender.setEditable(false);
        txtPrescriptionPatientGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescriptionPatientGender1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPrescriptionPatientGender1.setText("DOB:");

        txtPrescriptionPatientDOB.setEditable(false);
        txtPrescriptionPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPrescriptionNotes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPrescriptionNotes.setText("Notes");

        txtPrescriptionNotes.setColumns(20);
        txtPrescriptionNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPrescriptionNotes.setLineWrap(true);
        txtPrescriptionNotes.setRows(5);
        jScrollPane3.setViewportView(txtPrescriptionNotes);

        tblPrescriptionMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPrescriptionMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblPrescriptionMedicine);

        btnConcludeAppointment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnConcludeAppointment.setText("End Appointment");

        btnSaveNotes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSaveNotes.setText("Save Notes");

        javax.swing.GroupLayout tabMakeNotesLayout = new javax.swing.GroupLayout(tabMakeNotes);
        tabMakeNotes.setLayout(tabMakeNotesLayout);
        tabMakeNotesLayout.setHorizontalGroup(
            tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblSelectedAppointmentDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSelectedAppointmentDate))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPatientID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrescriptionPatientID))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPrescriptionPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrescriptionPatientName))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPrescriptionPatientAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPrescriptionPatientGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrescriptionPatientGender))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPrescriptionPatientGender1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrescriptionPatientDOB))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(lblPrescriptionNotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAppointments1)
                            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                                .addComponent(lblSelectAppointment)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSelectAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPrescription))
                        .addGap(0, 551, Short.MAX_VALUE))
                    .addGroup(tabMakeNotesLayout.createSequentialGroup()
                        .addComponent(btnConcludeAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabMakeNotesLayout.setVerticalGroup(
            tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMakeNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAppointments1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectAppointment)
                    .addComponent(cmbSelectAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectedAppointmentDate)
                    .addComponent(txtSelectedAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrescription)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientID)
                    .addComponent(txtPrescriptionPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrescriptionPatientName)
                    .addComponent(txtPrescriptionPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrescriptionPatientAddress)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrescriptionPatientGender)
                    .addComponent(txtPrescriptionPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrescriptionPatientGender1)
                    .addComponent(txtPrescriptionPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrescriptionNotes)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMakeNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveNotes)
                    .addComponent(btnConcludeAppointment))
                .addGap(86, 86, 86))
        );

        menuDoctor.addTab("Make Notes", tabMakeNotes);

        lblPatientHistory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPatientHistory.setText("Patient History");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Select Patient:");

        tblPatientHistory.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPatientHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor ID", "Date", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatientHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientHistoryMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPatientHistory);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Search Patient ID:");

        txtSearchPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        cmbSelectPatientID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbSelectPatientID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectPatientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectPatientIDActionPerformed(evt);
            }
        });

        lblPatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientName.setText("Name:");

        txtPatientName.setEditable(false);
        txtPatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("DOB:");

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblDoctorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctorName.setText("Doctor:");

        txtDoctorName.setEditable(false);
        txtDoctorName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPastDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastDate.setText("Date:");

        txtPastDate.setEditable(false);
        txtPastDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPastNotes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastNotes.setText("Notes:");

        txtPastNotes.setColumns(20);
        txtPastNotes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPastNotes.setRows(5);
        jScrollPane6.setViewportView(txtPastNotes);

        tblPastPrescription.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPastPrescription.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblPastPrescription);

        lblPastDate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPastDate1.setText("Prescription:");

        javax.swing.GroupLayout tabPatientHistoryLayout = new javax.swing.GroupLayout(tabPatientHistory);
        tabPatientHistory.setLayout(tabPatientHistoryLayout);
        tabPatientHistoryLayout.setHorizontalGroup(
            tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchPatientID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSelectPatientID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblPatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientName))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientDOB))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblDoctorName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDoctorName))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPastDate))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblPatientHistory)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastNotes)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane6)
                        .addGap(6, 6, 6))
                    .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                        .addComponent(lblPastDate1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7)))
                .addContainerGap())
        );
        tabPatientHistoryLayout.setVerticalGroup(
            tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPatientHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPatientHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSelectPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSearchPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctorName)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPastDate)
                    .addComponent(txtPastDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPastNotes)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPastDate1)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(229, 229, 229))
        );

        menuDoctor.addTab("Patient History", tabPatientHistory);

        lblRequestAppointment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRequestAppointment.setText("Create Appointment");

        lblAccountType2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAccountType2.setText("Select Patient:");

        cmbSelectPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbSelectPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSelectPatient.setToolTipText("");
        cmbSelectPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectPatientActionPerformed(evt);
            }
        });

        lblSelectDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectDate.setText("Enter Date:");

        txtEnterDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnterDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterDateKeyReleased(evt);
            }
        });

        lblSelectTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSelectTime.setText("Enter Time:");

        txtEnterTime.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnterTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEnterTimeKeyReleased(evt);
            }
        });

        lblConfirmAppointment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblConfirmAppointment.setText("Confirm Appointment");

        lblPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatient.setText("Patient:");

        txtSelectedPatient.setEditable(false);
        txtSelectedPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtSelectedPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSelectedPatientActionPerformed(evt);
            }
        });

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDate.setText("Date:");

        txtEnteredDate.setEditable(false);
        txtEnteredDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnteredDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnteredDateActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTime.setText("Time:");

        txtEnteredTime.setEditable(false);
        txtEnteredTime.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtEnteredTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnteredTimeActionPerformed(evt);
            }
        });

        btnConfirmAppointment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnConfirmAppointment.setText("Confirm Appointment");

        javax.swing.GroupLayout tabCreateAppointmentLayout = new javax.swing.GroupLayout(tabCreateAppointment);
        tabCreateAppointment.setLayout(tabCreateAppointmentLayout);
        tabCreateAppointmentLayout.setHorizontalGroup(
            tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblAccountType2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSelectPatient, 0, 961, Short.MAX_VALUE))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblSelectDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnterDate, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblSelectTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnterTime))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSelectedPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnteredDate))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRequestAppointment)
                            .addComponent(lblConfirmAppointment))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabCreateAppointmentLayout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnteredTime))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCreateAppointmentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabCreateAppointmentLayout.setVerticalGroup(
            tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCreateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAccountType2)
                    .addComponent(cmbSelectPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectDate)
                    .addComponent(txtEnterDate, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectTime)
                    .addComponent(txtEnterTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfirmAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatient)
                    .addComponent(txtSelectedPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(txtEnteredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCreateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime)
                    .addComponent(txtEnteredTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmAppointment)
                .addGap(402, 402, 402))
        );

        menuDoctor.addTab("Create Appointment", tabCreateAppointment);

        lblPrescribeMedicine.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPrescribeMedicine.setText("Prescribe Medicine");

        lblMedicineName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineName.setText("Select Medicine:");

        cmbMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cmbMedicineName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbMedicineName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMedicineNameActionPerformed(evt);
            }
        });

        lblMedicineName2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineName2.setText("Enter Quantity:");

        txtQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        lblMedicineName3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineName3.setText("Enter Dosage:");

        txtDosage.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtDosage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDosageKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Patient Details");

        lblMedicinePatientID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicinePatientID.setText("Patient ID:");

        txtMedicinePatientID.setEditable(false);
        txtMedicinePatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicinePatientName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicinePatientName.setText("Patient Name:");

        txtMedicinePatientName.setEditable(false);
        txtMedicinePatientName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineSelected.setText("Selected Medicine:");

        txtMedicineSelected.setEditable(false);
        txtMedicineSelected.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineSelected1.setText("Quantity:");

        txtMedicineQuantity.setEditable(false);
        txtMedicineQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineSelected2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineSelected2.setText("Dosage:");

        txtMedicineDosage.setEditable(false);
        txtMedicineDosage.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnPrescribe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnPrescribe.setText("Prescribe Medication");
        btnPrescribe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrescribeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabPrescribeMedicineLayout = new javax.swing.GroupLayout(tabPrescribeMedicine);
        tabPrescribeMedicine.setLayout(tabPrescribeMedicineLayout);
        tabPrescribeMedicineLayout.setHorizontalGroup(
            tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMedicineName, 0, 953, Short.MAX_VALUE))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineName3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDosage))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicinePatientID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicinePatientID))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicinePatientName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicinePatientName))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineSelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicineSelected))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineSelected1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicineQuantity))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrescribeMedicine)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(lblMedicineSelected2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicineDosage))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabPrescribeMedicineLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrescribe, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabPrescribeMedicineLayout.setVerticalGroup(
            tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrescribeMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrescribeMedicine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName)
                    .addComponent(cmbMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName2)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName3)
                    .addComponent(txtDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicinePatientID)
                    .addComponent(txtMedicinePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicinePatientName)
                    .addComponent(txtMedicinePatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected)
                    .addComponent(txtMedicineSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected1)
                    .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineSelected2)
                    .addComponent(txtMedicineDosage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrescribe)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        menuDoctor.addTab("Prescribe Medicine", tabPrescribeMedicine);

        lblPrescribeMedicine1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPrescribeMedicine1.setText("Add New Medicine");

        lblAddMedicineName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAddMedicineName.setText("Medicine Name:");

        txtAddMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtAddMedicineName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddMedicineNameKeyReleased(evt);
            }
        });

        lblAddMedicineQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAddMedicineQuantity.setText("Enter Quantity:");

        txtAddMedicineQauntity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtAddMedicineQauntity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddMedicineQauntityKeyReleased(evt);
            }
        });

        btnAddMedicine.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAddMedicine.setText("Add New Medicine");
        btnAddMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMedicineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabAddNewMedicineLayout = new javax.swing.GroupLayout(tabAddNewMedicine);
        tabAddNewMedicine.setLayout(tabAddNewMedicineLayout);
        tabAddNewMedicineLayout.setHorizontalGroup(
            tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                        .addComponent(lblAddMedicineName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddMedicineName, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE))
                    .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                        .addComponent(lblPrescribeMedicine1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                        .addComponent(lblAddMedicineQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddMedicineQauntity, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddNewMedicineLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddMedicine)))
                .addContainerGap())
        );
        tabAddNewMedicineLayout.setVerticalGroup(
            tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddNewMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrescribeMedicine1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddMedicineName)
                    .addComponent(txtAddMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabAddNewMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddMedicineQuantity)
                    .addComponent(txtAddMedicineQauntity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddMedicine)
                .addContainerGap(470, Short.MAX_VALUE))
        );

        menuDoctor.addTab("Add New Medicine", tabAddNewMedicine);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuDoctor)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tblAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentsMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblAppointments.getSelectedRow();
            String value = this.tblAppointments.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtAppointmentDate.setText(data[2]);
        this.txtAppointmentPatientID.setText(data[0]);
        this.txtAppointmentPatientName.setText(data[1]);
    }//GEN-LAST:event_tblAppointmentsMouseClicked

    private void cmbSelectAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectAppointmentActionPerformed
        String selectedAppointment = (String) this.cmbSelectAppointment.getSelectedItem();

        if(selectedAppointment != "Select Appointment")
        {
            for(Appointment appointment : Appointment.appointments)
            {
                if(appointment.getPatient().getID().equals(selectedAppointment))
                {
                    Patient patient = appointment.getPatient();
                    
                    this.txtSelectedAppointmentDate.setText(appointment.getDate());
                    this.txtPrescriptionPatientID.setText(patient.getID());
                    String patientName = patient.getForename() + " " + patient.getSurname();
                    this.txtPrescriptionPatientName.setText(patientName);
                    this.txtPrescriptionPatientAddress.setText(patient.getAddress());
                    this.txtPrescriptionPatientGender.setText(patient.getGender());
                    this.txtPrescriptionPatientDOB.setText(patient.getDOB());
                    
                    for(Prescription prescription : Prescription.prescriptions)
                    {
                        //System.out.println(prescription.getNotes());
                        if((patient.getID().equals(prescription.getPatient().getID())) &&
                                (prescription.getDoctor().getID().equals(User.loggedUser.getID())))
                        {
                            this.txtPrescriptionNotes.setText(prescription.getNotes());
                            
                            DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
                            int rows = model.getRowCount();
                            if(rows > 0)
                            {
                                for (int i = rows - 1; i >= 0; i--)
                                {
                                    model.removeRow(i);
                                }
                            }
                            
                            String[] data = {
                                prescription.getMedicine().getName(),
                                Integer.toString(prescription.getQuantity()),
                                prescription.getDosage()
                            };

                            model.addRow(data); 
                            break;
                        }
                    }
                    
                    break;
                }
            }
        }
        else
        {
            this.txtSelectedAppointmentDate.setText("");
            this.txtPrescriptionPatientID.setText("");
            this.txtPrescriptionPatientName.setText("");
            this.txtPrescriptionPatientAddress.setText("");
            this.txtPrescriptionPatientGender.setText("");
            this.txtPrescriptionPatientDOB.setText("");
            this.txtPrescriptionNotes.setText("");
            
            DefaultTableModel model = (DefaultTableModel) this.tblPrescriptionMedicine.getModel();
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
        }
    }//GEN-LAST:event_cmbSelectAppointmentActionPerformed

    private void cmbSelectPatientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectPatientIDActionPerformed
        String id = (String) this.cmbSelectPatientID.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        if(id != "Select Patient")
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
            setPastAppointments(id);
        }
        else
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
        }
    }//GEN-LAST:event_cmbSelectPatientIDActionPerformed

    private void tblPatientHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientHistoryMouseClicked
        int row = 0;
        String[] data = new String[3];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatientHistory.getSelectedRow();
            String value = this.tblPatientHistory.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        String doctorName = "";
        for(Doctor doctor : Doctor.doctors)
        {
            if(doctor.getID().equals(data[0]))
            {
                doctorName = doctor.getForename() + " " + doctor.getSurname();
                DefaultTableModel prescriptionModel = (DefaultTableModel) this.tblPastPrescription.getModel();
                for(PastAppointment pastAppointment : PastAppointment.pastAppointments)
                {   
                    if((pastAppointment.getPastPrescription().getPatient().getID().equals((String) this.cmbSelectPatientID.getSelectedItem())) 
                            && (pastAppointment.getPastPrescription().getDoctor().getID().equals(doctor.getID())))
                    {
                        System.out.println("Not Getting Here");
                        int rows = prescriptionModel.getRowCount();
                        if(rows > 0)
                        {
                            for (int i = rows - 1; i >= 0; i--)
                            {
                                prescriptionModel.removeRow(i);
                            }
                        }
                        String[] prescriptionData = {
                            pastAppointment.getPastPrescription().getMedicine().getName(),
                            Integer.toString(pastAppointment.getPastPrescription().getQuantity()),
                            pastAppointment.getPastPrescription().getDosage()
                        };
                        
                        System.out.println("Name: " + prescriptionData[0]);
                        System.out.println("Quantity: " + prescriptionData[1]);
                        System.out.println("Dosage: " + prescriptionData[2]);
                        prescriptionModel.addRow(prescriptionData);
                    }
                }
            }
        }
        this.txtDoctorName.setText(doctorName);
        this.txtPastDate.setText(data[1]);
        this.txtPastNotes.setText(data[2]);
    }//GEN-LAST:event_tblPatientHistoryMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = this.txtSearchPatientID.getText();
        DefaultTableModel model = (DefaultTableModel) this.tblPatientHistory.getModel();
        if(id.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter a Patient ID", "ERROR", JOptionPane.ERROR_MESSAGE);
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
        }
        else
        {
            int rows = model.getRowCount();
            if(rows > 0)
            {
                for (int i = rows - 1; i >= 0; i--)
                {
                    model.removeRow(i);
                }
            }
            this.txtPatientName.setText("");
            this.txtPatientDOB.setText("");
            setPastAppointments(id);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbSelectPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectPatientActionPerformed
        String id = (String) this.cmbSelectPatient.getSelectedItem();
        
        for(Patient patient : Patient.patients)
        {
            if(patient.getID().equals(id))
            {
                String name = patient.getForename() + " " + patient.getSurname();
                this.txtSelectedPatient.setText(name);
                break;
            }            
        }
    }//GEN-LAST:event_cmbSelectPatientActionPerformed

    private void txtSelectedPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSelectedPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSelectedPatientActionPerformed

    private void txtEnteredDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnteredDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnteredDateActionPerformed

    private void txtEnteredTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnteredTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnteredTimeActionPerformed

    private void txtEnterDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterDateKeyReleased
        this.txtEnteredDate.setText(this.txtEnterDate.getText());
    }//GEN-LAST:event_txtEnterDateKeyReleased

    private void txtEnterTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnterTimeKeyReleased
        this.txtEnteredTime.setText(this.txtEnterTime.getText());
    }//GEN-LAST:event_txtEnterTimeKeyReleased

    private void btnPrescribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrescribeActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,"ARE YOU SURE YOU WISH TO SUBMIT THIS PRESCRIPTION?", "WARNIGN",
                JOptionPane.INFORMATION_MESSAGE);
        if(confirm == 0)
        {
            try
            {
                String selectedMedicine = (String) this.cmbMedicineName.getSelectedItem();
                String patientID = this.txtMedicinePatientID.getText();
                String doctorID = User.loggedUser.getID();
                int quantity = Integer.parseInt(this.txtMedicineQuantity.getText());
                String dosage = this.txtMedicineDosage.getText();

                for(Patient patient : Patient.patients)
                {
                    if(patient.getID().equals(patientID))
                    {
                        for(Medicine medicine : Medicine.medicines)
                        {
                            if(medicine.getName().equals(selectedMedicine))
                            {
                                PrescriptionRequest newPrescriptionRequest = new PrescriptionRequest((Doctor)User.loggedUser, patient, "N/A", medicine,
                                    quantity, dosage);

                                newPrescriptionRequest.addPrescriptionRequest(newPrescriptionRequest);
                                
                                for(Secretary secretary : Secretary.secretarys)
                                {
                                    secretary.setNotification(new Notification("You have new Requests:"
                                        + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
                                }
                                User.saveUsers();
                            }
                        }
                    }
                } 
                
            }
            catch(Exception ex)
            {    
                JOptionPane.showMessageDialog(this, "Enter A Valid Quantity", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + ex);
            }
        }
    }//GEN-LAST:event_btnPrescribeActionPerformed

    private void cmbMedicineNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMedicineNameActionPerformed
        String selectedMedicine = (String) this.cmbMedicineName.getSelectedItem();
        
        if(selectedMedicine != "Select Medicine")
        {
            this.txtMedicineSelected.setText(selectedMedicine);
        }
    }//GEN-LAST:event_cmbMedicineNameActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        this.txtMedicineQuantity.setText(this.txtQuantity.getText());
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtDosageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosageKeyReleased
        this.txtMedicineDosage.setText(this.txtDosage.getText());
    }//GEN-LAST:event_txtDosageKeyReleased

    private void txtAddMedicineQauntityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddMedicineQauntityKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddMedicineQauntityKeyReleased

    private void txtAddMedicineNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddMedicineNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddMedicineNameKeyReleased

    private void btnAddMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMedicineActionPerformed
        String name = this.txtAddMedicineName.getText();
        int quantity = Integer.parseInt(this.txtAddMedicineQauntity.getText());
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WISH TO REQUEST NEW MEDICINE?", "WARNING", 
                JOptionPane.INFORMATION_MESSAGE);
        
        if(confirm == 0)
        {
            MedicineRequest newMedicine = new MedicineRequest(name, quantity);
            newMedicine.addMedicineRequest(newMedicine);
            
            for(Secretary secretary : Secretary.secretarys)
            {
                secretary.setNotification(new Notification("You have new Requests:"
                    + "\nAccount Reqeusts \nAppointment Reqeusts \nMedicine Requests \nTermination Requests"));
            }
            User.saveUsers();
                                
            this.txtAddMedicineName.setText("");
            this.txtAddMedicineQauntity.setText("");
        }
    }//GEN-LAST:event_btnAddMedicineActionPerformed

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
            java.util.logging.Logger.getLogger(DoctorFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorFunctions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMedicine;
    private javax.swing.JButton btnConcludeAppointment;
    private javax.swing.JButton btnConfirmAppointment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrescribe;
    private javax.swing.JButton btnSaveNotes;
    private javax.swing.JComboBox<String> cmbMedicineName;
    private javax.swing.JComboBox<String> cmbSelectAppointment;
    private javax.swing.JComboBox<String> cmbSelectPatient;
    private javax.swing.JComboBox<String> cmbSelectPatientID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAccountType2;
    private javax.swing.JLabel lblAddMedicineName;
    private javax.swing.JLabel lblAddMedicineQuantity;
    private javax.swing.JLabel lblAppointments;
    private javax.swing.JLabel lblAppointments1;
    private javax.swing.JLabel lblConfirmAppointment;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctorName;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicineName2;
    private javax.swing.JLabel lblMedicineName3;
    private javax.swing.JLabel lblMedicinePatientID;
    private javax.swing.JLabel lblMedicinePatientName;
    private javax.swing.JLabel lblMedicineSelected;
    private javax.swing.JLabel lblMedicineSelected1;
    private javax.swing.JLabel lblMedicineSelected2;
    private javax.swing.JLabel lblNextDate;
    private javax.swing.JLabel lblNextPatientID;
    private javax.swing.JLabel lblNextPatientName;
    private javax.swing.JLabel lblPastDate;
    private javax.swing.JLabel lblPastDate1;
    private javax.swing.JLabel lblPastNotes;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblPatientHistory;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblPrescribeMedicine;
    private javax.swing.JLabel lblPrescribeMedicine1;
    private javax.swing.JLabel lblPrescription;
    private javax.swing.JLabel lblPrescriptionNotes;
    private javax.swing.JLabel lblPrescriptionPatientAddress;
    private javax.swing.JLabel lblPrescriptionPatientGender;
    private javax.swing.JLabel lblPrescriptionPatientGender1;
    private javax.swing.JLabel lblPrescriptionPatientName;
    private javax.swing.JLabel lblRequestAppointment;
    private javax.swing.JLabel lblSelectAppointment;
    private javax.swing.JLabel lblSelectDate;
    private javax.swing.JLabel lblSelectTime;
    private javax.swing.JLabel lblSelectedAppointmentDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JTabbedPane menuDoctor;
    private javax.swing.JPanel tabAddNewMedicine;
    private javax.swing.JPanel tabCreateAppointment;
    private javax.swing.JPanel tabMakeNotes;
    private javax.swing.JPanel tabPatientHistory;
    private javax.swing.JPanel tabPrescribeMedicine;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JPanel tabViewAppointments;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTable tblPastPrescription;
    private javax.swing.JTable tblPatientHistory;
    private javax.swing.JTable tblPrescriptionMedicine;
    private javax.swing.JTextField txtAddMedicineName;
    private javax.swing.JTextField txtAddMedicineQauntity;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentPatientID;
    private javax.swing.JTextField txtAppointmentPatientName;
    private javax.swing.JTextField txtDoctorName;
    private javax.swing.JTextField txtDosage;
    private javax.swing.JTextField txtEnterDate;
    private javax.swing.JTextField txtEnterTime;
    private javax.swing.JTextField txtEnteredDate;
    private javax.swing.JTextField txtEnteredTime;
    private javax.swing.JTextField txtMedicineDosage;
    private javax.swing.JTextField txtMedicinePatientID;
    private javax.swing.JTextField txtMedicinePatientName;
    private javax.swing.JTextField txtMedicineQuantity;
    private javax.swing.JTextField txtMedicineSelected;
    private javax.swing.JTextField txtPastDate;
    private javax.swing.JTextArea txtPastNotes;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextArea txtPrescriptionNotes;
    private javax.swing.JTextArea txtPrescriptionPatientAddress;
    private javax.swing.JTextField txtPrescriptionPatientDOB;
    private javax.swing.JTextField txtPrescriptionPatientGender;
    private javax.swing.JTextField txtPrescriptionPatientID;
    private javax.swing.JTextField txtPrescriptionPatientName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearchPatientID;
    private javax.swing.JTextField txtSelectedAppointmentDate;
    private javax.swing.JTextField txtSelectedPatient;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
