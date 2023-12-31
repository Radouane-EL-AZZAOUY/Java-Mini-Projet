package view;

import controller.CourseController;
import controller.InputsController;
import controller.TeacherController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.CourseData;
import model.TeacherData;

/**
 *
 * @author Radouane El Azzaouy
 */
public class AddTeacher extends javax.swing.JFrame {

    private TeacherController teacherController;
    private CourseController courseController;
    private int teacherID = 0;
    private TeacherData teacherData;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;
    private DefaultComboBoxModel comboBoxModel;
    private ArrayList<CourseData> coursesData;

    public AddTeacher() {
        initComponents();
        init();
    }

    public AddTeacher(int ID, LocalDatabase.MODES MODE) {
        initComponents();
        init();
        this.teacherID = ID;
        this.MODE = MODE;
        if (MODE == LocalDatabase.MODES.DISPLAY_MODE) {
            saveBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            ageField.setEditable(false);
            courseCombo.setEnabled(false);
            adresseField.setEditable(false);
            maleRadioButton.setEnabled(false);
            femaleRadioButton.setEnabled(false);
            paBox.setEnabled(false);
            psBox.setEnabled(false);
            pesBox.setEnabled(false);
            fieldChefBox.setEnabled(false);
            departChefBox.setEnabled(false);
        } else if (MODE == LocalDatabase.MODES.UPDATE_MODE) {
            saveBtn.setText("Modifer");
        }
        displayTeacherData();
    }

    private void init() {
        this.setTitle("Ajouter enseignant");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        coursesData = new ArrayList();
        teacherController = new TeacherController();
        courseController = new CourseController();
        comboBoxModel = new DefaultComboBoxModel();
        coursesData = courseController.getAllCourses();
        for (CourseData courseData : coursesData) {
            comboBoxModel.addElement(courseData.getTitle() + " (" + courseData.getDuration() + " h)");
        }
        courseCombo.setModel(comboBoxModel);
    }

    private void displayTeacherData() {
        teacherData = teacherController.getTeacherData(teacherID);
        firstNameField.setText(teacherData.getFirstName());
        lastNameField.setText(teacherData.getLastName());
        ageField.setText(teacherData.getAge() + "");
        courseCombo.setSelectedIndex(courseController.getCourseIndex(teacherData.getSpecialite()));
        adresseField.setText(teacherData.getAdresse());
        maleRadioButton.setSelected(teacherData.getGender() == 'm');
        femaleRadioButton.setSelected(teacherData.getGender() == 'w');
        paBox.setSelected(teacherData.getStateList().contains("PA"));
        psBox.setSelected(teacherData.getStateList().contains("PS"));
        pesBox.setSelected(teacherData.getStateList().contains("PES"));
        fieldChefBox.setSelected(teacherData.getRole() < 2 && teacherData.getRole() > 0);
        departChefBox.setSelected(teacherData.getRole() > 2);
    }

    void clearAllInputs() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        courseCombo.setSelectedIndex(0);
        adresseField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        paBox.setSelected(false);
        psBox.setSelected(false);
        pesBox.setSelected(false);
        fieldChefBox.setSelected(false);
        departChefBox.setSelected(false);
    }

    TeacherData readInputs() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        int specialite = coursesData.get(courseCombo.getSelectedIndex()).getID();
        String adresse = adresseField.getText();
        char gender = (maleRadioButton.isSelected()) ? 'm' : 'w';
        int status = 0;
        int role = 0;
        if (paBox.isSelected()) {
            status += 1;
        }
        if (psBox.isSelected()) {
            status += 3;
        }
        if (pesBox.isSelected()) {
            status += 5;
        }
        if (fieldChefBox.isSelected()) {
            role += 1;
        }
        if (departChefBox.isSelected()) {
            role += 3;
        }

        return new TeacherData(0, firstName, lastName, age, specialite, adresse, gender, status, role);
    }

    boolean checkInputs(TeacherData teacherData) {
        return !(teacherData.getFirstName().isEmpty()
                || teacherData.getLastName().isEmpty()
                || teacherData.getAge() < 18);
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
        courseCombo = new javax.swing.JComboBox<>();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        paBox = new javax.swing.JCheckBox();
        psBox = new javax.swing.JCheckBox();
        pesBox = new javax.swing.JCheckBox();
        fieldChefBox = new javax.swing.JCheckBox();
        departChefBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setText("Ajouter un enseignent");

        jLabel1.setText("Nom*:");

        jLabel2.setText("Prénom*:");

        jLabel3.setText("Age(>=18)*:");

        jLabel4.setText("Domaine d'enseignement*:");

        jLabel5.setText("Sexe*:");

        jLabel6.setText("Adresse");

        jLabel7.setText("Status:");

        jLabel8.setText("Role:");

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

        ageField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageFieldKeyReleased(evt);
            }
        });

        courseCombo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                courseComboFocusGained(evt);
            }
        });

        maleRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(maleRadioButton);
        maleRadioButton.setSelected(true);
        maleRadioButton.setText("Homme");

        femaleRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(femaleRadioButton);
        femaleRadioButton.setText("Femme");

        paBox.setBackground(new java.awt.Color(255, 255, 255));
        paBox.setText("PA");

        psBox.setBackground(new java.awt.Color(255, 255, 255));
        psBox.setText("PS");

        pesBox.setBackground(new java.awt.Color(255, 255, 255));
        pesBox.setText("PES");

        fieldChefBox.setBackground(new java.awt.Color(255, 255, 255));
        fieldChefBox.setText("Chef de fillière");

        departChefBox.setBackground(new java.awt.Color(255, 255, 255));
        departChefBox.setText("Chef de département");

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
        jDesktopPane1.setLayer(courseCombo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(maleRadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(femaleRadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(paBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(psBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(pesBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldChefBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(departChefBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(paBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(psBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                    .addComponent(fieldChefBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(departChefBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adresseField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(courseCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
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
                    .addComponent(courseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adresseField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(femaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(paBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(psBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pesBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldChefBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(departChefBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
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
            teacherData = readInputs();
            if (checkInputs(teacherData)) {
                teacherData.setID(teacherID);
                boolean res = teacherController.saveTeacherData(teacherData, MODE);
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
        new DataCRUDSView(DataCRUDSView.TYPES.TEACHER).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearAllInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void ageFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageFieldKeyReleased
        InputsController.checkNumbersOnly(ageField, 3);
    }//GEN-LAST:event_ageFieldKeyReleased

    private void courseComboFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_courseComboFocusGained
        if (comboBoxModel.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "Ajouter des au mois cours pour continue!", "No cours trouver", JOptionPane.ERROR_MESSAGE);
            new AddCourse().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_courseComboFocusGained

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresseField;
    private javax.swing.JTextField ageField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JComboBox<String> courseCombo;
    private javax.swing.JCheckBox departChefBox;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JCheckBox fieldChefBox;
    private javax.swing.JTextField firstNameField;
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
    private javax.swing.JCheckBox paBox;
    private javax.swing.JCheckBox pesBox;
    private javax.swing.JCheckBox psBox;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
