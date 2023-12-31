package view;

import controller.ExamController;
import controller.InputsController;
import controller.NoteController;
import controller.StudentController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.ExamData;
import model.NoteData;
import model.StudentData;

/**
 *
 * @author user
 */
public class AddNote extends javax.swing.JFrame {

    private StudentController studentController;
    private ExamController examController;
    private NoteController noteController;
    private ArrayList<StudentData> studentsData;
    private ArrayList<ExamData> examsData;
    private DefaultComboBoxModel<String> studentComboBoxModel;
    private DefaultComboBoxModel<String> examComboBoxModel;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;
    private NoteData noteData;
    private int noteID;

    private enum SearchMode {
        ALL, STUDENTS, EXAMS
    };

    public AddNote() {
        initComponents();
        init();
        updateData("", SearchMode.ALL);
    }

    public AddNote(int noteID, LocalDatabase.MODES MODE) {
        initComponents();
        this.noteID = noteID;
        this.MODE = MODE;
        if (MODE == LocalDatabase.MODES.UPDATE_MODE) {
            this.setTitle("Modifer note");
            saveBtn.setText("Modifier");
        } else if (MODE == LocalDatabase.MODES.DISPLAY_MODE) {
            examSearchField.setEditable(false);
            studentSearchField.setEditable(false);
            examComboBox.setEnabled(false);
            studentComboBox.setEnabled(false);
            noteField.setEditable(false);
            saveBtn.setEnabled(false);
            clearBtn.setEnabled(false);
            this.setTitle("Detaills");
        }
        init();
        updateData("", SearchMode.ALL);
        displayNoteData();
    }

    private void init() {
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Add note");

        studentController = new StudentController();
        examController = new ExamController();
        noteController = new NoteController();
        studentComboBoxModel = new DefaultComboBoxModel();
        examComboBoxModel = new DefaultComboBoxModel();
        studentsData = new ArrayList();
        examsData = new ArrayList();
    }

    private void updateData(String query, SearchMode searchMode) {
        switch (searchMode) {
            case ALL -> {
                studentsData.clear();
                examsData.clear();
                studentComboBoxModel.removeAllElements();
                examComboBoxModel.removeAllElements();
                studentsData = studentController.getAllStudents();
                examsData = examController.getAllExams();
                for (StudentData studentData : studentsData) {
                    studentComboBoxModel.addElement(studentData.getFirstName() + " " + studentData.getLastName());
                }

                for (ExamData examData : examsData) {
                    examComboBoxModel.addElement(examData.getDate() + " " + examData.getDescription());
                }

            }
            case STUDENTS -> {
                studentsData.clear();
                studentComboBoxModel.removeAllElements();
                studentsData = studentController.searchStudents(query, 0);
                for (StudentData studentData : studentsData) {
                    studentComboBoxModel.addElement(studentData.getFirstName() + " " + studentData.getLastName());
                }
                //examComboBox.setModel(examsComboBoxModel);
            }
            case EXAMS -> {
                examsData.clear();
                examComboBoxModel.removeAllElements();
                examsData = examController.searchedExams(query, 0);
                for (ExamData examData : examsData) {
                    examComboBoxModel.addElement(examData.getDate() + " " + examData.getDescription());
                }
                //studentComboBox.setModel(studentComboBoxModel);
            }
            default ->
                throw new AssertionError();
        }

        studentComboBox.setModel(studentComboBoxModel);
        examComboBox.setModel(examComboBoxModel);
    }

    private void displayNoteData() {
        noteData = noteController.getNoteData(noteID);
        noteField.setText(noteData.getNote() + "");
        int studentIndex = 0;
        for (int i = 0; i < studentsData.size(); i++) {
            if (studentsData.get(i).getID() == noteData.getStudentID()) {
                studentIndex = i;
                break;
            }
        }
        int examIndex = 0;
        for (int i = 0; i < examsData.size(); i++) {
            if (examsData.get(i).getID() == noteData.getExamID()) {
                examIndex = i;
                break;
            }
        }
        examComboBox.setSelectedIndex(examIndex);
        studentComboBox.setSelectedIndex(studentIndex);
    }

    private NoteData readInputs() {
        int studenID = studentsData.get(studentComboBox.getSelectedIndex()).getID();
        int examID = examsData.get(examComboBox.getSelectedIndex()).getID();
        float note = Float.parseFloat(noteField.getText());

        return new NoteData(0, studenID, examID, note);
    }

    private void clearInputs() {
        init();
        examSearchField.setText("");
        studentSearchField.setText("");
        examComboBox.setSelectedIndex(0);
        studentComboBox.setSelectedIndex(0);
        noteField.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        titleView = new javax.swing.JLabel();
        examSearchField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        examComboBox = new javax.swing.JComboBox<>();
        noteField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        studentComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        studentSearchField = new javax.swing.JTextField();
        closeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleView.setText("Ajouter les notes");

        examSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                examSearchFieldKeyReleased(evt);
            }
        });

        jLabel9.setText("Rechercher un examen:");

        jLabel6.setText("Examen*:");

        noteField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                noteFieldKeyReleased(evt);
            }
        });

        jLabel10.setText("Note*:");

        jLabel7.setText("Etudiant*:");

        jLabel11.setText("Rechercher un etudiant:");

        studentSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studentSearchFieldKeyReleased(evt);
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

        saveBtn.setBackground(new java.awt.Color(0, 0, 153));
        saveBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 204, 255));
        saveBtn.setText("Enregistrer");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(examSearchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(examComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(noteField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(studentComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(studentSearchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(clearBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(saveBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(examSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(examComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(noteField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(studentSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(studentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(examSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(examComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noteField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 27, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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

    private void examSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_examSearchFieldKeyReleased
        String query = examSearchField.getText();
        updateData(query, SearchMode.EXAMS);

    }//GEN-LAST:event_examSearchFieldKeyReleased

    private void studentSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentSearchFieldKeyReleased
        String query = studentSearchField.getText();
        updateData(query, SearchMode.STUDENTS);
    }//GEN-LAST:event_studentSearchFieldKeyReleased

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new DataCRUDSView(DataCRUDSView.TYPES.NOTE).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            noteData = readInputs();
            if (noteData.getNote() == 0) {
                JOptionPane.showInputDialog(this, "Vérifer bien les données inserer!!!", "Les données sont incorrects", JOptionPane.ERROR_MESSAGE);
            } else {
                noteData.setID(noteID);
                boolean res = noteController.saveNoteData(noteData, MODE);
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

    private void noteFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noteFieldKeyReleased
        InputsController.checkFloatsOnly(noteField, 10);
    }//GEN-LAST:event_noteFieldKeyReleased

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
            java.util.logging.Logger.getLogger(AddNote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNote.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JComboBox<String> examComboBox;
    private javax.swing.JTextField examSearchField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField noteField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> studentComboBox;
    private javax.swing.JTextField studentSearchField;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
