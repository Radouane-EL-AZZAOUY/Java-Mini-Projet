package view;

import controller.FieldController;
import controller.InputsController;
import controller.StudentController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.FieldData;
import model.StudentData;

public class AddStudent extends javax.swing.JFrame {

    private StudentController studentController;
    private FieldController fieldController;
    private DefaultComboBoxModel fieldComboBoxModel;
    private int studentID = 0;
    private StudentData studentData;
    private ArrayList<FieldData> fieldsData;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;

    public AddStudent() {
        initComponents();

        init();
    }

    public AddStudent(int studentID, LocalDatabase.MODES MODE) {
        initComponents();

        init();
        this.studentID = studentID;
        this.MODE = MODE;

        if (MODE == LocalDatabase.MODES.DISPLAY_MODE) {
            saveBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            ageField.setEditable(false);
            fieldsCombo.setEnabled(false);
            adresseField.setEditable(false);
            maleRadioButton.setEnabled(false);
            femaleRadioButton.setEnabled(false);
            inscriptionDateField.setEditable(false);
            stateBox.setEnabled(false);
        } else if (MODE == LocalDatabase.MODES.UPDATE_MODE) {
            saveBtn.setText("Modifer");
        }
        displayTeacherData();
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ajouter un étudiant");
        this.setResizable(false);

        studentController = new StudentController();
        fieldController = new FieldController();
        fieldsData = new ArrayList();
        fieldComboBoxModel = new DefaultComboBoxModel();
        fieldsData = fieldController.getAllFields();
        for (FieldData fieldData : fieldsData) {
            fieldComboBoxModel.addElement(fieldData.getTitle());
        }
        fieldsCombo.setModel(fieldComboBoxModel);
        inscriptionDateField.setText(InputsController.getDate());
    }

    private void displayTeacherData() {
        studentData = studentController.getStudentData(studentID);
        firstNameField.setText(studentData.getFirstName());
        lastNameField.setText(studentData.getLastName());
        ageField.setText(studentData.getAge() + "");
        int fieldIndex = 0;
        for (int i = 0; i < fieldsData.size(); i++) {
            if (fieldsData.get(i).getID() == studentData.getFieldID()) {
                fieldIndex = i;
                break;
            }
        }
        fieldsCombo.setSelectedIndex(fieldIndex);
        adresseField.setText(studentData.getAdresse());
        maleRadioButton.setSelected(studentData.getGender() == 'm');
        femaleRadioButton.setSelected(studentData.getGender() == 'w');
        inscriptionDateField.setText(studentData.getInscriptionDate());
        stateBox.setSelected(studentData.getState() == 1);
    }

    void clearAllInputs() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        fieldsCombo.setSelectedIndex(0);
        adresseField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        inscriptionDateField.setText("");
        stateBox.setSelected(false);
    }

    StudentData readInputs() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        int field = fieldsData.get(fieldsCombo.getSelectedIndex()).getID();
        String adresse = adresseField.getText();
        char gender = (femaleRadioButton.isSelected()) ? 'w' : 'm';
        String inscriptionDate = inscriptionDateField.getText();

        return new StudentData(0, firstName, lastName, age, field, adresse, gender, inscriptionDate, stateBox.isSelected() ? 1 : 0);
    }

    boolean checkInputs(StudentData studentData) {
        if (studentData.getFirstName().isEmpty()
                || studentData.getLastName().isEmpty()
                || studentData.getAge() < 18) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        titleView = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        ageField = new javax.swing.JTextField();
        adresseField = new javax.swing.JTextField();
        fieldsCombo = new javax.swing.JComboBox<>();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        stateBox = new javax.swing.JCheckBox();
        inscriptionDateField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setText("Ajouter un étudaint");

        jLabel1.setText("Nom*:");

        jLabel2.setText("Prénom*:");

        jLabel3.setText("Age(>=18)*:");

        jLabel4.setText("Fillière*:");

        jLabel5.setText("Sexe*:");

        jLabel6.setText("Adresse");

        jLabel7.setText("Date d'inscription:");

        jLabel8.setText("Status:");

        saveBtn.setBackground(new java.awt.Color(0, 0, 153));
        saveBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 204, 255));
        saveBtn.setText("Enregistrer");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(204, 204, 204));
        closeBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        closeBtn.setText("Fermer");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(204, 204, 204));
        clearBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 0, 0));
        clearBtn.setText("Effacer");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        firstNameField.setPreferredSize(null);

        lastNameField.setPreferredSize(null);

        ageField.setPreferredSize(null);
        ageField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageFieldKeyReleased(evt);
            }
        });

        adresseField.setPreferredSize(null);

        fieldsCombo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldsComboFocusGained(evt);
            }
        });

        maleRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(maleRadioButton);
        maleRadioButton.setSelected(true);
        maleRadioButton.setText("Homme");

        femaleRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(femaleRadioButton);
        femaleRadioButton.setText("Femme");

        stateBox.setBackground(new java.awt.Color(255, 255, 255));
        stateBox.setText("Etudiant actif");

        inscriptionDateField.setPreferredSize(null);

        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(saveBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(clearBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(firstNameField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lastNameField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(ageField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(adresseField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldsCombo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(maleRadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(femaleRadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(stateBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(inscriptionDateField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inscriptionDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(adresseField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fieldsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(70, 70, 70)
                            .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(stateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adresseField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inscriptionDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            studentData = readInputs();
            if (checkInputs(studentData)) {
                studentData.setID(studentID);
                boolean res = studentController.saveStudentData(studentData, MODE);
                if (res) {
                    JOptionPane.showMessageDialog(this, "L'operation a été effectuer avec succes", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new DataCRUDSView(DataCRUDSView.TYPES.STUDENT).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearAllInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void ageFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageFieldKeyReleased
        InputsController.checkNumbersOnly(ageField, 3);
    }//GEN-LAST:event_ageFieldKeyReleased

    private void fieldsComboFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldsComboFocusGained
        if (fieldComboBoxModel.getSize() == 0) {
            JOptionPane.showConfirmDialog(this, "Ajouter au mois une fillière pour continue!", "No fillière trouver", JOptionPane.ERROR_MESSAGE);
            new AddField().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_fieldsComboFocusGained

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresseField;
    private javax.swing.JTextField ageField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JComboBox<String> fieldsCombo;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField inscriptionDateField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JButton saveBtn;
    private javax.swing.JCheckBox stateBox;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
