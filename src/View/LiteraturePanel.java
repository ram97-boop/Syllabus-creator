package View;

import model.Course;

import javax.swing.*;
import java.util.Properties;

/**
 * LiteraturePanel
 * Implements CoursePanel
 *
 * Handles all components in the JPanel component mainPanel
 * used when the user is entering literature attributes
 * of the course.
 *
 * @author Mikael Stener, Sofia Karbin (see annotations)
 */

public class LiteraturePanel implements CoursePanel {
    /**
     * Swing components
     */
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JButton printOutButton;
    private JTextPane printOutPane;
    private JCheckBox multipleInstitutionsCheckBox;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JTextField institutionField;
    private JSplitPane splitPane;
    private JButton saveButton;

    /**
     * Course attributes
     */
    private boolean thesis = false;

    /**
     * MainFrame attributes
     */
    Properties properties;

    /**
     * Constructors
     */

    public LiteraturePanel(MainFrame frame) {
        nextPanelButton.setEnabled(false);
        radio1.setSelected(true);
        addActionListeners();
        properties = frame.getProperties();
        setToolTips();
    }

    public LiteraturePanel(MainFrame frame, Course course) {
        nextPanelButton.setEnabled(false);
        addActionListeners();
        properties = frame.getProperties();
        setToolTips();

        if (course.getInstitution() != null) {
            institutionField.setText(course.getInstitution());
        }

        multipleInstitutionsCheckBox.setSelected(course.hasMultipleInstitutions());
        updateRadios();

        if (course.hasMultipleInstitutions()) {
            radio1.setSelected(course.getPrintMultipleInstitutionsAlt1());
            radio2.setSelected(!course.getPrintMultipleInstitutionsAlt1());
        } else {
            radio1.setSelected(true);
        }

    }

    public LiteraturePanel() {

    }

    /**
     * Helper methods to constructors
     */

    /**
     * Adds action listeners to swing components.
     */
    private void addActionListeners() {
        multipleInstitutionsCheckBox.addActionListener(e -> updateRadios());
        radio1.addActionListener(e -> radio2.setSelected(false));
        radio2.addActionListener(e -> radio1.setSelected(false));
        printOutButton.addActionListener(e -> printOut());
    }

    /**
     * Setting tool tips (help text popups).
     */
    private void setToolTips() {
        radio1.setToolTipText(properties.getProperty("literaturePanelAlt1ToolTip"));
        radio2.setToolTipText(properties.getProperty("literaturePanelAlt2ToolTip"));
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
        return properties.getProperty("LiteratureTitle");
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
        thesis = course.hasThesis();
        updateRadios();
    }

    /**
     * Action listener methods
     */

    /**
     * Shows multiple institutions radio buttons based on if there are multiple institutions.
     */
    private void updateRadios() {
        radio1.setVisible(multipleInstitutionsCheckBox.isSelected());
        radio2.setVisible(multipleInstitutionsCheckBox.isSelected());
    }

    /**
     * Getters
     */

    public JTextField getInstitutionField() {
        return institutionField;
    }

    public JCheckBox getMultipleInstitutionsCheckBox() {
        return multipleInstitutionsCheckBox;
    }

    public JRadioButton getRadio1() {
        return radio1;
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

        outPutText += printOutMultipleInstitutions();

        outPutText += printOutThesis();

        printOutPane.setText(outPutText);
    }

    /**
     * Returs text to be printed if there are multiple institutions.
     * @return
     */
    private String printOutMultipleInstitutions() {
        String outPutText = "";
        if (!multipleInstitutionsCheckBox.isSelected()) {
            outPutText += "Kurslitteratur beslutas av institutionsstyrelsen och publiceras på ";
            outPutText += institutionField.getText();
            outPutText += "s webbplats senast 2 månader före kursstart.\n\n";
        } else {
            if (radio1.isSelected()) {
                outPutText += "Kurslitteratur beslutas av institutionsstyrelse där kursen är inrättad " +
                        "och publiceras på ";
                outPutText += institutionField.getText();
                outPutText += "s webbplats senast 2 månader före kursstart.\n\n";
            } else if (radio2.isSelected()) {
                outPutText += "Kurslitteratur beslutas av respektive ansvarig institutionsstyrelse " +
                        "och publiceras på ";
                outPutText += institutionField.getText();
                outPutText += "s webbplats senast 2 månader före kursstart.\n\n";
            }
        }
        return outPutText;
    }

    /**
     * Returns text to be printed if the course contains a thesis.
     * @return
     */
    private String printOutThesis() {
        String outPutText = "";
        if (thesis) {
            outPutText += "Litteraturen baseras på vetenskapliga publikationer och rapporter " +
                    "inom det aktuella området framtagna av den studerande genom litteratursökning samt " +
                    "litteratur utdelad av huvudhandledaren och/eller av den biträdande handledaren. ";
        }
        return outPutText;
    }
}
