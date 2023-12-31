package view;

import controller.CourseController;
import controller.FieldController;
import controller.TimetableController;
import database.LocalDatabase;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.CourseData;
import model.FieldData;
import model.TimetableData;

public class AddTimetable extends javax.swing.JFrame {

    private TimetableController timetableController;
    private CourseController courseController;
    private FieldController fieldController;
    private ArrayList<CourseData> part1CoursesData;
    private ArrayList<CourseData> part2CoursesData;
    private ArrayList<CourseData> part3CoursesData;
    private ArrayList<CourseData> part4CoursesData;
    private ArrayList<FieldData> fieldsData;
    private DefaultComboBoxModel<String> part1ComboBoxModel;
    private DefaultComboBoxModel<String> part2ComboBoxModel;
    private DefaultComboBoxModel<String> part3ComboBoxModel;
    private DefaultComboBoxModel<String> part4ComboBoxModel;
    private DefaultComboBoxModel<String> fieldComboBoxModel;
    private DefaultComboBoxModel<String> dayComboBoxModel;
    private LocalDatabase.MODES MODE = LocalDatabase.MODES.INSERT_MODE;
    private int timetableID = -1;

    /*private int selectedPart = -1;
    private int selectedType = -1;*/
    private enum SearchMode {
        ALL, COURSES, FIELDS
    };

    private enum Parts {
        PART_1, PART_2, PART_3, PART_4
    };

    private enum Type {
        COURS, TD, TP
    };

    private Parts selectedPart = Parts.PART_1;
    private TimetableData timetableData;
    private final String days[] = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

    public AddTimetable() {
        initComponents();
        init();
        updateData("", SearchMode.ALL);
    }

    public AddTimetable(int timetableID, LocalDatabase.MODES MODE) {
        initComponents();

        this.timetableID = timetableID;
        this.MODE = MODE;

        init();
        updateData("", SearchMode.ALL);
        displayTimetableData();
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ajouter un emplois de temps");
        timetableController = new TimetableController();
        courseController = new CourseController();
        fieldController = new FieldController();
        part1CoursesData = new ArrayList();
        part2CoursesData = new ArrayList();
        part3CoursesData = new ArrayList();
        part4CoursesData = new ArrayList();
        fieldsData = new ArrayList();
        part1ComboBoxModel = new DefaultComboBoxModel();
        part2ComboBoxModel = new DefaultComboBoxModel();
        part3ComboBoxModel = new DefaultComboBoxModel();
        part4ComboBoxModel = new DefaultComboBoxModel();
        fieldComboBoxModel = new DefaultComboBoxModel();
        dayComboBoxModel = new DefaultComboBoxModel();

        for (String day : days) {
            dayComboBoxModel.addElement(day);
        }

        dayComboBox.setModel(dayComboBoxModel);
        part1ComboBoxModel.addElement("Vide");
        part2ComboBoxModel.addElement("Vide");
        part3ComboBoxModel.addElement("Vide");
        part4ComboBoxModel.addElement("Vide");
    }

    private void updateData(String query, AddTimetable.SearchMode searchMode) {
        switch (searchMode) {
            case ALL -> {
                fieldsData.clear();
                part1CoursesData.clear();
                part2CoursesData.clear();
                part3CoursesData.clear();
                part4CoursesData.clear();
                part1ComboBoxModel.removeAllElements();
                part2ComboBoxModel.removeAllElements();
                part3ComboBoxModel.removeAllElements();
                part4ComboBoxModel.removeAllElements();
                part1ComboBoxModel.addElement("Vide");
                part2ComboBoxModel.addElement("Vide");
                part3ComboBoxModel.addElement("Vide");
                part4ComboBoxModel.addElement("Vide");
                part1CoursesData = courseController.getAllCourses();
                part2CoursesData = part1CoursesData;
                part3CoursesData = part1CoursesData;
                part4CoursesData = part1CoursesData;
                fieldsData = fieldController.getAllFields();
                for (FieldData fieldData : fieldsData) {
                    fieldComboBoxModel.addElement(fieldData.getTitle());
                }

                for (CourseData courseData : part1CoursesData) {
                    part1ComboBoxModel.addElement(courseData.getTitle());
                    part2ComboBoxModel.addElement(courseData.getTitle());
                    part3ComboBoxModel.addElement(courseData.getTitle());
                    part4ComboBoxModel.addElement(courseData.getTitle());
                }

            }
            case FIELDS -> {
                fieldsData.clear();
                fieldComboBoxModel.removeAllElements();
                fieldsData = fieldController.searchedFields(query, 0);
                for (FieldData fieldData : fieldsData) {
                    fieldComboBoxModel.addElement(fieldData.getTitle());
                }
                //examComboBox.setModel(examsComboBoxModel);
            }
            case COURSES -> {

                switch (selectedPart) {
                    case PART_1 -> {
                        part1CoursesData.clear();
                        if (query.isEmpty()) {
                            part1CoursesData = courseController.getAllCourses();
                        } else {
                            part1CoursesData = courseController.searchedCourses(query, 0);
                        }
                        part1ComboBoxModel.removeAllElements();
                        part1ComboBoxModel.addElement("Vide");
                        for (CourseData courseData : part1CoursesData) {
                            part1ComboBoxModel.addElement(courseData.getTitle());
                        }
                    }
                    case PART_2 -> {
                        part2CoursesData.clear();
                        if (query.isEmpty()) {
                            part2CoursesData = courseController.getAllCourses();
                        } else {
                            part2CoursesData = courseController.searchedCourses(query, 0);
                        }
                        part2ComboBoxModel.removeAllElements();
                        part2ComboBoxModel.addElement("Vide");
                        for (CourseData courseData : part2CoursesData) {
                            part2ComboBoxModel.addElement(courseData.getTitle());
                        }
                    }
                    case PART_3 -> {
                        part3CoursesData.clear();
                        if (query.isEmpty()) {
                            part3CoursesData = courseController.getAllCourses();
                        } else {
                            part3CoursesData = courseController.searchedCourses(query, 0);
                        }
                        part3ComboBoxModel.removeAllElements();
                        part3ComboBoxModel.addElement("Vide");
                        for (CourseData courseData : part3CoursesData) {
                            part3ComboBoxModel.addElement(courseData.getTitle());
                        }
                    }
                    case PART_4 -> {
                        part4CoursesData.clear();
                        if (query.isEmpty()) {
                            part4CoursesData = courseController.getAllCourses();
                        } else {
                            part4CoursesData = courseController.searchedCourses(query, 0);
                        }
                        part4ComboBoxModel.removeAllElements();
                        part4ComboBoxModel.addElement("Vide");
                        for (CourseData courseData : part4CoursesData) {
                            part4ComboBoxModel.addElement(courseData.getTitle());
                        }
                    }
                    default ->
                        throw new AssertionError();
                }

                //studentComboBox.setModel(studentComboBoxModel);
            }
            default ->
                throw new AssertionError();
        }

        fieldComboBox.setModel(fieldComboBoxModel);
        part1ComboBox.setModel(part1ComboBoxModel);
        part2ComboBox.setModel(part2ComboBoxModel);
        part3ComboBox.setModel(part3ComboBoxModel);
        part4ComboBox.setModel(part4ComboBoxModel);
    }

    private void displayTimetableData() {
        timetableData = timetableController.getTimetableData(timetableID);
        int fieldIndex = 0;
        int part1Index = 0;
        int part2Index = 0;
        int part3Index = 0;
        int part4Index = 0;
        for (int i = 0; i < fieldsData.size(); i++) {
            if (fieldsData.get(i).getID() == timetableData.getFieldID()) {
                fieldIndex = i;
                break;
            }
        }
        for (int i = 0; i < part1CoursesData.size(); i++) {
            if (part1CoursesData.get(i).getID() == timetableData.getPart1()) {
                part1Index = i+1;
                break;
            }
        }
        for (int i = 0; i < part2CoursesData.size(); i++) {
            if (part2CoursesData.get(i).getID() == timetableData.getPart2()) {
                part2Index = i+1;
                break;
            }
        }
        for (int i = 0; i < part3CoursesData.size(); i++) {
            if (part3CoursesData.get(i).getID() == timetableData.getPart3()) {
                part3Index = i+1;
                break;
            }
        }
        for (int i = 0; i < part4CoursesData.size(); i++) {
            if (part4CoursesData.get(i).getID() == timetableData.getPart4()) {
                part4Index = i+1;
                break;
            }
        }

        fieldComboBox.setSelectedIndex(fieldIndex);
        dayComboBox.setSelectedIndex(timetableData.getDay());
        part1ComboBox.setSelectedIndex(part1Index);
        part2ComboBox.setSelectedIndex(part2Index);
        part3ComboBox.setSelectedIndex(part3Index);
        part4ComboBox.setSelectedIndex(part4Index);
        /*switch (timetableData.getType()) {
            case 1 ->
                courseRadioButton.setSelected(true);
            case 2 ->
                tdRadioButton.setSelected(true);
            case 3 ->
                tpRadioButton.setSelected(true);
            default ->
                throw new AssertionError();
        }*/

    }

    private TimetableData readInputs() {
        int fieldID = fieldsData.get(fieldComboBox.getSelectedIndex()).getID();
        int part1Index = 0;
        int part2Index = 0;
        int part3Index = 0;
        int part4Index = 0;
        if (part1ComboBoxModel.getSize() > 0) {
            part1Index = part1ComboBox.getSelectedIndex();
        }
        if (part2ComboBoxModel.getSize() > 0) {
            part2Index = part2ComboBox.getSelectedIndex();
        }
        if (part3ComboBoxModel.getSize() > 0) {
            part3Index = part3ComboBox.getSelectedIndex();
        }
        if (part4ComboBoxModel.getSize() > 0) {
            part4Index = part4ComboBox.getSelectedIndex();
        }
        
        int part1ID = part1Index == 0 ? 0 : part1CoursesData.get(part1Index - 1).getID();
        int part2ID = part2Index == 0 ? 0 : part2CoursesData.get(part2Index - 1).getID();
        int part3ID = part3Index == 0 ? 0 : part3CoursesData.get(part3Index - 1).getID();
        int part4ID = part4Index == 0 ? 0 : part4CoursesData.get(part4Index - 1).getID();
        System.out.println("Part1Index: "+ part1Index);
        /*int type;
        switch (selectedType) {
            case COURS ->
                type = 1;
            case TD ->
                type = 2;
            case TP ->
                type = 3;
            default ->
                type = -1;
        }*/
        return new TimetableData(0, fieldID, dayComboBox.getSelectedIndex(), part1ID, part2ID, part3ID, part4ID);
    }

    private void clearInputs() {
        init();
        fieldSearchField.setText("");
        searchCourField.setText("");
        fieldComboBox.setSelectedIndex(0);
        part1ComboBox.setSelectedIndex(0);
        part2ComboBox.setSelectedIndex(0);
        part3ComboBox.setSelectedIndex(0);
        part4ComboBox.setSelectedIndex(0);
        /*courseRadioButton.setSelected(true);
        tdRadioButton.setSelected(false);
        tpRadioButton.setSelected(false);*/
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        titleView = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldSearchField = new javax.swing.JTextField();
        fieldComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dayComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        searchCourField = new javax.swing.JTextField();
        closeBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        part2RadioButton = new javax.swing.JRadioButton();
        part1RadioButton = new javax.swing.JRadioButton();
        part3RadioButton = new javax.swing.JRadioButton();
        part4RadioButton = new javax.swing.JRadioButton();
        part1ComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        part2ComboBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        part3ComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        part4ComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        titleView.setFont(new java.awt.Font("Cascadia Code", 0, 24)); // NOI18N
        titleView.setForeground(new java.awt.Color(0, 0, 153));
        titleView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleView.setText("Ajouter une emplois de temps");

        jLabel9.setText("Rechercher un fillière:");

        jLabel6.setText("Fillière*:");

        fieldSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fieldSearchFieldKeyReleased(evt);
            }
        });

        jLabel11.setText("08:30 - 10:15");

        jLabel7.setText("Jour dans la semaine*:");

        jLabel10.setText("Rechercher un cours:");

        searchCourField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchCourFieldKeyReleased(evt);
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

        part2RadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(part2RadioButton);
        part2RadioButton.setText("10:30 - 12:15");
        part2RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                part2RadioButtonActionPerformed(evt);
            }
        });

        part1RadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(part1RadioButton);
        part1RadioButton.setSelected(true);
        part1RadioButton.setText("08:30 - 10:15");
        part1RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                part1RadioButtonActionPerformed(evt);
            }
        });

        part3RadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(part3RadioButton);
        part3RadioButton.setText("14:30 - 16:15");
        part3RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                part3RadioButtonActionPerformed(evt);
            }
        });

        part4RadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(part4RadioButton);
        part4RadioButton.setText("16:30 - 18:15");
        part4RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                part4RadioButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("10:30 - 12:15");

        jLabel13.setText("14:30 - 16:15");

        jLabel14.setText("16:30 - 18:15");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jDesktopPane1.setLayer(titleView, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldSearchField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fieldComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(dayComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(searchCourField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(closeBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(clearBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(saveBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part2RadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part1RadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part3RadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part4RadioButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part1ComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part2ComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part3ComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(part4ComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addGap(254, 254, 254)
                            .addComponent(titleView))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fieldSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(searchCourField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                    .addComponent(part1RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(part2RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(part3RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(part4RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(6, 6, 6)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(15, 15, 15)
                            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(part1ComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(part2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part4ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(titleView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(part4ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(fieldSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(part1RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(part2RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(part3RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(part4RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchCourField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, Short.MAX_VALUE)
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

    private void fieldSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldSearchFieldKeyReleased
        String query = fieldSearchField.getText();
        if (query.isEmpty()) {
            updateData("", SearchMode.ALL);
        } else {
            updateData(query, SearchMode.FIELDS);
        }
    }//GEN-LAST:event_fieldSearchFieldKeyReleased

    private void searchCourFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCourFieldKeyReleased
        String query = searchCourField.getText().replace(" ", "");
        updateData(query, SearchMode.COURSES);
    }//GEN-LAST:event_searchCourFieldKeyReleased

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        new DataCRUDSView(DataCRUDSView.TYPES.TIMETABLE).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearInputs();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            timetableData = readInputs();
            timetableData.setID(timetableID);
            boolean res = timetableController.saveTimetableData(timetableData, MODE);
            if (res) {
                JOptionPane.showMessageDialog(this, "L'operation a été effectuer avec succes", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Les données sont incorrects!", "Les donnée sont incorrect", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void part1RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_part1RadioButtonActionPerformed
        selectedPart = Parts.PART_1;
        updateData(searchCourField.getText().replace(" ", ""), SearchMode.COURSES);
    }//GEN-LAST:event_part1RadioButtonActionPerformed

    private void part2RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_part2RadioButtonActionPerformed
        selectedPart = Parts.PART_2;
        updateData(searchCourField.getText().replace(" ", ""), SearchMode.COURSES);
    }//GEN-LAST:event_part2RadioButtonActionPerformed

    private void part3RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_part3RadioButtonActionPerformed
        selectedPart = Parts.PART_3;
        updateData(searchCourField.getText().replace(" ", ""), SearchMode.COURSES);
    }//GEN-LAST:event_part3RadioButtonActionPerformed

    private void part4RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_part4RadioButtonActionPerformed
        selectedPart = Parts.PART_4;
        updateData(searchCourField.getText().replace(" ", ""), SearchMode.COURSES);
    }//GEN-LAST:event_part4RadioButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTimetable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTimetable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JComboBox<String> dayComboBox;
    private javax.swing.JComboBox<String> fieldComboBox;
    private javax.swing.JTextField fieldSearchField;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> part1ComboBox;
    private javax.swing.JRadioButton part1RadioButton;
    private javax.swing.JComboBox<String> part2ComboBox;
    private javax.swing.JRadioButton part2RadioButton;
    private javax.swing.JComboBox<String> part3ComboBox;
    private javax.swing.JRadioButton part3RadioButton;
    private javax.swing.JComboBox<String> part4ComboBox;
    private javax.swing.JRadioButton part4RadioButton;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField searchCourField;
    private javax.swing.JLabel titleView;
    // End of variables declaration//GEN-END:variables
}
