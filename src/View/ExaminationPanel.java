package View;

import model.Course;
import model.CoursePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JPanel gradingPanel;
    private JPanel partsGradingScalePanel;
    private JLabel partLabel1;
    private JLabel partLabel2;
    private JLabel partLabel3;
    private JLabel partLabel4;
    private JLabel partLabel5;
    private JLabel partLabel6;
    private JPanel partPanel1;
    private JPanel partPanel2;
    private JPanel partPanel3;
    private JPanel partPanel4;
    private JPanel partPanel5;
    private JPanel partPanel6;
    private JComboBox<String> gradingScale1;
    private JComboBox<String> gradingScale2;
    private JComboBox<String> gradingScale3;
    private JComboBox<String> gradingScale4;
    private JComboBox<String> gradingScale5;
    private JComboBox<String> gradingScale6;

    private JTextArea examinationField;
    private JRadioButton homeExamRadio1;
    private JRadioButton homeExamRadio2;
    private JRadioButton englishRadio1;
    private JRadioButton englishRadio2;
    private JRadioButton totalGradeRadio1;
    private JRadioButton totalGradeRadio2;
    private JRadioButton totalGradeRadio3;
    private JCheckBox otherActivitiesCheckBox;
    private JTextArea textArea2;
    private JPanel otherAcitivitiesGradePanel;
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
    private JTextArea notDistanceAttendanceField;
    private JTextArea distanceAttendanceField;

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


    private final JPanel[] partPanels = {
            partPanel1,
            partPanel2,
            partPanel3,
            partPanel4,
            partPanel5,
            partPanel6,
    };

    private final String[] gradingScaleStrings = {
            "7-gradig (A-F)",
            "3-gradig (VG-U)",
            "2-gradig (G-U)"
    };

    ArrayList<CoursePart> courseParts;
    int nParts = 0;
    boolean hasParts = false;
    boolean isDistance = false;
    ArrayList<String> gradingScale;
    boolean thesis = false;

    // Constructors
    private ExaminationPanel() {
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
        printOutButton.addActionListener(e -> printOut());

        for (String gradingScaleString : gradingScaleStrings) {
            gradingScale1.addItem(gradingScaleString);
            gradingScale2.addItem(gradingScaleString);
            gradingScale3.addItem(gradingScaleString);
            gradingScale4.addItem(gradingScaleString);
            gradingScale5.addItem(gradingScaleString);
            gradingScale6.addItem(gradingScaleString);
        }
    }
    private static final ExaminationPanel INSTANCE = new ExaminationPanel();
    public static ExaminationPanel getInstance() {return INSTANCE;}

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
    public void updateView(MainFrame frame, Course course) {
        homeExamPanel.setVisible(false);
        englishExaminationPanel.setVisible(false);

        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
        distanceAttendancePanel.setVisible(course.isDistance());
        notDistanceAttendancePanel.setVisible(!course.isDistance());



        courseParts = course.getCourseParts();
        nParts = courseParts.size();
        hasParts = nParts > 0;
        isDistance = course.isDistance();
        gradingScale = course.getGradingScale();
        thesis = course.hasThesis();

        partsExaminationPanel.setVisible(hasParts);
        noPartsExaminationPanel.setVisible(!hasParts);
        partsGradingScalePanel.setVisible(hasParts);
        if(hasParts) {
            int i = 0;
            for (JLabel examinationLabel : examinationLabels) {
                examinationLabel.setVisible(i < nParts);
                if (i < nParts) {
                    String labelText = "Kunskapskontroll för ";
                    labelText += courseParts.get(i).getName();
                    labelText += " sker genom: ";
                    examinationLabel.setText(labelText);
                }
                i++;
            }
            i = 0;
            for (JTextField examinationField : examinationFields) {
                examinationField.setVisible(i < nParts);
                i++;
            }
            i = 0;
            for (JPanel partPanel : partPanels) {
                partPanel.setVisible(i < nParts);
                if (i < nParts) {
                    JLabel label = (JLabel) partPanel.getComponent(0);
                    label.setText(courseParts.get(i).getName());
                }
                i++;
            }
        }
        totalGradeRadio1.setSelected(true);
        otherAcitivitiesGradePanel.setVisible(false);
    }

    // Action listeners methods
    private void updateHomeExamPanel() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        homeExamRadio1.setSelected(true);
    }

    private void updateHomeExamRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    private void updateEnglishExaminationPanel() {
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        englishRadio1.setSelected(true);
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
    }

    private void updateOtherActivitiesGradePanel() {
        otherAcitivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
    }

    // PrintOut method

    public void printOut() {
        String outPutText = "Kursen examineras på följande vis: \n";

        if (!hasParts) {
            outPutText += examinationField.getText() + "\n\n";
        } else {
            for (int i = 0; i < nParts; i++) {
                outPutText += examinationLabels[i].getText() + " ";
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

        outPutText += "b. ";
        if (hasAttendanceCheckBox.isSelected()) {
            if (!isDistance) {
                outPutText += "För godkänt slutbetyg krävs deltagande i ";
                outPutText += notDistanceAttendanceField.getText() + ".";
                outPutText += "Om särskilda skäl föreligger kan examinator efter samråd " +
                        "med vederbörande lärare medge den studerande befrielse från skyldigheten " +
                        "att delta i viss obligatorisk undervisning.\n\n";
            } else {
                outPutText += "Kursen innehåller ";
                outPutText += distanceAttendanceField.getText() + ".";
                outPutText += "som kräver närvaro på campus.\n\n";
            }
        } else {
            if (!isDistance) {
                outPutText += "Kursen har ingen obligatorisk undervisning.\n\n";
            } else {
                outPutText += "Kursen innehåller inga moment som kräver närvaro på campus.\n\n";
            }
        }

        outPutText += "c. Betygsättning: Kursens slutbetyg sätts enligt\n\n";
        for (String grade : gradingScale) {
            outPutText += grade + "\n";
        }

        // Betygskala för varje del

        outPutText += "\nFör godkänt slutbetyg krävs godkänt betyg på samtliga ingående delar.\n\n";

        // Hur slutbetyget sätts

        // Andra aktiviteter som påverkar betyget

        outPutText += "d. Kursens betygskriterier delas ut vid kursstart.\n\n";

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
                    "9. Övrigt\n";
        }

        outPutText += "\ne. Studerande som underkänts i ordinarie prov har rätt att genomgå " +
                "ytterligare prov så länge kursen ges. Antalet provtillfällen är inte begränsat. " +
                "Med prov jämställs också andra obligatoriska kursdelar. Studerande som godkänts på " +
                "prov får inte genomgå förnyat prov för högre betyg. En student, som utan godkänt " +
                "resultat har genomgått två prov för en kurs eller en del av en kurs, har rätt att få " +
                "en annan examinator utsedd, om inte särskilda skäl talar mot det. Framställan härom ska " +
                "göras till institutionsstyrelsen. Kursen har minst tre examinationstillfällen " +
                "(vid behov: för varje del) per läsår de år då undervisning ges. För de läsår som " +
                "kursen inte ges erbjuds minst ett examinationstillfälle. ";

        // Möjlighet till komplettering

        printOutPane.setText(outPutText);
    }

}
