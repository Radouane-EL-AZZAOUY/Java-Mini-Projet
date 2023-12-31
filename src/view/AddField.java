package view;

import controller.FieldController;
import controller.InputsController;
import database.LocalDatabase;
import javax.swing.JOptionPane;
import model.FieldData;

/**
 *
 * @author user
 */
public class AddField extends javax.swing.JFrame {

    private FieldData fieldData;
    private FieldController fieldController;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;
    private int fieldID;

    public AddField() {
        initComponents();
        init();

    }

    public AddField(int fieldID, LocalDatabase.MODES MODE) {
        initComponents();
        init();
        this.MODE = MODE;
        this.fieldID = fieldID;
        if (MODE == LocalDatabase.MODES.UPDATE_MODE) {
            this.setTitle("Modifer fillière");
            saveBtn.setText("Modifier");
        } else if (MODE == LocalDatabase.MODES.DISPLAY_MODE) {
            this.setTitle("Détail de fillière");
            titleField.setEditable(false);
            maxLimitField.setEditable(false);
            saveBtn.setEnabled(false);
            clearBtn.setEnabled(false);
        }
        displayData(fieldID);
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ajouter Fillière");
        fieldController = new FieldController();
    }

    private void displayData(int fieldID) {
        fieldData = fieldController.getFieldData(fieldID);
        titleField.setText(fieldData.getTitle());
        maxLimitField.setText(fieldData.getMaxLimit() + "");
    }

    void clearInputs() {
        titleField.setText("");
        maxLimitField.setText("");
    }

    FieldData readInputs() {
        String title = titleField.getText();
        int maxLimit = Integer.parseInt(maxLimitField.getText());
        return new FieldData(0, title, maxLimit);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        titleField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        maxLimitField = new javax.swing.JTextField();
        titleView = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Initulé*:");

        jLabel2.setText("Capacité max*:");

        maxLimitField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                maxLimitFieldKeyReleased(evt);
            }
        });

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setText("Ajouter une filière");

        saveBtn.setBackground(new java.awt.Color(0, 0, 153));
        saveBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 204, 255));
        saveBtn.setText("Enregistrer");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
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

        closeBtn.setBackground(new java.awt.Color(204, 204, 204));
        closeBtn.setFont(new java.awt.Font("Cascadia Code", 0, 14)); // NOI18N
        closeBtn.setText("Fermer");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(titleField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(maxLimitField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(saveBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(clearBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maxLimitField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxLimitField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Intitulé:");
        jLabel1.getAccessibleContext().setAccessibleDescription("");

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
            fieldData = readInputs();
            if (fieldData.getTitle().isEmpty() || fieldData.getMaxLimit() == 0) {
                JOptionPane.showInputDialog(this, "Vérifer bien les données inserer!!!", "Les données sont incorrects", JOptionPane.ERROR_MESSAGE);
            } else {
                fieldData.setID(fieldID);
                boolean res = fieldController.saveFieldData(fieldData, MODE);
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

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new DataCRUDSView(DataCRUDSView.TYPES.FIELD).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void maxLimitFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maxLimitFieldKeyReleased
        InputsController.checkNumbersOnly(maxLimitField, 9999999);
    }//GEN-LAST:event_maxLimitFieldKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddField().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField maxLimitField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
