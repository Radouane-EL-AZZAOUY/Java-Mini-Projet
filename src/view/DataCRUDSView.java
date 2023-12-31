package view;

import controller.CourseController;
import controller.ExamController;
import controller.FieldController;
import controller.NoteController;
import controller.StudentController;
import controller.TeacherController;
import controller.TimetableController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.CourseData;
import model.ExamData;
import model.FieldData;
import model.NoteData;
import model.StudentData;
import model.TeacherData;
import model.TimetableData;

/**
 *
 * @author user
 */
public class DataCRUDSView extends javax.swing.JFrame {

    private TeacherController teacherController;
    private StudentController studentController;
    private FieldController fieldController;
    private CourseController courseController;
    private ExamController examController;
    private NoteController noteController;
    private TimetableController timetableController;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> searchMethodsComboBoxModel;
    private String[] searchMethods;
    private ArrayList<TeacherData> teachersData;
    private ArrayList<StudentData> studentsData;
    private ArrayList<FieldData> fieldsData;
    private ArrayList<CourseData> coursesData;
    private ArrayList<ExamData> examsData;
    private ArrayList<NoteData> notesData;
    private ArrayList<TimetableData> timetablesData;
    private boolean searchMode = false;
    private int rowSelectedIndex = -1;

    public static enum TYPES {
        TEACHER, STUDENT, FIELD, COURSE, EXAM, NOTE, TIMETABLE
    };
    private TYPES TYPE;

    private String colNames[];

    public DataCRUDSView() {
        initComponents();
        init();
    }

    public DataCRUDSView(TYPES TYPE) {
        initComponents();
        this.TYPE = TYPE;
        init();
        switch (TYPE) {
            case TEACHER ->
                initForTeachers();
            case STUDENT ->
                initForStudents();
            case FIELD -> {
                initForFields();
            }
            case COURSE -> {
                initForCourses();
            }
            case EXAM -> {
                initForExams();
            }
            case NOTE -> {
                initForNotes();
            }
            case TIMETABLE -> {
                initForTimetables();
            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }

    }

    private void init() {
        this.setTitle("Gestion de données");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        teacherController = new TeacherController();
        studentController = new StudentController();
        fieldController = new FieldController();
        courseController = new CourseController();
        examController = new ExamController();
        noteController = new NoteController();
        timetableController = new TimetableController();
        teachersData = new ArrayList<>();
        studentsData = new ArrayList<>();
        fieldsData = new ArrayList<>();
        coursesData = new ArrayList<>();
        examsData = new ArrayList<>();
        notesData = new ArrayList<>();
        timetablesData = new ArrayList();
        searchMethodsComboBoxModel = new DefaultComboBoxModel();
    }

    final void initForTeachers() {
        titleView.setText("Gestion des enseignants");
        colNames = new String[]{"Nom", "Prénom", "Age", "Spécialite", "Adresse", "Sexe", "Status", "Role"};
        searchMethods = new String[]{"Nom et Prénom", "Age", "Specialite", "Adresse", "Sexe (M=Homme|W=Femme)"};
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateTeachersData();
    }

    final void initForStudents() {
        titleView.setText("Gestion des étudiants");
        colNames = new String[]{"Nom", "Prénom", "Age", "Filière", "Adresse", "Sexe", "Date d'inscription", "Status (Act|Des)"};
        searchMethods = new String[]{"Nom et Prénom", "Age", "Filière", "Adresse", "Sexe (M=Homme|W=Femme)", "Date d'inscription"};
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateStudentsData();
    }

    final void initForFields() {
        titleView.setText("Gestion des filières");
        colNames = new String[]{"Intitulé de filière", "Capacité maximale"};
        searchMethods = colNames;
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateFieldsData();
    }

    final void initForCourses() {
        titleView.setText("Gestion des cours");
        colNames = new String[]{"Le titre", "La duration"};
        searchMethods = colNames;
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateCoursesData();
    }

    final void initForExams() {
        titleView.setText("Gestion des exams");
        colNames = new String[]{"La description", "La date", "La duration", "Fiellière", "Enseignant"};
        searchMethods = colNames;
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateExamsData();
    }

    final void initForNotes() {
        titleView.setText("Gestion des notes");
        colNames = new String[]{"L'examen", "Etudiant", "Note"};
        searchMethods = colNames;
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateNotesData();
    }

    final void initForTimetables() {
        titleView.setText("Gestion des emplois des temps");
        colNames = new String[]{"Le jour", "La filière", "08:30 - 10:15", "10:30 - 12:15", "14:30 - 16:15", "16:30 - 18:15"};
        searchMethods = new String[]{"La filière", "Le jour"};
        for (String item : searchMethods) {
            searchMethodsComboBoxModel.addElement(item);
        }
        searchMethodComboBox.setModel(searchMethodsComboBoxModel);
        updateTimetablesData();
    }

    private void updateTeachersData() {
        teachersData.clear();
        if (!searchMode) {
            teachersData = teacherController.getAllTeachers();
            searchMode = false;
        } else {
            String query = searchField.getText();
            teachersData = teacherController.searchTeachers(query, searchMethodComboBox.getSelectedIndex());
        }
        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateStudentsData() {
        studentsData.clear();
        if (!searchMode) {
            studentsData = studentController.getAllStudents();
            searchMode = false;
        } else {
            String query = searchField.getText();
            studentsData = studentController.searchStudents(query, searchMethodComboBox.getSelectedIndex());
        }
        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateFieldsData() {
        fieldsData.clear();
        if (!searchMode) {
            fieldsData = fieldController.getAllFields();
            searchMode = false;
        } else {
            String query = searchField.getText();
            fieldsData = fieldController.searchedFields(query, searchMethodComboBox.getSelectedIndex());
        }
        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateCoursesData() {
        coursesData.clear();

        if (!searchMode) {
            coursesData = courseController.getAllCourses();
            searchMode = false;
        } else {
            String query = searchField.getText();
            coursesData = courseController.searchedCourses(query, searchMethodComboBox.getSelectedIndex());
        }

        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateExamsData() {
        examsData.clear();

        if (!searchMode) {
            examsData = examController.getAllExams();
            searchMode = false;
        } else {
            String query = searchField.getText();
            examsData = examController.searchedExams(query, searchMethodComboBox.getSelectedIndex());
        }

        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateNotesData() {
        notesData.clear();

        if (!searchMode) {
            notesData = noteController.getAllNotes();
            searchMode = false;
        } else {
            String query = searchField.getText();
            notesData = noteController.searchedNotes(query, searchMethodComboBox.getSelectedIndex());
        }

        initTableModel();
        tableView.setModel(tableModel);
    }

    private void updateTimetablesData() {
        timetablesData.clear();

        if (!searchMode) {
            timetablesData = timetableController.getAllTimetables();
            searchMode = false;
        } else {
            String query = searchField.getText();
            timetablesData = timetableController.searchTimetables(query, searchMethodComboBox.getSelectedIndex());
        }

        initTableModel();
        tableView.setModel(tableModel);
    }

    void initTableModel() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(colNames);
        switch (TYPE) {
            case TEACHER -> {
                teacherController.initTableViewModel(teachersData, tableModel, courseController);
            }
            case STUDENT -> {
                studentController.initTableViewModel(studentsData, tableModel, fieldController);
            }
            case FIELD -> {
                fieldController.initTableViewModel(fieldsData, tableModel);
            }
            case COURSE -> {
                courseController.initTableViewModel(coursesData, tableModel);
            }
            case EXAM -> {
                examController.initTableViewModel(examsData, tableModel, teacherController, fieldController);
            }
            case NOTE -> {
                noteController.initTableViewModel(notesData, examController, studentController, tableModel);
            }
            case TIMETABLE -> {
                timetableController.initTableViewModel(timetablesData, fieldController, courseController, tableModel);
            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }
    }

    private void disableBtns() {
        deleteBtn.setEnabled(false);
        detailsBtn.setEnabled(false);
        updateBtn.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        searchField = new javax.swing.JTextField();
        titleView = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        detailsBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchMethodComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleView.setText("Data manager");
        titleView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        addBtn.setBackground(new java.awt.Color(0, 102, 0));
        addBtn.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(51, 255, 0));
        addBtn.setText("Ajouter");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(204, 204, 204));
        updateBtn.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(0, 102, 255));
        updateBtn.setText("Modifer");
        updateBtn.setEnabled(false);
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(204, 204, 204));
        deleteBtn.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 0, 0));
        deleteBtn.setText("Supprimer");
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(204, 204, 204));
        closeBtn.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        closeBtn.setText("Acueill");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        tableView.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableView.setFillsViewportHeight(true);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setShowVerticalLines(true);
        tableView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableView);

        detailsBtn.setBackground(new java.awt.Color(204, 204, 204));
        detailsBtn.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        detailsBtn.setForeground(new java.awt.Color(204, 102, 0));
        detailsBtn.setText("Details");
        detailsBtn.setEnabled(false);
        detailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Rechercher par:");

        jDesktopPane1.setLayer(searchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(addBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(updateBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(deleteBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(detailsBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(searchMethodComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(detailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchMethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleView)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchMethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
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

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        switch (TYPE) {
            case TEACHER ->
                new AddTeacher().setVisible(true);
            case STUDENT ->
                new AddStudent().setVisible(true);
            case FIELD ->
                new AddField().setVisible(true);
            case COURSE -> {
                new AddCourse().setVisible(true);
            }
            case EXAM -> {
                new AddExam().setVisible(true);
            }
            case NOTE -> {
                new AddNote().setVisible(true);
            }
            case TIMETABLE -> {
                new AddTimetable().setVisible(true);
            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }
        this.setVisible(false);
    }//GEN-LAST:event_addBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new Home().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int chx = JOptionPane.showConfirmDialog(this, "Voulez vous faire la suppression?");
        if (chx == JOptionPane.OK_OPTION) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    switch (TYPE) {
                        case TEACHER -> {
                            rowSelectedIndex = teachersData.get(tableView.getSelectedRow()).getID();
                            if (teacherController.deleteTeacher(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "L'enseignant est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateTeachersData();
                        }
                        case STUDENT -> {
                            rowSelectedIndex = studentsData.get(tableView.getSelectedRow()).getID();
                            if (studentController.deleteStudent(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "L'étudiant est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateStudentsData();
                        }
                        case FIELD -> {
                            rowSelectedIndex = fieldsData.get(tableView.getSelectedRow()).getID();
                            if (fieldController.deleteField(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "La filière est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateFieldsData();
                        }
                        case COURSE -> {
                            rowSelectedIndex = coursesData.get(tableView.getSelectedRow()).getID();
                            if (courseController.deleteCourse(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "Le cours est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateCoursesData();
                        }
                        case EXAM -> {
                            rowSelectedIndex = examsData.get(tableView.getSelectedRow()).getID();
                            if (examController.deleteExam(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "L'examen est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateExamsData();
                        }
                        case NOTE -> {
                            rowSelectedIndex = notesData.get(tableView.getSelectedRow()).getID();
                            if (noteController.deleteNote(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "La note est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateNotesData();
                        }
                        case TIMETABLE -> {
                            rowSelectedIndex = timetablesData.get(tableView.getSelectedRow()).getID();
                            if (timetableController.deleteTimetable(rowSelectedIndex)) {
                                JOptionPane.showMessageDialog(null, "Le jour est supprimé avec success.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de supprission.", "Echec", JOptionPane.ERROR_MESSAGE);
                            }
                            updateTimetablesData();
                        }
                        // TODO Add new case here.
                        default -> {
                        }
                    }
                }
            };
            thread.start();
        }
        disableBtns();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        switch (TYPE) {
            case TEACHER -> {
                rowSelectedIndex = teachersData.get(tableView.getSelectedRow()).getID();
                new AddTeacher(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case STUDENT -> {
                rowSelectedIndex = studentsData.get(tableView.getSelectedRow()).getID();
                new AddStudent(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case FIELD -> {
                rowSelectedIndex = fieldsData.get(tableView.getSelectedRow()).getID();
                new AddField(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case COURSE -> {
                rowSelectedIndex = coursesData.get(tableView.getSelectedRow()).getID();
                new AddCourse(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case EXAM -> {
                rowSelectedIndex = examsData.get(tableView.getSelectedRow()).getID();
                new AddExam(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case NOTE -> {
                rowSelectedIndex = notesData.get(tableView.getSelectedRow()).getID();
                new AddNote(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);
            }
            case TIMETABLE -> {
                rowSelectedIndex = timetablesData.get(tableView.getSelectedRow()).getID();
                new AddTimetable(rowSelectedIndex, LocalDatabase.MODES.UPDATE_MODE).setVisible(true);

            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }
        this.setVisible(false);
    }//GEN-LAST:event_updateBtnActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        disableBtns();
        if (searchField.getText().isEmpty()) {
            searchMode = false;
        } else {
            searchMode = true;
        }
        switch (TYPE) {
            case TEACHER -> {
                updateTeachersData();
            }
            case STUDENT -> {
                updateStudentsData();
            }
            case FIELD -> {
                updateFieldsData();
            }
            case COURSE -> {
                updateCoursesData();
            }
            case EXAM -> {
                updateExamsData();
            }
            case NOTE -> {
                updateNotesData();
            }
            case TIMETABLE -> {
                updateTimetablesData();
            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }
        System.out.println(searchField.getText());
    }//GEN-LAST:event_searchFieldKeyReleased

    private void tableViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewMouseClicked
        if (tableView.getSelectedRowCount() > 0) {
            deleteBtn.setEnabled(true);
            detailsBtn.setEnabled(true);
            updateBtn.setEnabled(true);
        }
    }//GEN-LAST:event_tableViewMouseClicked

    private void detailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtnActionPerformed
        switch (TYPE) {
            case TEACHER -> {
                rowSelectedIndex = teachersData.get(tableView.getSelectedRow()).getID();
                new AddTeacher(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case STUDENT -> {
                rowSelectedIndex = studentsData.get(tableView.getSelectedRow()).getID();
                new AddStudent(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case FIELD -> {
                rowSelectedIndex = fieldsData.get(tableView.getSelectedRow()).getID();
                new AddField(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case COURSE -> {
                rowSelectedIndex = coursesData.get(tableView.getSelectedRow()).getID();
                new AddCourse(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case EXAM -> {
                rowSelectedIndex = examsData.get(tableView.getSelectedRow()).getID();
                new AddExam(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case NOTE -> {
                rowSelectedIndex = notesData.get(tableView.getSelectedRow()).getID();
                new AddNote(rowSelectedIndex, LocalDatabase.MODES.DISPLAY_MODE).setVisible(true);
            }
            case TIMETABLE -> {
                rowSelectedIndex = timetablesData.get(tableView.getSelectedRow()).getFieldID();
                new TimetableView(rowSelectedIndex).setVisible(true);

            }
            // TODO Add new case here.
            default ->
                throw new AssertionError();
        }
        if (TYPE != TYPES.TIMETABLE) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_detailsBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataCRUDSView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton detailsBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> searchMethodComboBox;
    private javax.swing.JTable tableView;
    private javax.swing.JLabel titleView;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
