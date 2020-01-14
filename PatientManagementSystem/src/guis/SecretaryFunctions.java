
package guis;
import javax.swing.table.DefaultTableModel;
import system.*;
import users.*;
import java.awt.Toolkit;
import javax.swing.JOptionPane;


public class SecretaryFunctions extends javax.swing.JFrame {


    public SecretaryFunctions() {
        initComponents();
        getUserInfo();
        setAccountRequests();
        setAppointmentRequests();
        setPrescriptions();
        setMedicine();
        setMedicineRequests();
        setPatients();
        setTerminations();
        
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
        
        this.txtUserAccountType.setText("Secretary");
        this.txtUserID.setText(User.loggedUser.getID());
        this.txtUserName.setText(User.loggedUser.getForename() + " " + User.loggedUser.getSurname());
        this.txtUserAddress.setText(User.loggedUser.getAddress());
    }
    
    
    private void setAccountRequests()
    {
        String[][] patientRequests = new String[AccountRequest.accountRequests.length][5];
        
        DefaultTableModel model = (DefaultTableModel) this.tblPatientRequests.getModel();
        
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) 
            {
                model.removeRow(i);
            }
        }

        int i = 0;
        for(AccountRequest accountRequest : AccountRequest.accountRequests)
        {
            patientRequests[i][0] = accountRequest.getForename();
            patientRequests[i][1] = accountRequest.getSurname();
            patientRequests[i][2] = accountRequest.getAddress();
            patientRequests[i][3] = accountRequest.getGender();
            patientRequests[i][4] = accountRequest.getDOB();
            
            i++;
        }
        
        for(String[] data : patientRequests)
        {
            model.addRow(data);
        }
    }
    
    private void setAppointmentRequests()
    {
        
        DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
        
        String[][] appointmentRequests = new String[AppointmentRequest.appointmentRequests.length][5];
        
        int i = 0;
        for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
        {
            Patient patient = appointmentRequest.getPatient();
            
            String forename = patient.getForename();
            String surname = patient.getSurname();

            appointmentRequests[i][0] = patient.getID();
            appointmentRequests[i][1] = forename + " " + surname;
                
            Doctor doctor = appointmentRequest.getDoctor();
            appointmentRequests[i][2] = doctor.getID();
            appointmentRequests[i][3] = "Dr. " + doctor.getSurname();
            appointmentRequests[i][4] = appointmentRequest.getDate();

            i++;
        }
        
        for(String[] data : appointmentRequests)
        {
            model.addRow(data);
        }
    }
    
    private void setPrescriptions()
    {   
        DefaultTableModel model = (DefaultTableModel) this.tblPrescriptions.getModel();

        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests)
        {
            String name = "";
            for(Patient patient : Patient.patients)
            {
                if(prescriptionRequest.getPatient().getID().equals(patient.getID())){
                    name = patient.getForename() + " " + patient.getSurname();
                    break;
                }
            }
            
            String[] data = {
                prescriptionRequest.getDoctor().getID(),
                prescriptionRequest.getPatient().getID(),
                name,
                prescriptionRequest.getMedicine().getName(),
                Integer.toString(prescriptionRequest.getQuantity())
            };

            model.addRow(data);  
        }
    }
    
    private void setMedicine()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblStock.getModel();
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(Medicine medicine : Medicine.medicines)
        {        
            String[] data = {
                medicine.getName(),
                Integer.toString(medicine.getStock())
            };

            model.addRow(data); 
            
        }
    }
    
    private void orderStock(String name, int quantity)
    {
        boolean exist = false;
        for(Medicine medicine : Medicine.medicines)
        {
            if(medicine.getName().equals(name))
            {
                int stock = medicine.getStock();
                stock += quantity;
                medicine.setStock(stock);
                
                DefaultTableModel model = (DefaultTableModel) this.tblStock.getModel();
                int rowCount = model.getRowCount();
                
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                
                setMedicine();
                exist = true;
                break;
            }
        }
        
        if(exist == false)
        {
            orderNewMedicine(name, quantity);
        }
    }
    
    private void setMedicineRequests()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblMedicineRequest.getModel();
        int rowCount = model.getRowCount();
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests)
        {        
            String[] data = {
                medicineRequest.getName(),
                Integer.toString(medicineRequest.getStock())
            };

            model.addRow(data); 
            
        }
    }
    
    private void orderNewMedicine(String name, int quantity)
    {
        for(MedicineRequest medicineRequest : MedicineRequest.medicineRequests)
        {
            if(medicineRequest.getName().equals(name))
            {
                Medicine newMedicine = new Medicine(name, quantity);
                newMedicine.addMedicine(newMedicine);

                medicineRequest.removeMedicineRequest(medicineRequest);
                
                setMedicineRequests();
                break; 
            }
        }
    }
    
    private void setPatients()
    {   
        DefaultTableModel model = (DefaultTableModel) this.tblPatients.getModel();
        int rowCount = model.getRowCount();
        
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(Patient patient : Patient.patients)
        {        
            String[] data = {
                patient.getID(),
                patient.getForename(),
                patient.getSurname(),
                patient.getAddress(),
                patient.getGender(),
                patient.getDOB()
            };

            model.addRow(data); 
        }
    }
    
    private void removePatient(String id)
    {
        Patient[] temp = new Patient[Patient.patients.length - 1];
        int i = 0;
        for(Patient patient : Patient.patients)
        {
            if(!(patient.getID().equals(id)))
            {
               temp[i] = patient;
               i++;
            }
        }
        Patient.patients = temp;
    }
    
    private void setTerminations()
    {
        DefaultTableModel model = (DefaultTableModel) this.tblTermination.getModel();
        int rowCount = model.getRowCount();
        
        if(rowCount > 0)
        {
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        
        for(TerminationRequest terminationRequest : TerminationRequest.terminationRequests)
        {
            Patient patient = terminationRequest.getPatient();
            String[] data = {
                patient.getID(),
                patient.getForename(),
                patient.getSurname(),
                patient.getAddress(),
                patient.getGender(),
                patient.getDOB()
            };
            
            model.addRow(data);
        }
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
        menuSecretary = new javax.swing.JTabbedPane();
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
        tabApprovePatient = new javax.swing.JPanel();
        lblApproveAccounts = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPatientRequests = new javax.swing.JTable();
        lblForename = new javax.swing.JLabel();
        txtPatientForename = new javax.swing.JTextField();
        lblSurname = new javax.swing.JLabel();
        txtPatientSurname = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        txtPatientGender = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtPatientDOB = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPatientAddress = new javax.swing.JTextArea();
        btnApprovePatient = new javax.swing.JButton();
        tabSetAppointment = new javax.swing.JPanel();
        lblApproveAccounts1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAppointmentRequests = new javax.swing.JTable();
        lblForename1 = new javax.swing.JLabel();
        txtAppointmentPatientID = new javax.swing.JTextField();
        lblSurname1 = new javax.swing.JLabel();
        txtAppointmentPatient = new javax.swing.JTextField();
        lblAddress1 = new javax.swing.JLabel();
        txtAppointmentDoctorID = new javax.swing.JTextField();
        lblAddress2 = new javax.swing.JLabel();
        txtAppointmentDoctor = new javax.swing.JTextField();
        lblGender1 = new javax.swing.JLabel();
        txtAppointmentDate = new javax.swing.JTextField();
        btnApproveAppointment = new javax.swing.JButton();
        btnDeclineAppointment = new javax.swing.JButton();
        tabGiveMedicine = new javax.swing.JPanel();
        lblApproveAccounts2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPrescriptions = new javax.swing.JTable();
        lblPatientID = new javax.swing.JLabel();
        txtPrescriptionPatientID = new javax.swing.JTextField();
        lblSurname2 = new javax.swing.JLabel();
        txtPrescriptionPatient = new javax.swing.JTextField();
        lblDoctorID = new javax.swing.JLabel();
        txtPrescriptionDoctorID = new javax.swing.JTextField();
        lblMedicine = new javax.swing.JLabel();
        txtPrescriptionMedicine = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        txtPrescriptionQuantity = new javax.swing.JTextField();
        btnApprovePrescription = new javax.swing.JButton();
        tabOrderStock = new javax.swing.JPanel();
        lblMedicineMain = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        lblMedicineRequests = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblMedicineRequest = new javax.swing.JTable();
        lblStock1 = new javax.swing.JLabel();
        lblMedicineName = new javax.swing.JLabel();
        txtMedicineName = new javax.swing.JTextField();
        lblMedicineQuantity = new javax.swing.JLabel();
        txtMedicineQuantity = new javax.swing.JTextField();
        lblNumItems = new javax.swing.JLabel();
        txtOrderItems = new javax.swing.JTextField();
        btnOrder = new javax.swing.JButton();
        tabRemovePatient = new javax.swing.JPanel();
        lblApproveAccounts3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblPatients = new javax.swing.JTable();
        lblPatientID1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblPatientName1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblPatientGender = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        lblPatientDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        lblPatientAddress = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        btnRemove = new javax.swing.JButton();
        tabApproveTermination = new javax.swing.JPanel();
        lblApproveAccounts4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTermination = new javax.swing.JTable();
        lblPatientID2 = new javax.swing.JLabel();
        txtTerminatePatientID = new javax.swing.JTextField();
        lblPatientName2 = new javax.swing.JLabel();
        txtTerminateName = new javax.swing.JTextField();
        lblPatientGender1 = new javax.swing.JLabel();
        txtTerminateGender = new javax.swing.JTextField();
        lblPatientDOB1 = new javax.swing.JLabel();
        txtTerminateDOB = new javax.swing.JTextField();
        lblPatientAddress1 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtTerminateAddress = new javax.swing.JTextArea();
        btnTerminate = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Secretary");

        lblMain.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMain.setName(""); // NOI18N
        lblMain.setText("Patient Management System");

        menuSecretary.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblUserInfo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserInfo1.setText("Account Information");

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
                        .addComponent(txtUserAccountType, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserInfo1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE))
                    .addGroup(tabUserInfoLayout.createSequentialGroup()
                        .addComponent(lblUserID1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserID)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName2)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(639, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Account Information", tabUserInfo);

        lblApproveAccounts.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblApproveAccounts.setText("Approve Patient Accounts");

        tblPatientRequests.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblPatientRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatientRequests.setRowHeight(25);
        tblPatientRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientRequestsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPatientRequests);
        if (tblPatientRequests.getColumnModel().getColumnCount() > 0) {
            tblPatientRequests.getColumnModel().getColumn(4).setHeaderValue("DOB");
        }

        lblForename.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblForename.setText("Forename:");

        txtPatientForename.setEditable(false);
        txtPatientForename.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblSurname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSurname.setText("Surname:");

        txtPatientSurname.setEditable(false);
        txtPatientSurname.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblGender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGender.setText("Gender:");

        txtPatientGender.setEditable(false);
        txtPatientGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblDOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDOB.setText("DOB:");

        txtPatientDOB.setEditable(false);
        txtPatientDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAddress.setText("Address:");

        txtPatientAddress.setEditable(false);
        txtPatientAddress.setColumns(20);
        txtPatientAddress.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtPatientAddress.setRows(5);
        jScrollPane2.setViewportView(txtPatientAddress);

        btnApprovePatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnApprovePatient.setText("Approve Account");
        btnApprovePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovePatientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabApprovePatientLayout = new javax.swing.GroupLayout(tabApprovePatient);
        tabApprovePatient.setLayout(tabApprovePatientLayout);
        tabApprovePatientLayout.setHorizontalGroup(
            tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblForename)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientForename))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblSurname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientGender))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblDOB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientDOB))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblApproveAccounts)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(lblAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabApprovePatientLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnApprovePatient)))
                .addContainerGap())
        );
        tabApprovePatientLayout.setVerticalGroup(
            tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApprovePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForename)
                    .addComponent(txtPatientForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurname)
                    .addComponent(txtPatientSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(txtPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtPatientDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApprovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress)
                    .addGroup(tabApprovePatientLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApprovePatient)))
                .addContainerGap(431, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Approve Patient Accounts", tabApprovePatient);

        lblApproveAccounts1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblApproveAccounts1.setText("Approve Appointments");

        tblAppointmentRequests.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblAppointmentRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAppointmentRequests.setRowHeight(25);
        tblAppointmentRequests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentRequestsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAppointmentRequests);

        lblForename1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblForename1.setText("Patient ID:");

        txtAppointmentPatientID.setEditable(false);
        txtAppointmentPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblSurname1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSurname1.setText("Patient Name:");

        txtAppointmentPatient.setEditable(false);
        txtAppointmentPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblAddress1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAddress1.setText("Doctor ID:");

        txtAppointmentDoctorID.setEditable(false);
        txtAppointmentDoctorID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblAddress2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAddress2.setText("Doctor Name:");

        txtAppointmentDoctor.setEditable(false);
        txtAppointmentDoctor.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblGender1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGender1.setText("Date:");

        txtAppointmentDate.setEditable(false);
        txtAppointmentDate.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnApproveAppointment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnApproveAppointment.setText("Approve Appointment");
        btnApproveAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveAppointmentActionPerformed(evt);
            }
        });

        btnDeclineAppointment.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDeclineAppointment.setText("Decline Appointment");
        btnDeclineAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabSetAppointmentLayout = new javax.swing.GroupLayout(tabSetAppointment);
        tabSetAppointment.setLayout(tabSetAppointmentLayout);
        tabSetAppointmentLayout.setHorizontalGroup(
            tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblForename1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblSurname1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblAddress1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentDoctorID))
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblAddress2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentDoctor))
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblApproveAccounts1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabSetAppointmentLayout.createSequentialGroup()
                        .addComponent(lblGender1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAppointmentDate))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSetAppointmentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeclineAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApproveAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabSetAppointmentLayout.setVerticalGroup(
            tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSetAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForename1)
                    .addComponent(txtAppointmentPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurname1)
                    .addComponent(txtAppointmentPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress1)
                    .addComponent(txtAppointmentDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress2)
                    .addComponent(txtAppointmentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender1)
                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabSetAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApproveAppointment)
                    .addComponent(btnDeclineAppointment))
                .addContainerGap(523, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Approve Appointment", tabSetAppointment);

        lblApproveAccounts2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblApproveAccounts2.setText("Prescription Approval");

        tblPrescriptions.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPrescriptions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor ID", "Patient ID", "Patient Name", "Medicine", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrescriptions.setRowHeight(25);
        tblPrescriptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPrescriptionsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPrescriptions);

        lblPatientID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientID.setText("Patient ID:");

        txtPrescriptionPatientID.setEditable(false);
        txtPrescriptionPatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblSurname2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSurname2.setText("Patient Name:");

        txtPrescriptionPatient.setEditable(false);
        txtPrescriptionPatient.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblDoctorID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDoctorID.setText("Doctor ID:");

        txtPrescriptionDoctorID.setEditable(false);
        txtPrescriptionDoctorID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicine.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicine.setText("Medicine:");

        txtPrescriptionMedicine.setEditable(false);
        txtPrescriptionMedicine.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblQuantity.setText("Quantity");

        txtPrescriptionQuantity.setEditable(false);
        txtPrescriptionQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnApprovePrescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnApprovePrescription.setText("Approve Prescription");
        btnApprovePrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApprovePrescriptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabGiveMedicineLayout = new javax.swing.GroupLayout(tabGiveMedicine);
        tabGiveMedicine.setLayout(tabGiveMedicineLayout);
        tabGiveMedicineLayout.setHorizontalGroup(
            tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGiveMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApproveAccounts2)
                    .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabGiveMedicineLayout.createSequentialGroup()
                            .addComponent(lblPatientID)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrescriptionPatientID))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabGiveMedicineLayout.createSequentialGroup()
                            .addComponent(lblSurname2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrescriptionPatient)))
                    .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabGiveMedicineLayout.createSequentialGroup()
                            .addComponent(lblQuantity)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrescriptionQuantity))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabGiveMedicineLayout.createSequentialGroup()
                            .addComponent(lblMedicine)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrescriptionMedicine))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabGiveMedicineLayout.createSequentialGroup()
                            .addComponent(lblDoctorID)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrescriptionDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnApprovePrescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        tabGiveMedicineLayout.setVerticalGroup(
            tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGiveMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientID)
                    .addComponent(txtPrescriptionPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSurname2)
                    .addComponent(txtPrescriptionPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctorID)
                    .addComponent(txtPrescriptionDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicine)
                    .addComponent(txtPrescriptionMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGiveMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(txtPrescriptionQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApprovePrescription)
                .addContainerGap(457, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Approve Prescriptions", tabGiveMedicine);

        lblMedicineMain.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMedicineMain.setText("Order Medicine");

        lblStock.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblStock.setText("Current Stock");

        tblStock.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStock.setRowHeight(25);
        tblStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStockMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblStock);

        lblMedicineRequests.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMedicineRequests.setText("Requests");

        tblMedicineRequest.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblMedicineRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicineRequest.setRowHeight(25);
        tblMedicineRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicineRequestMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblMedicineRequest);

        lblStock1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblStock1.setText("Order Medicine");

        lblMedicineName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineName.setText("Medicine:");

        txtMedicineName.setEditable(false);
        txtMedicineName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblMedicineQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedicineQuantity.setText("Quantity:");

        txtMedicineQuantity.setEditable(false);
        txtMedicineQuantity.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblNumItems.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNumItems.setText("Enter Number of Items to Order:");

        txtOrderItems.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        btnOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrder.setText("Order Selected Medicine");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabOrderStockLayout = new javax.swing.GroupLayout(tabOrderStock);
        tabOrderStock.setLayout(tabOrderStockLayout);
        tabOrderStockLayout.setHorizontalGroup(
            tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabOrderStockLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane11)
                    .addGroup(tabOrderStockLayout.createSequentialGroup()
                        .addComponent(lblMedicineName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicineName, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE))
                    .addGroup(tabOrderStockLayout.createSequentialGroup()
                        .addComponent(lblMedicineQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedicineQuantity))
                    .addGroup(tabOrderStockLayout.createSequentialGroup()
                        .addGroup(tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMedicineMain)
                            .addComponent(lblStock)
                            .addComponent(lblMedicineRequests)
                            .addComponent(lblStock1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabOrderStockLayout.createSequentialGroup()
                        .addComponent(lblNumItems)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOrderItems, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabOrderStockLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tabOrderStockLayout.setVerticalGroup(
            tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabOrderStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMedicineMain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMedicineRequests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStock1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineName)
                    .addComponent(txtMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedicineQuantity)
                    .addComponent(txtMedicineQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabOrderStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumItems)
                    .addComponent(txtOrderItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrder)
                .addContainerGap(410, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Order Medicine", tabOrderStock);

        lblApproveAccounts3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblApproveAccounts3.setText("Remove Patient");

        tblPatients.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", " Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPatients.setRowHeight(25);
        tblPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblPatients);

        lblPatientID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientID1.setText("Patient ID:");

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientName1.setText("Patient Name:");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientGender.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientGender.setText("Gender");

        txtGender.setEditable(false);
        txtGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientDOB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientDOB.setText("DOB:");

        txtDOB.setEditable(false);
        txtDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientAddress.setText("Address:");

        txtAddress.setEditable(false);
        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtAddress.setRows(5);
        jScrollPane7.setViewportView(txtAddress);

        btnRemove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRemove.setText("Delete Selected Patient");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabRemovePatientLayout = new javax.swing.GroupLayout(tabRemovePatient);
        tabRemovePatient.setLayout(tabRemovePatientLayout);
        tabRemovePatientLayout.setHorizontalGroup(
            tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRemovePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblApproveAccounts3)
                        .addGroup(tabRemovePatientLayout.createSequentialGroup()
                            .addComponent(lblPatientID1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(tabRemovePatientLayout.createSequentialGroup()
                            .addComponent(lblPatientName1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtName))
                        .addComponent(jScrollPane6)
                        .addGroup(tabRemovePatientLayout.createSequentialGroup()
                            .addComponent(lblPatientGender)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGender))
                        .addGroup(tabRemovePatientLayout.createSequentialGroup()
                            .addComponent(lblPatientDOB)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDOB))
                        .addGroup(tabRemovePatientLayout.createSequentialGroup()
                            .addComponent(lblPatientAddress)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane7)))
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        tabRemovePatientLayout.setVerticalGroup(
            tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRemovePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientID1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientGender)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientDOB)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRemovePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientAddress)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addContainerGap(446, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Remove Patients", tabRemovePatient);

        lblApproveAccounts4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblApproveAccounts4.setText("Approve Account Termination");

        tblTermination.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        tblTermination.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Forename", "Surname", "Address", "Gender", "DOB"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTermination.setRowHeight(25);
        tblTermination.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTerminationMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTermination);

        lblPatientID2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientID2.setText("Patient ID:");

        txtTerminatePatientID.setEditable(false);
        txtTerminatePatientID.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientName2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientName2.setText("Patient Name:");

        txtTerminateName.setEditable(false);
        txtTerminateName.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientGender1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientGender1.setText("Gender");

        txtTerminateGender.setEditable(false);
        txtTerminateGender.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientDOB1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientDOB1.setText("DOB:");

        txtTerminateDOB.setEditable(false);
        txtTerminateDOB.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        lblPatientAddress1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPatientAddress1.setText("Address:");

        txtTerminateAddress.setEditable(false);
        txtTerminateAddress.setColumns(20);
        txtTerminateAddress.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTerminateAddress.setRows(5);
        jScrollPane9.setViewportView(txtTerminateAddress);

        btnTerminate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTerminate.setText("Approve Selected Account Termination");
        btnTerminate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabApproveTerminationLayout = new javax.swing.GroupLayout(tabApproveTermination);
        tabApproveTermination.setLayout(tabApproveTerminationLayout);
        tabApproveTerminationLayout.setHorizontalGroup(
            tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblPatientID2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminatePatientID))
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblPatientName2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminateName, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblPatientGender1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminateGender))
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblPatientDOB1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTerminateDOB))
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblApproveAccounts4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabApproveTerminationLayout.createSequentialGroup()
                        .addComponent(lblPatientAddress1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabApproveTerminationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTerminate)))
                .addContainerGap())
        );
        tabApproveTerminationLayout.setVerticalGroup(
            tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabApproveTerminationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblApproveAccounts4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientID2)
                    .addComponent(txtTerminatePatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName2)
                    .addComponent(txtTerminateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientGender1)
                    .addComponent(txtTerminateGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientDOB1)
                    .addComponent(txtTerminateDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabApproveTerminationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientAddress1)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTerminate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuSecretary.addTab("Approve Account Termination", tabApproveTermination);

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
                .addComponent(lblMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSecretary, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(menuSecretary)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPatientRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientRequestsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatientRequests.getSelectedRow();
            String value = tblPatientRequests.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPatientForename.setText(data[0]);
        this.txtPatientSurname.setText(data[1]);
        this.txtPatientAddress.setText(data[2]);
        this.txtPatientGender.setText(data[3]);
        this.txtPatientDOB.setText(data[4]);
    }//GEN-LAST:event_tblPatientRequestsMouseClicked

    private void btnApprovePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePatientActionPerformed
        String id = String.format("P%03d", Patient.patients.length + 1);
        String forename = this.txtPatientForename.getText();
        String surname = this.txtPatientSurname.getText();
        String address = this.txtPatientAddress.getText();
        String gender = this.txtPatientGender.getText();
        String dob = this.txtPatientDOB.getText();
        
        for(AccountRequest accountRequest : AccountRequest.accountRequests)
        {
            if((accountRequest.getForename().equals(forename)) && (accountRequest.getSurname().equals(surname)))
            {
                int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
                if(confirm == 0)
                {
                    Notification message = new Notification("Welcome to your new Patient Account");
                    
                    Patient newPatient = new Patient(id, accountRequest.getPassword(), forename, surname, address, message, gender, dob);
                    newPatient.addPatient(newPatient);
                    
                    accountRequest.removeAccountRequest(accountRequest);
                    
                    DefaultTableModel model = (DefaultTableModel) this.tblPatientRequests.getModel();
                    int rows = model.getRowCount();
                    
                    if(rows > 0)
                    {
                        for (int i = rows - 1; i >= 0; i--)
                        {
                            model.removeRow(i);
                        }
                    }
                    
                    this.txtPatientForename.setText("");
                    this.txtPatientSurname.setText("");
                    this.txtPatientAddress.setText("");
                    this.txtPatientGender.setText("");
                    this.txtPatientDOB.setText("");
                    
                    setAccountRequests();
                    setPatients();
                }
                break;
            }
        }
        
        
    }//GEN-LAST:event_btnApprovePatientActionPerformed

    private void tblAppointmentRequestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentRequestsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblAppointmentRequests.getSelectedRow();
            String value = tblAppointmentRequests.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtAppointmentPatientID.setText(data[0]);
        this.txtAppointmentPatient.setText(data[1]);
        this.txtAppointmentDoctorID.setText(data[2]);
        this.txtAppointmentDoctor.setText(data[3]);
        this.txtAppointmentDate.setText(data[4]);
    }//GEN-LAST:event_tblAppointmentRequestsMouseClicked

    private void btnApproveAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveAppointmentActionPerformed
        String doctorID = this.txtAppointmentDoctorID.getText();
        String patientID = this.txtAppointmentPatientID.getText();
        String date = this.txtAppointmentDate.getText();
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(patientID))
                {
                    for(Doctor doctor : Doctor.doctors)
                    {
                        if(doctor.getID().equals(doctorID))
                        {
                            Appointment newAppointment = new Appointment(doctor, patient, date);
                            newAppointment.addAppointment(newAppointment);
                            Appointment.saveAppointments();
                            
                            patient.setNotification(new Notification("Your Appointment with Dr. " 
                                    + doctor.getSurname() + " was approved for " + date));
                            doctor.setNotification(new Notification("Your Appointment with Patient " + 
                                    patient.getForename() + " " + patient.getSurname() + " was approved for " + date));
                            User.saveUsers();
                            
                            for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
                            {
                                if((appointmentRequest.getDoctor().getID().equals(doctor.getID())) &&
                                        (appointmentRequest.getPatient().getID().equals(patient.getID())))
                                {
                                    appointmentRequest.removeAppointmentRequest(appointmentRequest);
                                    AppointmentRequest.saveAppointmentRequests();
                                }
                            }
                        }
                    }
                }
            }
            
                    
            int selectedRow = this.tblAppointmentRequests.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
            model.removeRow(selectedRow);
        }
    }//GEN-LAST:event_btnApproveAppointmentActionPerformed

    private void btnDeclineAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineAppointmentActionPerformed
        
        
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO DECLINE?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {   
            int selectedRow = this.tblAppointmentRequests.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) this.tblAppointmentRequests.getModel();
            model.removeRow(selectedRow);
            
            String doctorID = this.txtAppointmentDoctorID.getText();
            String patientID = this.txtAppointmentPatientID.getText();
            
            //REMOVE APPOINTMENT FROM REQUEST ARRAY
            for(AppointmentRequest appointmentRequest : AppointmentRequest.appointmentRequests)
            {
                Doctor doctor = appointmentRequest.getDoctor();
                Patient patient = appointmentRequest.getPatient();
                
                if((doctor.getID().equals(doctorID)) && (patient.getID().equals(patientID)))
                {
                    appointmentRequest.removeAppointmentRequest(appointmentRequest);
                }
                
                for(Patient patients : Patient.patients)
                {
                    if(patients.getID().equals(patient.getID()))
                    {
                        patients.setNotification(new Notification("Your Appointment Request with Dr. " +
                            doctor.getSurname() + " was declined"));
                        User.saveUsers();
                        break;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeclineAppointmentActionPerformed

    private void tblPrescriptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPrescriptionsMouseClicked
        int row = 0;
        String[] data = new String[5];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPrescriptions.getSelectedRow();
            String value = tblPrescriptions.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtPrescriptionDoctorID.setText(data[0]);
        this.txtPrescriptionPatientID.setText(data[1]);
        this.txtPrescriptionPatient.setText(data[2]);
        this.txtPrescriptionMedicine.setText(data[3]);
        this.txtPrescriptionQuantity.setText(data[4]);
        
    }//GEN-LAST:event_tblPrescriptionsMouseClicked

    private void btnApprovePrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApprovePrescriptionActionPerformed
       int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO APPROVE THIS PRESCRIPTION?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            String doctorID = this.txtPrescriptionDoctorID.getText();
            String patientID = this.txtPrescriptionPatientID.getText();
            for(PrescriptionRequest prescriptionRequest : PrescriptionRequest.prescriptionRequests)
            {
                Doctor doctor = prescriptionRequest.getDoctor();
                Patient patient = prescriptionRequest.getPatient();
                
                if((doctor.getID().equals(doctorID)) && (patient.getID().equals(patientID)))
                {
                    System.out.println(prescriptionRequest.getMedicine().getName());
                    
                    for(Medicine medicine : Medicine.medicines)
                    {
                        if(medicine.getName().equals(prescriptionRequest.getMedicine().getName()))
                        {
                            System.out.println("TRUE");
                            int stockAmount = medicine.getStock();
                            int quantity = prescriptionRequest.getQuantity();
                            int newStock = stockAmount - quantity;
                            
                            if((newStock) < 0)
                            {
                                int orderMore = JOptionPane.showConfirmDialog(this, "THERE IS NOT ENOUGH MEDICINE IN STOCK TO FULFILL THIS PRESCRIPTION"
                                    + "\nWOULD YOU LIKE TO ORDER MORE?", "CONFIRM", JOptionPane.YES_NO_OPTION);
                                if(orderMore == 0)
                                {
                                    //OPEN ORDER STOCK TAB
                                    this.txtMedicineName.setText(medicine.getName());
                                    this.txtMedicineQuantity.setText(Integer.toString(medicine.getStock()));
                                    this.menuSecretary.setSelectedIndex(4);
                                }
                                break;
                            }
                            else
                            {
                                medicine.setStock(newStock);
                                for(Prescription prescription : Prescription.prescriptions)
                                {
                                    if((prescription.getDoctor() == prescriptionRequest.getDoctor()) && 
                                            prescription.getPatient() == prescriptionRequest.getPatient())
                                    {
                                        prescription.removePrescription(prescription);
                                    }
                                }
                                
                                Prescription newPrescription = new Prescription(doctor, patient, prescriptionRequest.getNotes(),
                                    medicine, quantity, prescriptionRequest.getDosage());
                                
                                newPrescription.addPrescription(newPrescription);
                                prescriptionRequest.removePrescriptionRequest(prescriptionRequest);
                                
                                this.txtPrescriptionDoctorID.setText("");
                                this.txtPrescriptionMedicine.setText("");
                                this.txtPrescriptionPatient.setText("");
                                this.txtPrescriptionPatientID.setText("");
                                this.txtPrescriptionQuantity.setText("");
                                
                                Medicine.saveMedicine();
                                
                                setPrescriptions();
                                setMedicine();
                                break;
                            } 
                        }
                    }
                    break;
                }
            } 
        }
    }//GEN-LAST:event_btnApprovePrescriptionActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        String name = this.txtMedicineName.getText();
        int quantity = 0;
        boolean error = false;
        try
        {
            quantity = Integer.parseInt(this.txtOrderItems.getText());
        }
        catch(NumberFormatException err)
        {
            JOptionPane.showMessageDialog(this, "ENTER ONLY INTEGERS!", "ERROR", JOptionPane.ERROR_MESSAGE);
            error = true;
        }
        if(error != true)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO ORDER " + quantity + " ITEMS OF " + name, "CONFIRM", 
            JOptionPane.YES_NO_OPTION);
            
            if(confirm == 0)
            {
                orderStock(name, quantity);
            }
        }  
        
        this.txtMedicineName.setText("");
        this.txtMedicineQuantity.setText("");
        this.txtOrderItems.setText("");
    }//GEN-LAST:event_btnOrderActionPerformed

    private void tblStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockMouseClicked
        int row = 0;
        String[] data = new String[2];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblStock.getSelectedRow();
            String value = tblStock.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtMedicineName.setText(data[0]);
        this.txtMedicineQuantity.setText(data[1]);
    }//GEN-LAST:event_tblStockMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO REMOVE THIS PATIENT ACCOUNT?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(this.txtID.getText()))
                {
                    this.txtID.setText("");
                    this.txtName.setText("");
                    this.txtAddress.setText("");
                    this.txtGender.setText("");
                    this.txtDOB.setText("");
                    
                    patient.removePatient(patient);
                    setPatients();
                }
            } 
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tblPatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientsMouseClicked
        int row = 0;
        String[] data = new String[6];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblPatients.getSelectedRow();
            String value = tblPatients.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtID.setText(data[0]);
        this.txtName.setText(data[1] + " " + data[2]);
        this.txtAddress.setText(data[3]);
        this.txtGender.setText(data[4]);
        this.txtDOB.setText(data[5]);
    }//GEN-LAST:event_tblPatientsMouseClicked

    private void btnTerminateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminateActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "ARE YOU SURE YOU WANT TO REMOVE THIS PATIENT ACCOUNT?", "CONFIRM", 
                JOptionPane.YES_NO_OPTION);
        
        if(confirm == 0)
        {
            String id = this.txtTerminatePatientID.getText();
            for(Patient patient : Patient.patients)
            {
                if(patient.getID().equals(id))
                {
                    this.txtTerminatePatientID.setText("");
                    this.txtTerminateName.setText("");
                    this.txtTerminateAddress.setText("");
                    this.txtTerminateGender.setText("");
                    this.txtTerminateDOB.setText("");
                    
                    patient.removePatient(patient);
                    setPatients();
                    
                    for(TerminationRequest terminationRequest : TerminationRequest.terminationRequests)
                    {
                        if(terminationRequest.getPatient().getID().equals(id))
                        {
                            terminationRequest.removeTerminationRequest(terminationRequest);
                            setTerminations();
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }//GEN-LAST:event_btnTerminateActionPerformed

    private void tblTerminationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTerminationMouseClicked
        int row = 0;
        String[] data = new String[6];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblTermination.getSelectedRow();
            String value = tblTermination.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtTerminatePatientID.setText(data[0]);
        this.txtTerminateName.setText(data[1] + " " + data[2]);
        this.txtTerminateAddress.setText(data[3]);
        this.txtTerminateGender.setText(data[4]);
        this.txtTerminateDOB.setText(data[5]);
    }//GEN-LAST:event_tblTerminationMouseClicked

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

    private void tblMedicineRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedicineRequestMouseClicked
        int row = 0;
        String[] data = new String[2];
        for(int i = 0; i < data.length; i++)
        {
            row = this.tblMedicineRequest.getSelectedRow();
            String value = tblMedicineRequest.getModel().getValueAt(row, i).toString();
            data[i] = value;
        }
        this.txtMedicineName.setText(data[0]);
        this.txtMedicineQuantity.setText(data[1]);
        this.txtOrderItems.setText(data[1]);
    }//GEN-LAST:event_tblMedicineRequestMouseClicked

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
            java.util.logging.Logger.getLogger(SecretaryFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecretaryFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecretaryFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecretaryFunctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecretaryFunctions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApproveAppointment;
    private javax.swing.JButton btnApprovePatient;
    private javax.swing.JButton btnApprovePrescription;
    private javax.swing.JButton btnDeclineAppointment;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnTerminate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAccountType1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAddress1;
    private javax.swing.JLabel lblAddress2;
    private javax.swing.JLabel lblApproveAccounts;
    private javax.swing.JLabel lblApproveAccounts1;
    private javax.swing.JLabel lblApproveAccounts2;
    private javax.swing.JLabel lblApproveAccounts3;
    private javax.swing.JLabel lblApproveAccounts4;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDoctorID;
    private javax.swing.JLabel lblForename;
    private javax.swing.JLabel lblForename1;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblGender1;
    private java.awt.Label lblMain;
    private javax.swing.JLabel lblMedicine;
    private javax.swing.JLabel lblMedicineMain;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicineQuantity;
    private javax.swing.JLabel lblMedicineRequests;
    private javax.swing.JLabel lblNumItems;
    private javax.swing.JLabel lblPatientAddress;
    private javax.swing.JLabel lblPatientAddress1;
    private javax.swing.JLabel lblPatientDOB;
    private javax.swing.JLabel lblPatientDOB1;
    private javax.swing.JLabel lblPatientGender;
    private javax.swing.JLabel lblPatientGender1;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientID1;
    private javax.swing.JLabel lblPatientID2;
    private javax.swing.JLabel lblPatientName1;
    private javax.swing.JLabel lblPatientName2;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStock1;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblSurname1;
    private javax.swing.JLabel lblSurname2;
    private javax.swing.JLabel lblUserID1;
    private javax.swing.JLabel lblUserInfo1;
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserName2;
    private javax.swing.JTabbedPane menuSecretary;
    private javax.swing.JPanel tabApprovePatient;
    private javax.swing.JPanel tabApproveTermination;
    private javax.swing.JPanel tabGiveMedicine;
    private javax.swing.JPanel tabOrderStock;
    private javax.swing.JPanel tabRemovePatient;
    private javax.swing.JPanel tabSetAppointment;
    private javax.swing.JPanel tabUserInfo;
    private javax.swing.JTable tblAppointmentRequests;
    private javax.swing.JTable tblMedicineRequest;
    private javax.swing.JTable tblPatientRequests;
    private javax.swing.JTable tblPatients;
    private javax.swing.JTable tblPrescriptions;
    private javax.swing.JTable tblStock;
    private javax.swing.JTable tblTermination;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentDoctor;
    private javax.swing.JTextField txtAppointmentDoctorID;
    private javax.swing.JTextField txtAppointmentPatient;
    private javax.swing.JTextField txtAppointmentPatientID;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMedicineName;
    private javax.swing.JTextField txtMedicineQuantity;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOrderItems;
    private javax.swing.JTextArea txtPatientAddress;
    private javax.swing.JTextField txtPatientDOB;
    private javax.swing.JTextField txtPatientForename;
    private javax.swing.JTextField txtPatientGender;
    private javax.swing.JTextField txtPatientSurname;
    private javax.swing.JTextField txtPrescriptionDoctorID;
    private javax.swing.JTextField txtPrescriptionMedicine;
    private javax.swing.JTextField txtPrescriptionPatient;
    private javax.swing.JTextField txtPrescriptionPatientID;
    private javax.swing.JTextField txtPrescriptionQuantity;
    private javax.swing.JTextArea txtTerminateAddress;
    private javax.swing.JTextField txtTerminateDOB;
    private javax.swing.JTextField txtTerminateGender;
    private javax.swing.JTextField txtTerminateName;
    private javax.swing.JTextField txtTerminatePatientID;
    private javax.swing.JTextField txtUserAccountType;
    private javax.swing.JTextArea txtUserAddress;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
