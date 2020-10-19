package View;

import model.Course;
import model.CoursePart;
import model.GradingScale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ExaminationPanel
 * Implements CoursePanel
 *
 * Handles all components in the JPanel component mainPanel
 * used when the user is entering examination attributes
 * of the course.
 *
 * @author Mikael Stener, Sofia Karbin (see annotations)
 */

public class ExaminationPanel implements CoursePanel {
    /**
     * Swing components
     */
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel noPartsExaminationPanel;
    private JPanel partsExaminationPanel;
    private JCheckBox homeExamCheckBox;
    private JPanel homeExamPanel;
    private JCheckBox examinationOnEnglishCheckBox;
    private JPanel notDistanceAttendancePanel;
    private JPanel distanceAttendancePanel;
    private JCheckBox hasAttendanceCheckBox;
    private JPanel attendancePanel;
    private JPanel englishExaminationPanel;
    private JLabel partLabel1;
    private JLabel partLabel2;
    private JLabel partLabel3;
    private JLabel partLabel4;
    private JLabel partLabel5;
    private JLabel partLabel6;
    private JComboBox<String> gradingScale1;
    private JComboBox<String> gradingScale2;
    private JComboBox<String> gradingScale3;
    private JComboBox<String> gradingScale4;
    private JComboBox<String> gradingScale5;
    private JComboBox<String> gradingScale6;
    private JRadioButton homeExamRadio1;
    private JRadioButton homeExamRadio2;
    private JRadioButton englishRadio1;
    private JRadioButton englishRadio2;
    private JRadioButton totalGradeRadio1;
    private JRadioButton totalGradeRadio2;
    private JRadioButton totalGradeRadio3;
    private JCheckBox otherActivitiesCheckBox;
    private JTextPane otherActivitiesPane;
    private JPanel otherActivitiesGradePanel;
    private JButton printOutButton;
    private JTextPane printOutPane;
    private JLabel partExaminationLabel1;
    private JLabel partExaminationLabel2;
    private JLabel partExaminationLabel3;
    private JLabel partExaminationLabel4;
    private JLabel partExaminationLabel5;
    private JLabel partExaminationLabel6;
    private JTextField partExaminationField1;
    private JTextField partExaminationField2;
    private JTextField partExaminationField3;
    private JTextField partExaminationField4;
    private JTextField partExaminationField5;
    private JTextField partExaminationField6;
    private JRadioButton supplementRadio1;
    private JRadioButton supplementRadio2;
    private JRadioButton supplementRadio3;
    private JRadioButton noSupplementRadio1;
    private JRadioButton noSupplementRadio2;
    private JCheckBox supplementCheckBox;
    private JPanel gradingPanel;
    private JTextPane ePane;
    private JTextPane examinationPane;
    private JComboBox<String> courseGradingScaleComboBox;
    private JRadioButton gradeCertainPart1;
    private JRadioButton gradeCertainPart2;
    private JRadioButton gradeCertainPart3;
    private JRadioButton gradeCertainPart4;
    private JRadioButton gradeCertainPart5;
    private JRadioButton gradeCertainPart6;
    private JPanel gradeCertainPartsPanel;
    private JTextPane totalGradeAlt3TextPane;
    private JTextPane notDistanceAttendancePane;
    private JTextPane distanceAttendancePane;
    private JSplitPane splitPane;
    private JButton saveButton;

    /**
     * Collections of swing components
     */
    private final JLabel[] examinationLabels = {
            partExaminationLabel1,
            partExaminationLabel2,
            partExaminationLabel3,
            partExaminationLabel4,
            partExaminationLabel5,
            partExaminationLabel6,
    };
    private final JTextField[] examinationFields = {
            partExaminationField1,
            partExaminationField2,
            partExaminationField3,
            partExaminationField4,
            partExaminationField5,
            partExaminationField6
    };
    private final JLabel[] gradingScalePartLabels = {
            partLabel1,
            partLabel2,
            partLabel3,
            partLabel4,
            partLabel5,
            partLabel6,
    };
    private final String[] gradingScaleStrings = GradingScale.getGradingScaleStrings();
    private final String[] gradingScaleStringsLong = GradingScale.getLongGradingScaleStrings();
    private final ArrayList<JComboBox<String>> gradingScaleComboBoxes = new ArrayList<>();
    private final JRadioButton[] supplementRadios = {
            supplementRadio1,
            supplementRadio2,
            supplementRadio3,
            noSupplementRadio1,
            noSupplementRadio2
    };

    private final JRadioButton[] gradeCertainPartsRadios = {
            gradeCertainPart1,
            gradeCertainPart2,
            gradeCertainPart3,
            gradeCertainPart4,
            gradeCertainPart5,
            gradeCertainPart6
    };

    /**
     * Course attributes
     */

    private ArrayList<CoursePart> courseParts;
    private int nParts = 0;
    private boolean hasParts = false;
    private boolean isDistance = false;
    private final GradingScale gradingScale = new GradingScale();
    private boolean thesis = false;

    /**
     * MainFrame attributes
     */
    Properties properties;

    /**
     * Constructors
     */

    public ExaminationPanel(MainFrame frame) {
        setUpComponents();

        setUpGradingScalesComboBoxes();
        addActionListeners();
        properties = frame.getProperties();
        setToolTips();
    }

    public ExaminationPanel() {
        setUpGradingScalesComboBoxes();
    }

    public ExaminationPanel(MainFrame frame, Course course) {
        setUpComponents();
        setUpGradingScalesComboBoxes();
        addActionListeners();
        properties = frame.getProperties();
        setToolTips();

        setGradingScaleForCourse(course);

        if (course.getCourseParts().isEmpty() && course.getExamination() != null) {
            examinationPane.setText(course.getExamination());
        }

        setHomeExamButtonsForCourse(course);
        setExaminationLanguageButtonsForCourse(course);
        setOtherActivitiesForCourse(course);
        setAttendanceFieldsForCourse(course);
        setSupplementsForCourse(course);

    }

    /**
     * Helper methods to constructors
     */

    private void setSupplementsForCourse(Course course) {
        supplementCheckBox.setSelected(course.areSupplementsAllowed());
        updateSupplementRadios();

        int supplementAlternative = course.getSupplementAlternative();
        IntStream.range(0, supplementRadios.length).forEach(i -> {
            supplementRadios[i].setSelected(false);
            if (i == supplementAlternative) {
                supplementRadios[i].setSelected(true);
            }
        });

    }

    private void setHomeExamButtonsForCourse(Course course) {
        homeExamCheckBox.setSelected(course.hasHomeExam());
        if (homeExamCheckBox.isSelected()) {
            homeExamRadio1.setSelected(course.isLateHomeExamNotExamined());
            homeExamRadio2.setSelected(!course.isLateHomeExamNotExamined());
        }
    }

    private void setExaminationLanguageButtonsForCourse(Course course) {
        examinationOnEnglishCheckBox.setSelected(course.isExaminationPartiallyInEnglish());
        if (examinationOnEnglishCheckBox.isSelected()) {
            englishRadio1.setSelected(course.isExaminationInEnglish());
            englishRadio2.setSelected(!course.isExaminationInEnglish());
        }
    }

    private void setOtherActivitiesForCourse(Course course) {
        otherActivitiesCheckBox.setSelected(course.getOtherActivitiesAffectGrade());
        otherActivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
        if (otherActivitiesCheckBox.isSelected() && course.getOtherActivitiesThatAffectGrade() != null) {
            otherActivitiesPane.setText(course.getOtherActivitiesThatAffectGrade());
        }
    }

    private void setAttendanceFieldsForCourse(Course course) {
        hasAttendanceCheckBox.setSelected(course.isAttendanceRequired());
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());

        if (hasAttendanceCheckBox.isSelected()) {
            distanceAttendancePanel.setVisible(isDistance);
            notDistanceAttendancePanel.setVisible(!isDistance);
            if (course.isDistance() && course.getDisanceAttendanceText() != null) {
                distanceAttendancePane.setText(course.getDisanceAttendanceText());
            } else if (!course.isDistance() && course.getNotDistanceAttendanceText() != null) {
                notDistanceAttendancePane.setText(course.getNotDistanceAttendanceText());
            }
        }
    }

    private void setGradingScaleForCourse(Course course) {
        if (course.getGradingScale() != null) {
            int lengthOfArray = course.getGradingScale().size();
            if (lengthOfArray==7) {
                courseGradingScaleComboBox.setSelectedIndex(0);
            } else if (lengthOfArray==3) {
                courseGradingScaleComboBox.setSelectedIndex(1);
            } else if (lengthOfArray==2) {
                courseGradingScaleComboBox.setSelectedIndex(2);
            }
        }
    }

    /**
     * Set initial states of components.
     */
    private void setUpComponents() {
        supplementCheckBox.setSelected(true);
        supplementRadio1.setSelected(true);
        gradeCertainPartsPanel.setVisible(false);
        totalGradeAlt3TextPane.setVisible(false);
    }

    /**
     * Add items to grading scale ComboBoxes.
     */
    private void setUpGradingScalesComboBoxes() {
        gradingScaleComboBoxes.add(gradingScale1);
        gradingScaleComboBoxes.add(gradingScale2);
        gradingScaleComboBoxes.add(gradingScale3);
        gradingScaleComboBoxes.add(gradingScale4);
        gradingScaleComboBoxes.add(gradingScale5);
        gradingScaleComboBoxes.add(gradingScale6);

        for (String gradingScaleString : gradingScaleStrings) {
            courseGradingScaleComboBox.addItem(gradingScaleString);
        }

        for (String gradingScaleString : gradingScaleStringsLong) {
            for (JComboBox<String> gradingScale : gradingScaleComboBoxes) {
                gradingScale.addItem(gradingScaleString);
            }
        }
    }

    /**
     * Add action listeners to components.
     */
    private void addActionListeners() {
        homeExamCheckBox.addActionListener(e -> updateHomeExamPanel());
        examinationOnEnglishCheckBox.addActionListener(e -> updateEnglishExaminationPanel());
        hasAttendanceCheckBox.addActionListener(e -> updateAttendancePanel());
        homeExamRadio1.addActionListener(e -> updateHomeExamRadios(homeExamRadio2));
        homeExamRadio2.addActionListener(e -> updateHomeExamRadios(homeExamRadio1));
        englishRadio1.addActionListener(e -> updateEnglishRadios(englishRadio2));
        englishRadio2.addActionListener(e -> updateEnglishRadios(englishRadio1));
        totalGradeRadio1.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio2, totalGradeRadio3));
        totalGradeRadio2.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio3, totalGradeRadio1));
        totalGradeRadio3.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio1, totalGradeRadio2));
        otherActivitiesCheckBox.addActionListener(e -> updateOtherActivitiesGradePanel());
        supplementCheckBox.addActionListener(e -> updateSupplementRadios());
        for (JRadioButton radio : supplementRadios) radio.addActionListener(e -> {
            for (JRadioButton otherRadio : supplementRadios) {
                if (otherRadio != radio) otherRadio.setSelected(false);
            }
        });
        printOutButton.addActionListener(e -> printOut());
    }

    /**
     * Setting tool tips (help text popups).
     */
    private void setToolTips() {
        ePane.setToolTipText(properties.getProperty("examinationEPaneToolTip"));
        supplementRadio1.setToolTipText(properties.getProperty("examinationFPaneAlt1ToolTip"));
        supplementRadio2.setToolTipText(properties.getProperty("examinationFPaneAlt2ToolTip"));
        supplementRadio3.setToolTipText(properties.getProperty("examinationFPaneAlt3ToolTip"));
        noSupplementRadio1.setToolTipText(properties.getProperty("examinationFPaneAlt4ToolTip"));
        noSupplementRadio2.setToolTipText(properties.getProperty("examinationFPaneAlt5ToolTip"));
    }

    /**
     * Methods from interface CoursePanel
     */

    /**
     * Returns JPanel with all content.
     * @return
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Returns next panel button.
     * @return
     */
    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    /**
     * Returns previous panel button.
     * @return
     */
    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    /**
     * Returns button to save course.
     * @return
     */
    public JButton getSaveButton() {return saveButton;}

    /**
     * Returns title to be set in MainFrame when this panel is shown.
     * @return A string from properties file.
     */
    public String getFrameName() {
        return properties.getProperty("ExaminationTitle");
    }

    /**
     * Returns the JSplitPane of this panel.
     * @return
     */
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    /**
     * Updates components of the panel based on previously entered attributes of course.
     * @param course
     */
    public void updateView(Course course) {
        updateCourseAttributes(course);
        updateComponents();
        setLabelNames();
        if (!course.getCourseParts().isEmpty()) {
            setExaminationFieldsForCourseParts(course);
            setGradingScalesForCourseParts(course);
        }

        if (course.isTotalGradeFromAllParts() || course.isTotalGradeFromSomeParts() || course.getTotalGradeAlt3Text() != null) {
            setTotalGradeForCourse(course);
        }
    }

    /**
     * Helper methods for updateView
     */


    private void setGradingScalesForCourseParts(Course course) {
        int size = course.getCourseParts().size();

        IntStream.range(0,size).forEach(i -> {
            JComboBox<String> stringJComboBox = gradingScaleComboBoxes.get(i);
            if (course.getCourseParts().get(i).getGradingScale() != null) {
                ArrayList<String> gradingScale = course.getCourseParts().get(i).getGradingScale();
                int lengthOfArray = gradingScale.size();
                if (lengthOfArray==7) {
                    stringJComboBox.setSelectedIndex(0);
                } else if (lengthOfArray==3) {
                    stringJComboBox.setSelectedIndex(1);
                } else if (lengthOfArray==2) {
                    stringJComboBox.setSelectedIndex(2);
                }
            }
        });
    }

    private void setExaminationFieldsForCourseParts(Course course) {
        int size = course.getCourseParts().size();

        IntStream.range(0,size).forEach(i -> {
            JTextField examinationField = examinationFields[i];
            if (course.getCourseParts().get(i).getExamination() != null) {
                String examination = course.getCourseParts().get(i).getExamination();
                examinationField.setText(examination);
            } else {
                examinationField.setText("");
            }
        });
    }

    private void setTotalGradeForCourse(Course course) {

        totalGradeRadio1.setVisible(!course.getCourseParts().isEmpty());
        totalGradeRadio1.setSelected(course.isTotalGradeFromAllParts());
        totalGradeRadio2.setVisible(!course.getCourseParts().isEmpty());
        totalGradeRadio2.setSelected((totalGradeRadio2.isVisible() && course.isTotalGradeFromSomeParts()));
        gradeCertainPartsPanel.setVisible((totalGradeRadio2.isVisible() && totalGradeRadio2.isSelected()));
        totalGradeRadio3.setSelected((course.getTotalGradeAlt3Text() != null && !course.getTotalGradeAlt3Text().isEmpty()));
        totalGradeAlt3TextPane.setVisible(totalGradeRadio3.isSelected());

        if (totalGradeRadio2.isSelected()) {
            Arrays.stream(gradeCertainPartsRadios).filter(Component::isVisible).forEach(jRadioButton -> {
                List<CoursePart> collect = course.getCourseParts().stream()
                        .filter(part -> part.getName().toLowerCase().equals(jRadioButton.getText().toLowerCase()))
                        .collect(Collectors.toList());
                jRadioButton.setSelected(collect.get(0).getDecidesTotalGrade());
            });
        }

        if (totalGradeAlt3TextPane.isVisible() && course.getTotalGradeAlt3Text() != null) {
            totalGradeAlt3TextPane.setText(course.getTotalGradeAlt3Text());
        }

    }

    /**
     * Updates course attributes based on previously entered attributes of course.
     * @param course
     */
    private void updateCourseAttributes(Course course) {
        courseParts = course.getCourseParts();
        nParts = courseParts.size();
        hasParts = nParts > 0;
        isDistance = course.isDistance();
        thesis = course.hasThesis();
    }

    /**
     * Updates swing components based on previously entered attributes of course.
     */
    private void updateComponents() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
        distanceAttendancePanel.setVisible(isDistance);
        notDistanceAttendancePanel.setVisible(!isDistance);
        partsExaminationPanel.setVisible(hasParts);
        noPartsExaminationPanel.setVisible(!hasParts);
        gradingPanel.setVisible(hasParts);
        totalGradeRadio1.setVisible(hasParts);
        totalGradeRadio2.setVisible(hasParts);
        otherActivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
        for (int i = 0; i < examinationLabels.length; i++) { // TODO global MaxParts?
            examinationLabels[i].setVisible(i < nParts);
            examinationFields[i].setVisible(i < nParts);
            gradingScalePartLabels[i].setVisible(i < nParts);
            gradingScaleComboBoxes.get(i).setVisible(i < nParts);
            gradeCertainPartsRadios[i].setVisible(i < nParts);
        }
        if (thesis) {
            supplementCheckBox.setSelected(true);
            supplementCheckBox.setEnabled(false);
        }
        updateSupplementRadios();
    }

    /**
     * Sets label names based on previously entered attributes of course.
     */
    private void setLabelNames() {
        for (int i = 0; i < nParts; i++) {
            String partName = courseParts.get(i).getName();
            String examinationLabelText = "Kunskapskontroll för " + partName + " sker genom ";
            examinationLabels[i].setText(examinationLabelText);
            gradingScalePartLabels[i].setText(partName);
            gradeCertainPartsRadios[i].setText(partName);
        }
    }

    /**
     * Action listener methods
     */

    /**
     * Showing homeExamPanel based on whether there is a home exam in the course or not.
     */
    private void updateHomeExamPanel() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        homeExamRadio1.setSelected(true);
        homeExamRadio2.setSelected(false);
    }

    /**
     * Sets radio to false, so that only one radio button at a time can be chosen.
     * @param radio
     */
    private void updateHomeExamRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    /**
     * Showing englishExaminationPanel based on if examination might be in English.
     */
    private void updateEnglishExaminationPanel() {
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        englishRadio1.setSelected(true);
        englishRadio2.setSelected(false);
    }

    /**
     * Sets radio to false, so that only one radio button at a time can be chosen.
     * @param radio
     */
    private void updateEnglishRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    /**
     * Shows attendancePanel based on if there are attendance requirements in the course.
     */
    private void updateAttendancePanel() {
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
    }

    /**
     * Sets radio1 and radio2 to false, so that only one radio button at a time can be chosen.
     * Shows gradeCertainPartsPanel based on if certain parts decide course grade.
     * Shows totalGradeAlt3TextPane based on if course grade is set custom.
     * @param radio1
     * @param radio2
     */
    private void updateTotalGradeRadios(JRadioButton radio1, JRadioButton radio2) {
        radio1.setSelected(false);
        radio2.setSelected(false);
        gradeCertainPartsPanel.setVisible(totalGradeRadio2.isSelected());
        totalGradeAlt3TextPane.setVisible(totalGradeRadio3.isSelected());
    }

    /**
     * Shows otherActivitiesGradePanel based on if there are other activities deciding course grade.
     */
    private void updateOtherActivitiesGradePanel() {
        otherActivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
    }

    /**
     * Sets supplement radios visible or not depending on if there are ways of supplementing course grade.
     */
    private void updateSupplementRadios() {
        supplementRadio1.setVisible(supplementCheckBox.isSelected());
        supplementRadio2.setVisible(supplementCheckBox.isSelected());
        supplementRadio3.setVisible(supplementCheckBox.isSelected());
        noSupplementRadio1.setVisible(!supplementCheckBox.isSelected());
        noSupplementRadio2.setVisible(!supplementCheckBox.isSelected());
    }

    /**
     * Getters
     */

    public JTextPane getExaminationPane() {
        return examinationPane;
    }

    public JTextField[] getExaminationFields(){return examinationFields;}

    public JCheckBox getHomeExamCheckBox() {
        return homeExamCheckBox;
    }

    public JRadioButton getHomeExamRadio1() {
        return homeExamRadio1;
    }

    public JCheckBox getExaminationOnEnglishCheckBox() {
        return examinationOnEnglishCheckBox;
    }

    public JRadioButton getEnglishRadio1() {
        return englishRadio1;
    }

    public JRadioButton getEnglishRadio2() {
        return englishRadio2;
    }

    public JCheckBox getHasAttendanceCheckBox() {
        return hasAttendanceCheckBox;
    }

    public JTextPane getDistanceAttendancePane() {
        return distanceAttendancePane;
    }

    public JTextPane getNotDistanceAttendancePane() {
        return notDistanceAttendancePane;
    }

    public JComboBox<String> getCourseGradingScaleComboBox() {
        return courseGradingScaleComboBox;
    }

    public ArrayList<JComboBox<String>> getGradingScales() {
        return gradingScaleComboBoxes;
    }

    public JRadioButton getTotalGradeRadio1() {
        return totalGradeRadio1;
    }

    public JRadioButton getTotalGradeRadio2() {
        return totalGradeRadio2;
    }

    public JRadioButton getTotalGradeRadio3() {
        return totalGradeRadio3;
    }

    public JRadioButton[] getGradeCertainPartsRadios() {
        return gradeCertainPartsRadios;
    }

    public JTextPane getTotalGradeAlt3TextPane() {
        return totalGradeAlt3TextPane;
    }

    public JCheckBox getOtherActivitiesCheckBox() {
        return otherActivitiesCheckBox;
    }

    public JTextPane getOtherActivitiesPane() {
        return otherActivitiesPane;
    }

    public JCheckBox getSupplementCheckBox() {
        return supplementCheckBox;
    }

    public JRadioButton[] getSupplementRadios() {
        return new JRadioButton[]{
                supplementRadio1,
                supplementRadio2,
                supplementRadio3,
                noSupplementRadio1,
                noSupplementRadio2
        };
    }

    public JTextPane getPrintOutPane() {
        return printOutPane;
    }

    /**
     * Print out methods
     */

    /**
     * Updates text in printOutPane.
     */
    public void printOut() {
        // a. /////////
        String outPutText = aPrintOut();
        // b. /////////
        outPutText += bPrintOut();
        // c. /////////
        outPutText += cPrintOut();
        // d. /////////
        outPutText += dPrintOut();
        // e. /////////
        outPutText += ePrintOut();
        // f. /////////
        outPutText += fPrintOut();
        // Möjlighet till komplettering

        printOutPane.setText(outPutText);
    }

    /**
     * Returns text to be printed in panel a.
     * @return
     */
    private String aPrintOut() {
        String outPutText = "a. Kursen examineras på följande vis: \n";

        if (!hasParts) {
            outPutText += examinationPane.getText() + "\n\n";
        } else {
            for (int i = 0; i < nParts; i++) {
                outPutText += examinationLabels[i].getText();
                outPutText += examinationFields[i].getText() + "\n";
            }
            outPutText += "\n";
        }

        outPutText += "Examinator har möjlighet att besluta om anpassad eller alternativ examination " +
                "för studenter med funktionsnedsättning.\n\n";

        if (homeExamCheckBox.isSelected()) {
            if (homeExamRadio1.isSelected()) {
                outPutText += "Hemexaminationsuppgiften kommer inte examineras vid försenad inlämning, " +
                        "dock ska examinator beakta särskilda skäl.\n\n";
            } else if (homeExamRadio2.isSelected()) {
                outPutText += "Sen inlämning av det självständiga arbetet/hemexaminationsuppgift " +
                        "har konsekvenser för kursens slutbetyg, " +
                        "vilket närmare beskrivs i kursens betygskriterier.\n\n";
            }
        }

        if (examinationOnEnglishCheckBox.isSelected()) {
            if (englishRadio1.isSelected()) {
                outPutText += "Examination sker på engelska.\n\n";
            } else if (englishRadio2.isSelected()) {
                outPutText += "Om undervisningen sker på engelska kan även " +
                        "examination komma att genomföras på engelska.\n\n";
            }
        }
        return outPutText;
    }

    /**
     * Returns text to be printed in panel b.
     * @return
     */
    private String bPrintOut() {
        String outPutText = "b. ";
        if (hasAttendanceCheckBox.isSelected()) {
            if (!isDistance) {
                outPutText += "För godkänt slutbetyg krävs deltagande i ";
                outPutText += notDistanceAttendancePane.getText() + ". ";
                outPutText += "Om särskilda skäl föreligger kan examinator efter samråd " +
                        "med vederbörande lärare medge den studerande befrielse från skyldigheten " +
                        "att delta i viss obligatorisk undervisning.\n\n";
            } else {
                outPutText += distanceAttendancePane.getText() + "\n\n";
            }
        } else {
            if (!isDistance) {
                outPutText += "Kursen har ingen obligatorisk undervisning.\n\n";
            } else {
                outPutText += "Kursen innehåller inga moment som kräver närvaro på campus.\n\n";
            }
        }
        return outPutText;
    }

    /**
     * Returns text to be printed in panel c.
     * @return
     */
    private String cPrintOut() {
        String outPutText = "c. Betygsättning: Kursens slutbetyg sätts enligt ";
        outPutText += courseGradingScaleComboBox.getSelectedItem() + "\n";
        ArrayList<String> grades = gradingScale.userGetGradingScale(
                (String) Objects.requireNonNull(courseGradingScaleComboBox.getSelectedItem()));
        for (String grade : grades) {
            outPutText += grade + "\n";
        }
        outPutText += "\n";

        for (int i = 0; i < nParts; i++) {
            outPutText += "Betygsättning av " + courseParts.get(i).getName() + " sker enligt " +
                    gradingScaleComboBoxes.get(i).getSelectedItem() + ".\n";
            outPutText += i == nParts - 1 ? "\n" : "";
        }

        if (hasParts) {
            outPutText += "För godkänt slutbetyg krävs godkänt betyg på samtliga ingående delar.\n\n";
        }

        if (totalGradeRadio1.isSelected()) {
            outPutText += "Kursens slutbetyg sätts genom en sammanvägning av betygen på kursens delar, " +
                    "där de olika delarnas betyg viktas i förhållande till deras omfattning.\n\n";
        } else if (totalGradeRadio2.isSelected()) {
            outPutText += "Kursens slutbetyg sätts utifrån betygsättning på ";
            int totalCount = 0;
            for (JRadioButton radio : gradeCertainPartsRadios) if(radio.isSelected()) totalCount++;
            int count = 0;
            for (int i = 0; i < nParts; i++) {
                if (gradeCertainPartsRadios[i].isSelected()) {
                    outPutText += count > 0 && count < totalCount - 1 ? ", " : "";
                    outPutText += count > 0 && count == totalCount - 1 ? " och " : "";
                    outPutText += courseParts.get(i).getName();
                    count++;
                }
            }
            if (count > 1) {
                outPutText += " där de olika delarnas betyg viktas i förhållande till deras omfattning.";
            }
            outPutText += "\n\n";
        } else if (totalGradeRadio3.isSelected()) {
            outPutText += totalGradeAlt3TextPane.getText() + "\n\n";
        }

        if (otherActivitiesCheckBox.isSelected()) {
            outPutText += otherActivitiesPane.getText() + "\n\n";
        }

        return outPutText;
    }

    /**
     * Returns text to be printed in panel d.
     * @return
     */
    private String dPrintOut() {
        String outPutText = "d. Kursens betygskriterier delas ut vid kursstart.\n\n";

        if (thesis) {
            outPutText += "Grundläggande bedömningsgrunder är: \n" +
                    "1. Förståelse av den förelagda uppgiften \n" +
                    "2. Genomförande av experimenten/fältarbeten/den teoretiska uppgiften \n" +
                    "3. Kunskap om den teoretiska bakgrunden \n" +
                    "4. Tolkning och analys av resultat\n" +
                    "5. Självständighet\n" +
                    "6. Förmåga att hålla den fastställda tidsplanen för arbetet \n" +
                    "7. Presentation  muntlig redovisning \n" +
                    "8. Presentation  skriftlig redovisning \n" +
                    "9. Övrigt\n\n";
        }
        return outPutText;
    }

    /**
     * Returns text to be printed in panel e.
     * @return
     */
    private String ePrintOut() {
        return "e. Studerande som underkänts i ordinarie prov har rätt att genomgå " +
                "ytterligare prov så länge kursen ges. Antalet provtillfällen är inte begränsat. " +
                "Med prov jämställs också andra obligatoriska kursdelar. Studerande som godkänts på " +
                "prov får inte genomgå förnyat prov för högre betyg. En student, som utan godkänt " +
                "resultat har genomgått två prov för en kurs eller en del av en kurs, har rätt att få " +
                "en annan examinator utsedd, om inte särskilda skäl talar mot det. Framställan härom ska " +
                "göras till institutionsstyrelsen. Kursen har minst tre examinationstillfällen " +
                "(vid behov: för varje del) per läsår de år då undervisning ges. För de läsår som " +
                "kursen inte ges erbjuds minst ett examinationstillfälle. \n\n";
    }

    /**
     * Returns text to be printed in panel f.
     * @return
     */
    private String fPrintOut() {
        String outPutText = "f. ";
        if (supplementRadio1.isSelected()) {
            outPutText += "Vid betyget Fx ges möjlighet att komplettera upp till betyget E. " +
                    "Examinator beslutar om vilka kompletteringsuppgifter som ska utföras och vilka " +
                    "kriterier som ska gälla för att bli godkänd på kompletteringen. Kompletteringen " +
                    "ska äga rum före nästa examinationstillfälle.";
        } else if (supplementRadio2.isSelected()) {
            outPutText += "Vid betyget Fx ges möjlighet att komplettera till godkänt betyg. Examinator beslutar" +
                    " om vilka kompletteringsuppgifter som ska utföras och vilka kriterier som ska gälla för " +
                    "att bli godkänd på kompletteringen. Kompletteringen ska äga rum före nästa " +
                    "examinationstillfälle. Vid godkänd komplettering av brister av förståelsekaraktär - " +
                    "mindre missförstånd, smärre felaktigheter eller i någon del alltför begränsade resonemang - " +
                    "används betyget E. Vid godkänd komplettering av enklare formaliafel används betygen A-E. ";
        } else if (supplementRadio3.isSelected()) {
            outPutText += "Vid betyget U ges möjlighet att komplettera upp till betyget G. Examinator beslutar " +
                    "om vilka kompletteringsuppgiftersom ska utföras och vilka kriterier som ska gälla för att bli " +
                    "godkänd på kompletteringen. Kompletteringen ska äga rum före nästa examinationstillfälle.";
        } else if (noSupplementRadio1.isSelected()) {
            outPutText += "Möjlighet till komplettering av betyget Fx upp till godkänt betyg ges inte på denna kurs.";
        } else if (noSupplementRadio2.isSelected()) {
            outPutText += "Möjlighet till komplettering av betyget U upp till godkänt betyg ges inte på denna kurs.";
        }
        return outPutText;
    }

}
