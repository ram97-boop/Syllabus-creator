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

    private boolean thesis = false;

    Properties properties;

    // Constructors

    private LiteraturePanel() {
        nextPanelButton.setEnabled(false);
        radio1.setSelected(true);
        addActionListeners();
    }
    private static final LiteraturePanel INSTANCE = new LiteraturePanel();
    public static LiteraturePanel getInstance() {return INSTANCE;}

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

    public String getFrameName() {
        return properties.getProperty("LiteratureTitle");
    }

    public void updateView(MainFrame frame, Course course) {
        thesis = course.hasThesis();
        updateRadios();
        properties = frame.getProperties();
        setToolTips();
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
            outPutText += "Kurslitteratur beslutas av institutionsstyrelsen och publiceras på " +
                    "Institutionen för XXs webbplats senast 2 månader före kursstart.\n\n";
        } else {
            if (radio1.isSelected()) {
                outPutText += "Kurslitteratur beslutas av institutionsstyrelse där kursen är inrättad " +
                        "och publiceras på Institutionen för XXs webbplats senast 2 månader före kursstart.\n\n";
            } else if (radio2.isSelected()) {
                outPutText += "Kurslitteratur beslutas av respektive ansvarig institutionsstyrelse " +
                        "och publiceras på Institutionen för XXs webbplats senast 2 månader före kursstart.\n\n";
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
