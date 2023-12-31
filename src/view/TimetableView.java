package view;

import controller.CourseController;
import controller.FieldController;
import controller.TimetableController;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.CourseData;
import model.TimetableData;

/**
 *
 * @author user
 */
public class TimetableView extends javax.swing.JFrame {

    private TimetableController timetableController;
    private CourseController courseController;
    private FieldController fieldController;
    private ArrayList<TimetableData> timetablesData;
    private DefaultTableModel timetableModel;
    private int fieldID;
    private boolean searchMode = false;

    public TimetableView() {
        initComponents();
        init();
    }

    public TimetableView(int fieldID) {
        initComponents();

        this.fieldID = fieldID;
        init();
        updateTimetableData();
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Emplois de temps");

        timetableController = new TimetableController();
        fieldController = new FieldController();
        courseController = new CourseController();
        timetablesData = new ArrayList();

    }

    private void updateTimetableData() {
        timetableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        timetableModel.setColumnIdentifiers(new String[]{"Jour", "08:30 - 10:15", "10:30 - 12:15", "14:30 - 16:15", "16:30 - 18:15"});
        timetablesData.clear();
        if (searchMode) {
            timetablesData = timetableController.searchTimetables(fieldField.getText(), 0);
            searchMode = false;
        } else {
            timetablesData = timetableController.searchTimetables(fieldController.getFieldName(fieldID), 0);
        }
        String field = "";
        if (!timetablesData.isEmpty()) {
            field = fieldController.getFieldName(timetablesData.getFirst().getFieldID());
        }
        for (TimetableData timetableData : timetablesData) {
            String part1 = "Vide";
            String part2 = "Vide";
            String part3 = "Vide";
            String part4 = "Vide";

            for (CourseData courseData : courseController.getAllCourses()) {
                if (courseData.getID() == timetableData.getPart1()) {
                    part1 = courseData.getTitle();
                }
                if (courseData.getID() == timetableData.getPart2()) {
                    part2 = courseData.getTitle();
                }

                if (courseData.getID() == timetableData.getPart3()) {
                    part3 = courseData.getTitle();
                }

                if (courseData.getID() == timetableData.getPart4()) {
                    part4 = courseData.getTitle();
                }

            }

            int rowIndex = timetableData.getDay();
            if (timetableModel.getRowCount() < rowIndex) {
                timetableModel.addRow(new String[]{
                    timetableController.getDayName(timetableData),
                    part1,
                    part2,
                    part3,
                    part4
                });
            } else {
                timetableModel.insertRow(timetableData.getDay(), new String[]{
                    timetableController.getDayName(timetableData),
                    part1,
                    part2,
                    part3,
                    part4
                });
            }
            System.out.println(timetableModel.getRowCount());
        }
        /*for (TimetableData timetableData:timetablesData) {
            timetableModel.addRow(new String[]{
                timetableData.getDay()+"",
                timetableData.getPart1()+"",
                timetableData.getPart2()+"",
                timetableData.getPart3()+"",
                timetableData.getPart4()+"",
            });
        }*/
        fieldLabel.setText(field);
        timetableView.setModel(timetableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        timetableView = new javax.swing.JTable();
        titleView = new javax.swing.JLabel();
        fieldField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        fieldLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timetableView.setFont(new java.awt.Font("Cascadia Code", 1, 14)); // NOI18N
        timetableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Jour", "08:30 - 10:15", "10:30 - 12:15", "14:30 - 16:15", "16:30 - 18:15"
            }
        ));
        timetableView.setRowHeight(40);
        timetableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        timetableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        timetableView.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(timetableView);

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setText("Emplois de temps");

        fieldField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFieldActionPerformed(evt);
            }
        });
        fieldField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldFieldKeyReleased(evt);
            }
        });

        jLabel1.setText("Fillière : ");

        jLabel2.setText("Rechercher par le nom de fillière:");

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(titleView))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fieldField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(titleView)
                .addGap(21, 21, 21)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
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

    private void fieldFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldFieldKeyReleased
        if (fieldField.getText().isEmpty()) {
            searchMode = false;
        } else {
            searchMode = true;
        }
        updateTimetableData();
    }//GEN-LAST:event_fieldFieldKeyReleased

    private void fieldFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFieldActionPerformed

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
            java.util.logging.Logger.getLogger(TimetableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimetableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimetableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimetableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimetableView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fieldField;
    private javax.swing.JLabel fieldLabel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable timetableView;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
