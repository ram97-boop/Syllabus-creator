package View;

import model.Course;
import model.CoursePart;
import model.GradingScale;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.IntStream;

// TODO Sätt ihop delar i printOut som examineras på samma sätt
// TODO Sätt ihop delar i printOut som har samma betygskala
// TODO Slytbetyg sätts på annat sätt i printOut
// TODO Problem med vad som krävs på campus

public class ExaminationPanel implements CoursePanel {
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
    private JTextPane dPane;
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

    Properties properties;

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

    private ArrayList<CoursePart> courseParts;
    private int nParts = 0;
    private boolean hasParts = false;
    private boolean isDistance = false;
    private final GradingScale gradingScale = new GradingScale();
    private boolean thesis = false;

    // Constructors
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

        homeExamCheckBox.setSelected(course.hasHomeExam());
        if (homeExamCheckBox.isSelected()) {
            homeExamRadio1.setSelected(course.isLateHomeExamNotExamined());
            homeExamRadio2.setSelected(!course.isLateHomeExamNotExamined());
        }

        examinationOnEnglishCheckBox.setSelected(course.isExaminationPartiallyInEnglish());
        if (examinationOnEnglishCheckBox.isSelected()) {
            englishRadio1.setSelected(course.isExaminationInEnglish());
            englishRadio2.setSelected(!course.isExaminationInEnglish());
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
            }
        });
    }

    private void setUpComponents() {
        supplementCheckBox.setSelected(true);
        supplementRadio1.setSelected(true);
        gradeCertainPartsPanel.setVisible(false);
    }

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

    // Interface methods
    public JPanel getPanel() {
        return mainPanel;
    }
    public JButton getNextPanelButton() {
        return nextPanelButton;
    }
    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }
    public String getFrameName() {
        return properties.getProperty("ExaminationTitle");
    }
    public void updateView(Course course) {
        updateCourseAttributes(course);
        setVisibilityOfComponents();
        setLabelNames();
        if (!course.getCourseParts().isEmpty()) {
            setExaminationFieldsForCourseParts(course);
            setGradingScalesForCourseParts(course);
        }
    }

    private void updateCourseAttributes(Course course) {
        courseParts = course.getCourseParts();
        nParts = courseParts.size();
        hasParts = nParts > 0;
        isDistance = course.isDistance();
        thesis = course.hasThesis();
    }

    private void setVisibilityOfComponents() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
        distanceAttendancePanel.setVisible(isDistance);
        notDistanceAttendancePanel.setVisible(!isDistance);
        partsExaminationPanel.setVisible(hasParts);
        noPartsExaminationPanel.setVisible(!hasParts);
        gradingPanel.setVisible(hasParts);
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

    private void setLabelNames() {
        for (int i = 0; i < nParts; i++) {
            String partName = courseParts.get(i).getName();
            String examinationLabelText = "Kunskapskontroll för " + partName + " sker genom ";
            examinationLabels[i].setText(examinationLabelText);
            gradingScalePartLabels[i].setText(partName);
            gradeCertainPartsRadios[i].setText(partName);
        }
    }

    private void setToolTips() {
        ePane.setToolTipText(properties.getProperty("examinationEPaneToolTip"));
        supplementRadio1.setToolTipText(properties.getProperty("examinationFPaneAlt1ToolTip"));
        supplementRadio2.setToolTipText(properties.getProperty("examinationFPaneAlt2ToolTip"));
        supplementRadio3.setToolTipText(properties.getProperty("examinationFPaneAlt3ToolTip"));
        noSupplementRadio1.setToolTipText(properties.getProperty("examinationFPaneAlt4ToolTip"));
        noSupplementRadio2.setToolTipText(properties.getProperty("examinationFPaneAlt5ToolTip"));
    }

    // Action listeners methods
    private void updateHomeExamPanel() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        homeExamRadio1.setSelected(true);
        homeExamRadio2.setSelected(false);
    }

    private void updateHomeExamRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    private void updateEnglishExaminationPanel() {
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        englishRadio1.setSelected(true);
        englishRadio2.setSelected(false);
    }

    private void updateEnglishRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    private void updateAttendancePanel() {
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
    }

    private void updateTotalGradeRadios(JRadioButton radio1, JRadioButton radio2) {
        radio1.setSelected(false);
        radio2.setSelected(false);
        gradeCertainPartsPanel.setVisible(totalGradeRadio2.isSelected());
    }

    private void updateOtherActivitiesGradePanel() {
        otherActivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
    }

    private void updateSupplementRadios() {
        supplementRadio1.setVisible(supplementCheckBox.isSelected());
        supplementRadio2.setVisible(supplementCheckBox.isSelected());
        supplementRadio3.setVisible(supplementCheckBox.isSelected());
        noSupplementRadio1.setVisible(!supplementCheckBox.isSelected());
        noSupplementRadio2.setVisible(!supplementCheckBox.isSelected());
    }

    // Getters to Controller

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

    // PrintOut methods

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

    private String aPrintOut() {
        String outPutText = "a. Kursen examineras på följande vis: \n";

        if (!hasParts) {
            outPutText += examinationPane.getText() + "\n\n";
        } else {
            for (int i = 0; i < nParts; i++) {
                outPutText += examinationLabels[i].getText();
                outPutText += examinationFields[i].getText() + "\n";
            }
        }

        outPutText += "\nExaminator har möjlighet att besluta om anpassad eller alternativ examination " +
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

    private String bPrintOut() {
        String outPutText = "b. ";
        if (hasAttendanceCheckBox.isSelected()) {
            if (!isDistance) {
                outPutText += "För godkänt slutbetyg krävs deltagande i ";
                outPutText += notDistanceAttendancePane.getText() + ".";
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
        }

        outPutText += "\nFör godkänt slutbetyg krävs godkänt betyg på samtliga ingående delar. ";

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
