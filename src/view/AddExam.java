package view;

import controller.ExamController;
import controller.FieldController;
import controller.InputsController;
import controller.TeacherController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.ExamData;
import model.FieldData;
import model.TeacherData;

/**
 *
 * @author user
 */
public class AddExam extends javax.swing.JFrame {

    private TeacherController teacherController;
    private FieldController fieldController;
    private ExamController examController;
    private DefaultComboBoxModel<String> fieldsComboBoxModel, teacherComboBoxModel;
    private ArrayList<TeacherData> teachersData;
    private ArrayList<FieldData> fieldsData;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;
    private ExamData examData;
    private int examID = -1;

    private enum SearchMode {
        ALL, FIELDS, TEACHERS
    };

    public AddExam() {
        initComponents();

        init();
        updateData("", SearchMode.ALL);

    }

    public AddExam(int ID, LocalDatabase.MODES MODE) {
        initComponents();
        this.MODE = MODE;
        this.examID = ID;

        if (MODE == LocalDatabase.MODES.UPDATE_MODE) {
            saveBtn.setText("Modifer");
            this.setTitle("Modifer examen");
            titleView.setText("Modifer un cours");
        } else if (MODE == LocalDatabase.MODES.DISPLAY_MODE) {
            dateField.setEditable(false);
            durationField.setEditable(false);
            saveBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            fieldComboBox.setEnabled(false);
            teacherComboBox.setEnabled(false);
            fieldSearchField.setEnabled(false);
            teacherSearchField.setEnabled(false);
            descriptionField.setEditable(false);
        }
        init();
        updateData("", SearchMode.ALL);
        displayExamData(examID);
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setTitle("Ajouter un examen");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        dateField.setText(InputsController.getDate());

        teacherController = new TeacherController();
        fieldController = new FieldController();
        examController = new ExamController();
        teacherComboBoxModel = new DefaultComboBoxModel();
        fieldsComboBoxModel = new DefaultComboBoxModel();
        teachersData = new ArrayList<>();
        fieldsData = new ArrayList<>();

    }

    private void displayExamData(int examID) {
        examData = examController.getExamData(examID);
        descriptionField.setText(examData.getDescription());
        dateField.setText(examData.getDate());
        durationField.setText(examData.getDuration() + "");
        int fieldIndex = 0;
        for (int i = 0; i < fieldsData.size(); i++) {
            if (fieldsData.get(i).getID() == examData.getFieldID()) {
                fieldIndex = i;
                break;
            }
        }
        int teacherIndex = 0;
        for (int i = 0; i < teachersData.size(); i++) {
            if (teachersData.get(i).getID() == examData.getTeacherID()) {
                teacherIndex = i;
                break;
            }
        }
        fieldComboBox.setSelectedIndex(fieldIndex);
        teacherComboBox.setSelectedIndex(teacherIndex);
    }

    private void updateData(String query, SearchMode searchMode) {

        switch (searchMode) {
            case ALL -> {
                fieldsData.clear();
                teachersData.clear();
                teacherComboBoxModel.removeAllElements();
                fieldsComboBoxModel.removeAllElements();
                teachersData = teacherController.getAllTeachers();
                fieldsData = fieldController.getAllFields();
                for (TeacherData teacherData : teachersData) {
                    teacherComboBoxModel.addElement(teacherData.getFirstName() + " " + teacherData.getLastName());
                }

                for (FieldData fieldData : fieldsData) {
                    fieldsComboBoxModel.addElement(fieldData.getTitle());
                }

            }
            case FIELDS -> {
                fieldsData.clear();
                fieldsComboBoxModel.removeAllElements();
                fieldsData = fieldController.searchedFields(query, 0);
                for (FieldData fieldData : fieldsData) {
                    fieldsComboBoxModel.addElement(fieldData.getTitle());
                }
                fieldComboBox.setModel(fieldsComboBoxModel);
            }
            case TEACHERS -> {
                teachersData.clear();
                teacherComboBoxModel.removeAllElements();
                teachersData = teacherController.searchTeachers(query, 0);
                for (TeacherData teacherData : teachersData) {
                    teacherComboBoxModel.addElement(teacherData.getFirstName() + " " + teacherData.getLastName());
                }
                teacherComboBox.setModel(teacherComboBoxModel);
            }
            default ->
                throw new AssertionError();
        }

        teacherComboBox.setModel(teacherComboBoxModel);
        fieldComboBox.setModel(fieldsComboBoxModel);
    }

    void clearInputs() {
        descriptionField.setText("");
        dateField.setText("");
        fieldSearchField.setText("");
        teacherSearchField.setText("");
        init();
        updateData("", SearchMode.ALL);
    }

    ExamData readInputs() {
        String date = dateField.getText();
        String description = descriptionField.getText();
        float duration = Float.parseFloat(durationField.getText());
        int fieldID = fieldsData.get(fieldComboBox.getSelectedIndex()).getID();
        int teacherID = teachersData.get(teacherComboBox.getSelectedIndex()).getID();
        return new ExamData(0, description, date, duration, fieldID, teacherID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        titleView = new javax.swing.JLabel();
        fieldComboBox = new javax.swing.JComboBox<>();
        teacherComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        closeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        durationField = new javax.swing.JTextField();
        teacherSearchField = new javax.swing.JTextField();
        fieldSearchField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setForeground(new java.awt.Color(255, 255, 255));

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleView.setText("Ajouter un examen");

        jLabel5.setText("Date*:");

        jLabel6.setText("Fillière*:");

        jLabel7.setText("Enseignant*:");

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

        saveBtn.setBackground(new java.awt.Color(0, 0, 153));
        saveBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 204, 255));
        saveBtn.setText("Enregistrer");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Durée (1.5 = 1h 30min)*:");

        durationField.setText("1.5");
        durationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                durationFieldKeyReleased(evt);
            }
        });

        teacherSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                teacherSearchFieldKeyReleased(evt);
            }
        });

        fieldSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldSearchFieldKeyReleased(evt);
            }
        });

        jLabel9.setText("Rechercher une fillière:");

        jLabel10.setText("Rechercher un enseignant:");

        jLabel11.setText("Description*:");

        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(teacherComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(dateField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(clearBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(saveBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(durationField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(teacherSearchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldSearchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(descriptionField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teacherComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teacherSearchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(teacherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new DataCRUDSView(DataCRUDSView.TYPES.EXAM).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            examData = readInputs();
            if (examData.getDate().isEmpty() || examData.getDuration() == 0 || examData.getDescription().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vérifer bien les données inserer!!!", "Les données sont incorrects", JOptionPane.ERROR_MESSAGE);
            } else {
                examData.setID(examID);
                boolean res = examController.saveExamData(examData, MODE);
                if (res) {
                    JOptionPane.showMessageDialog(this, "L'operation a été effectuer avec succes", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void fieldSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSearchFieldKeyReleased
        String query = fieldSearchField.getText();
        updateData(query, SearchMode.FIELDS);
        /*fieldsData.clear();
        fieldsData = fieldController.searchedFields(query);
        for (FieldData fieldData : fieldsData) {
            fieldsComboBoxModel.addElement(fieldData.getTitle());
        }
        fieldComboBox.setModel(fieldsComboBoxModel);*/
    }//GEN-LAST:event_fieldSearchFieldKeyReleased

    private void teacherSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_teacherSearchFieldKeyReleased
        String query = teacherSearchField.getText();
        updateData(query, SearchMode.TEACHERS);
        /*teachersData.clear();
        teachersData = teacherController.searchTeachers(query);
        for (TeacherData teacherData : teachersData) {
            teacherComboBoxModel.addElement(teacherData.getFirstName() + " " + teacherData.getLastName());
        }
        teacherComboBox.setModel(teacherComboBoxModel);*/
    }//GEN-LAST:event_teacherSearchFieldKeyReleased

    private void durationFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_durationFieldKeyReleased
        InputsController.checkFloatsOnly(durationField, 10);
    }//GEN-LAST:event_durationFieldKeyReleased

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
            java.util.logging.Logger.getLogger(AddExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddExam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddExam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField dateField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JTextField durationField;
    private javax.swing.JComboBox<String> fieldComboBox;
    private javax.swing.JTextField fieldSearchField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> teacherComboBox;
    private javax.swing.JTextField teacherSearchField;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
