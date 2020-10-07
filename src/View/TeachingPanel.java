package View;

import model.Course;

import javax.swing.*;
import java.util.Properties;

public class TeachingPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JTextArea teachingField;
    private JCheckBox otherThanSwedishCheckBox;
    private JTextField thesisHoursField;
    private JCheckBox canChangeSupervisorCheckBox;
    private JPanel languagePanel;
    private JPanel thesisPanel;
    private JPanel notDistancePanel;
    private JPanel distancePanel;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JTextPane printOutPane;
    private JButton printOutButton;

    boolean isDistance = false;
    boolean thesis = false;

    Properties properties;

    // Constructors

    public TeachingPanel(MainFrame frame) {
        languagePanel.setVisible(false);
        radio1.setSelected(true);
        addActionListeners();
        properties = frame.getProperties();
    }

    public TeachingPanel() {

    }

    private void addActionListeners() {
        otherThanSwedishCheckBox.addActionListener(e -> updateLanguagePanel());
        radio1.addActionListener(e -> radio2.setSelected(false));
        radio2.addActionListener(e -> radio1.setSelected(false));
        printOutButton.addActionListener(e -> printOut());
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
        return properties.getProperty("TeachingTitle");
    }
    public void updateView(Course course) {
        updateCourseAttributes(course);
        setVisibilityOfComponents();
    }

    private void updateCourseAttributes(Course course) {
        isDistance = course.isDistance();
        thesis = course.hasThesis();
    }

    private void setVisibilityOfComponents() {
        distancePanel.setVisible(isDistance);
        notDistancePanel.setVisible(!isDistance);
        thesisPanel.setVisible(thesis);
    }

    // Action listeners methods
    private void updateLanguagePanel() {
        languagePanel.setVisible(otherThanSwedishCheckBox.isSelected());
    }

    // Getters
    public JCheckBox getOtherThanSwedishCheckBox() {
        return otherThanSwedishCheckBox;
    }

    public JRadioButton getRadioButtonCourseInEnglish() {
        return radio1;
    }

    public JRadioButton getRadioButtonLanguageGivenAtStart() {
        return radio2;
    }

    // PrintOut method
    public void printOut() {
        String outPutText = "";
        outPutText += printOutDistance();

        outPutText += printOutEnglish();

        outPutText += printOutThesis();

        printOutPane.setText(outPutText);
    }

    private String printOutThesis() {
        String outPutText = "";
        if (thesis) {
            outPutText += "Studenten har rätt till minst ";
            outPutText += thesisHoursField.getText();
            outPutText += " timmars handledning, där individuell handledning ska " +
                    "utgöra minst en tredjedel av tiden.\n\n";

            outPutText += "Handledning ges endast inom den planerade kurstiden. " +
                    "Vid särskilda omständigheter kan studenten beviljas förlängd " +
                    "handledartid. Begäran om detta ska ställas till institutionsstyrelsen. \n\n";
            if (canChangeSupervisorCheckBox.isSelected()) {
                outPutText += "Vid särskilda omständigheter har studenten rätt att byta handledare. " +
                        "Begäran om detta ska ställas till institutionsstyrelsen. ";
            }
        }
        return outPutText;
    }

    private String printOutEnglish() {
        String outPutText = "";
        if (otherThanSwedishCheckBox.isSelected()) {
            if (radio1.isSelected()) {
                outPutText += "Kursen ges på engelska.\n\n";
            } else if (radio2.isSelected()) {
                outPutText += "Kursens undervisningsspråk anges inför varje kurstillfälle " +
                        "och framgår av den digitala utbildningskatalogen.\n\n";
            }
        }
        return outPutText;
    }

    private String printOutDistance() {
        String outPutText = "";
        if (!isDistance) {
            outPutText += "Undervisningen består av ";
            outPutText += teachingField.getText() + "\n\n";
        } else {
            outPutText += "Undervisningen sker på distans.\n\n";
        }
        return outPutText;
    }
}
