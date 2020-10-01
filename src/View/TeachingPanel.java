package View;

import model.Course;

import javax.swing.*;
import java.awt.*;

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

    // Constructors

    private TeachingPanel() {
        languagePanel.setVisible(false);
        otherThanSwedishCheckBox.addActionListener(e -> updateLanguagePanel());
        radio1.addActionListener(e -> updateRadios(radio2));
        radio2.addActionListener(e -> updateRadios(radio1));
        printOutButton.addActionListener(e -> printOut());
    }
    private static final TeachingPanel INSTANCE = new TeachingPanel();
    public static TeachingPanel getInstance() {return INSTANCE;}

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
        isDistance = course.isDistance();
        distancePanel.setVisible(isDistance);
        notDistancePanel.setVisible(!isDistance);
        thesis = course.hasThesis();
        thesisPanel.setVisible(thesis);
    }

    // Action listeners methods
    private void updateLanguagePanel() {
        languagePanel.setVisible(otherThanSwedishCheckBox.isSelected());
        radio1.setSelected(true);
    }

    private void updateRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    // PrintOut method
    public void printOut() {
        String outPutText = "";
        if (!isDistance) {
            outPutText += "Undervisningen består av ";
            outPutText += teachingField.getText() + "\n\n";
        } else {
            outPutText += "Undervisningen sker på distans.\n";
        }

        if (otherThanSwedishCheckBox.isSelected()) {
            if (radio1.isSelected()) {
                outPutText += "Kursen ges på engelska.\n\n";
            } else if (radio2.isSelected()) {
                outPutText += "Kursens undervisningsspråk anges inför varje kurstillfälle " +
                        "och framgår av den digitala utbildningskatalogen.\n\n";
            }
        }
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

        printOutPane.setText(outPutText);
    }
}
