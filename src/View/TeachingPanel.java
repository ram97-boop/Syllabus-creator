package View;

import controller.Language;
import model.Course;

import javax.swing.*;
import java.util.Properties;

/**
 * TeachingPanel
 * Implements CoursePanel
 *
 * Handles all components in the JPanel component mainPanel
 * used when the user is entering teaching methods
 * of the course.
 *
 * @author Mikael Stener, Sofia Karbin (see annotations)
 */

public class TeachingPanel implements CoursePanel {
    /**
     * Swing components
     */
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JCheckBox otherThanSwedishCheckBox;
    private JTextField thesisSupervisedHoursField;
    private JCheckBox canChangeSupervisorCheckBox;
    private JPanel languagePanel;
    private JPanel thesisPanel;
    private JPanel notDistancePanel;
    private JPanel distancePanel;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JTextPane printOutPane;
    private JButton printOutButton;
    private JTextPane teachingPane;
    private JSplitPane splitPane;
    private JButton saveButton;

    /**
     * Course attributes
     */
    boolean isDistance = false;
    boolean thesis = false;

    /**
     * MainFrame attributes
     */
    Properties properties;

    /**
     * Constructors
     */

    public TeachingPanel(MainFrame frame) {
        languagePanel.setVisible(false);
        radio1.setSelected(true);
        addActionListeners();
        properties = frame.getProperties();
    }

    public TeachingPanel(MainFrame frame, Course course) {
        languagePanel.setVisible(false);
        radio1.setSelected(true);
        addActionListeners();
        properties = frame.getProperties();

        if (course.getTeaching() != null) {
            teachingPane.setText(course.getTeaching());
        }

        if (course.getThesisSupervisedHours() !=null) {
            thesisSupervisedHoursField.setText(course.getThesisSupervisedHours());
        }

        canChangeSupervisorCheckBox.setSelected(course.getCanChangeSupervisor());

        String language = course.getLanguage();

        if (language == null || language.equals(Language.SWEDISH.getLanguage())) {
            otherThanSwedishCheckBox.setSelected(false);
            radio2.setSelected(false);
        } else if (language.equals(Language.ENGLISH.getLanguage())) {
            otherThanSwedishCheckBox.setSelected(true);
            languagePanel.setVisible(true);
            radio1.setSelected(true);
            radio2.setSelected(false);
        } else if (language.equals(Language.NOT_SPECIFIED.getLanguage())) {
            otherThanSwedishCheckBox.setSelected(true);
            languagePanel.setVisible(true);
            radio1.setSelected(false);
            radio2.setSelected(true);
        }

    }

    public TeachingPanel() {

    }

    /**
     * Helper methods for constructors
     */

    /**
     * Add actions listeners to components.
     */
    private void addActionListeners() {
        otherThanSwedishCheckBox.addActionListener(e -> updateLanguagePanel());
        radio1.addActionListener(e -> radio2.setSelected(false));
        radio2.addActionListener(e -> radio1.setSelected(false));
        printOutButton.addActionListener(e -> printOut());
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
        return properties.getProperty("TeachingTitle");
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
    }

    /**
     * Helper methods for updateView
     */

    /**
     * Updates course attributes based on previously entered attributes of course.
     * @param course
     */
    private void updateCourseAttributes(Course course) {
        isDistance = course.isDistance();
        thesis = course.hasThesis();
    }

    /**
     * Updates swing components based on previously entered attributes of course.
     */
    private void updateComponents() {
        distancePanel.setVisible(isDistance);
        notDistancePanel.setVisible(!isDistance);
        thesisPanel.setVisible(thesis);
    }

    /**
     * Action listener methods
     */

    /**
     * Showing language options depending on user choosing other language than swedish.
     */
    private void updateLanguagePanel() {
        languagePanel.setVisible(otherThanSwedishCheckBox.isSelected());
    }

    /**
     * Getters
     */
    public JTextPane getTeachingPane() {
        return teachingPane;
    }

    public JCheckBox getOtherThanSwedishCheckBox() {
        return otherThanSwedishCheckBox;
    }

    public JRadioButton getRadioButtonCourseInEnglish() {
        return radio1;
    }

    public JRadioButton getRadioButtonCourseLanguageNotSpecified() {
        return radio2;
    }

    public JTextField getThesisSupervisedHoursField() {
        return thesisSupervisedHoursField;
    }

    public JCheckBox getCanChangeSupervisorCheckBox() {
        return canChangeSupervisorCheckBox;
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
        String outPutText = "";
        outPutText += printOutDistance();

        outPutText += printOutEnglish();

        outPutText += printOutThesis();

        printOutPane.setText(outPutText);
    }

    /**
     * Returns text to be printed if course contains a thesis.
     * @return
     */
    private String printOutThesis() {
        String outPutText = "";
        if (thesis) {
            outPutText += "Studenten har rätt till minst ";
            outPutText += thesisSupervisedHoursField.getText();
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

    /**
     * Returns text to be printed if teaching language might be other than Swedish.
     * @return
     */
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

    /**
     * Returns text to be printed if the course is a distance course.
     * @return
     */
    private String printOutDistance() {
        String outPutText = "";
        if (!isDistance) {
            outPutText += "Undervisningen består av ";
            outPutText += teachingPane.getText() + "\n\n";
        } else {
            outPutText += "Undervisningen sker på distans.\n\n";
        }
        return outPutText;
    }
}
