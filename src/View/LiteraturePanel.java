package View;

import model.Course;

import javax.swing.*;
import java.util.Properties;

public class LiteraturePanel implements CoursePanel {
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

    private boolean thesis = false;

    Properties properties;

    // Constructors

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

    private void addActionListeners() {
        multipleInstitutionsCheckBox.addActionListener(e -> updateRadios());
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

    public JButton getSaveButton() {return saveButton;}

    public String getFrameName() {
        return properties.getProperty("LiteratureTitle");
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public void updateView(Course course) {
        thesis = course.hasThesis();
        updateRadios();
    }

    private void setToolTips() {
        radio1.setToolTipText(properties.getProperty("literaturePanelAlt1ToolTip"));
        radio2.setToolTipText(properties.getProperty("literaturePanelAlt2ToolTip"));
    }

    // Action listener methods

    private void updateRadios() {
        radio1.setVisible(multipleInstitutionsCheckBox.isSelected());
        radio2.setVisible(multipleInstitutionsCheckBox.isSelected());
    }

    // Getters

    public JTextField getInstitutionField() {
        return institutionField;
    }

    public JCheckBox getMultipleInstitutionsCheckBox() {
        return multipleInstitutionsCheckBox;
    }

    public JRadioButton getRadio1() {
        return radio1;
    }

    // PrintOut methods

    public void printOut() {
        String outPutText = "";

        outPutText += printOutMultipleInstitutions();

        outPutText += printOutThesis();

        printOutPane.setText(outPutText);
    }

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
