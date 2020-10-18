package View;

import model.Course;
import model.CoursePart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.IntStream;


/**
 * CourseContentPanel
 * Implements CoursePanel
 *
 * Handles all components in the JPanel component mainPanel
 * used when the user is entering content of the course.
 *
 * @author Mikael Stener
 */

public class CourseContentPanel implements CoursePanel {
    /**
     * Swing components
     */
    private MainFrame frame;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JTextPane courseContentTextPane;
    private JComboBox<Integer> nPartsComboBox;
    private JTextField part1S;
    private JTextField part2S;
    private JTextField part3S;
    private JTextField part4S;
    private JTextField part5S;
    private JTextField part6S;
    private JTextField part1E;
    private JTextField part2E;
    private JTextField part3E;
    private JTextField part4E;
    private JTextField part5E;
    private JTextField part6E;
    private JTextField credits1;
    private JTextField credits2;
    private JTextField credits3;
    private JTextField credits4;
    private JTextField credits5;
    private JTextField credits6;
    private JTextPane printOutPane;
    private JButton printOutButton;
    private JPanel partsPanel;
    private JLabel courseContentLabel;
    private JLabel creditsLabel;
    private JSplitPane splitPane;
    private JButton saveButton;

    /**
     * Collections of swing components
     */
    private final JTextField[][] partFields = {
            {part1S, part1E, credits1},
            {part2S, part2E, credits2},
            {part3S, part3E, credits3},
            {part4S, part4E, credits4},
            {part5S, part5E, credits5},
            {part6S, part6E, credits6},
    };

    /**
     * Course attributes
     */
    private int nParts = 0;

    /**
     * MainFrame attributes
     */
    Properties properties;

    /**
     * Constructors
     */

    public CourseContentPanel(MainFrame frame) {
        setUpComponents();
        setUpComboBox();
        addActionListeners();
        this.frame = frame;
        properties = frame.getProperties();
        setToolTips();
    }

    public CourseContentPanel(MainFrame frame, Course course) {
        setUpComponents();
        setUpComboBox();
        addActionListeners();
        this.frame = frame;
        properties = frame.getProperties();
        setToolTips();

        nPartsComboBox.setSelectedItem(course.getCourseParts().size());
        updatePartFields();

        if (course.getCourseContentText() != null) {
            courseContentTextPane.setText(course.getCourseContentText());
        }

        IntStream.range(0, course.getCourseParts().size()).forEach(index -> {
            JTextField[] row = partFields[index];
            ArrayList<CoursePart> courseParts = course.getCourseParts();
            row[0].setText(courseParts.get(index).getName());
            row[1].setText(courseParts.get(index).getEngName());
            row[2].setText(String.valueOf(courseParts.get(index).getCredits()));
        });
    }

    public CourseContentPanel() {
        setUpComboBox();
        addActionListeners();
    }

    /**
     * Helper methods to constructors
     */

    /**
     * Set initial states of components.
     */
    private void setUpComponents() {
        nPartsComboBox.setEditable(false);
        partsPanel.setVisible(false);
    }

    /**
     * Add items to parts ComboBox.
     */
    private void setUpComboBox() {
        int[] possibleNParts = IntStream.range(0, 6).toArray();
        for (int possibleNPart : possibleNParts) {
            nPartsComboBox.addItem(possibleNPart);
        }
    }

    /**
     * Add action listeners to components.
     */
    private void addActionListeners() {
        nPartsComboBox.addActionListener(e -> updatePartFields());
        printOutButton.addActionListener(e -> printOut());
    }

    /**
     * Setting tool tips (help text popups).
     */
    private void setToolTips() {
        courseContentLabel.setToolTipText("");
        creditsLabel.setToolTipText("Använd punkt som kommatecken.");
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
        return properties.getProperty("CourseContentTitle");
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

    }

    /**
     * Action listener methods
     */

    /**
     * Adding or removing part fields based on number of parts the user entered.
     */
    private void updatePartFields() {
        Object nPartsObject;
        if ((nPartsObject = nPartsComboBox.getSelectedItem()) != null) {
            nParts = (int) nPartsObject;
        }
        partsPanel.setVisible(nParts > 0);
        for (int i = 0; i < partFields.length; i++) {
            for (JTextField field : partFields[i]) {
                field.setVisible(i < nParts);
            }
        }
        if (frame != null) {
            frame.keepSize(splitPane);
        }
    }

    /**
     * Getters
     * Methods used by controller and in tests.
     */

    public JTextPane getCourseContentTextPane() {
        return courseContentTextPane;
    }

    public JComboBox<Integer> getnPartsComboBox() {
        return nPartsComboBox;
    }

    public JTextField[][] getPartFields() {
        return partFields;
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
        String outPutText = "a. Kursen behandlar: ";
        outPutText += courseContentTextPane.getText() + "\n\n";
        outPutText += nParts > 0 ? "b. Kursen består av följande moment: \n" : "";
        for (int i = 0; i < nParts; i++) {
            if (partFields[i][0].isVisible()) {
                String name = partFields[i][0].getText();
                String ename = partFields[i][1].getText();
                String credit = partFields[i][2].getText();

                outPutText += "Del " + (i + 1) + ", " + name + " (" + ename + "), " +
                        credit.replace('.', ',') + " hp\n";
            }
        }

        printOutPane.setText(outPutText);
    }

}
