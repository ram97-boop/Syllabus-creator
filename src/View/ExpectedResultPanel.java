package View;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ExpectedResultPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JCheckBox isConnectedToAll;
    private JPanel partsLabels;
    private JPanel partsRadios1;
    private JPanel partsRadios2;
    private JPanel partsRadios3;
    private JPanel partsRadios4;
    private JPanel partsRadios5;
    private JPanel partsRadios6;
    private JPanel partsRadios7;
    private JPanel partsRadios8;
    private JPanel goalsWithParts;
    private JPanel goalPanel1;
    private JPanel goalPanel2;
    private JPanel goalPanel3;
    private JPanel goalPanel4;
    private JPanel goalPanel5;
    private JPanel goalPanel6;
    private JPanel goalPanel7;
    private JPanel goalPanel8;
    private final JPanel[] radioPanels = {
            partsRadios1,
            partsRadios2,
            partsRadios3,
            partsRadios4,
            partsRadios5,
            partsRadios6,
            partsRadios7,
            partsRadios8
    };

    //Constructors
    private ExpectedResultPanel() {
        isConnectedToAll.addActionListener(e -> updateRadioButtons());
    }
    private static final ExpectedResultPanel INSTANCE = new ExpectedResultPanel();
    public static ExpectedResultPanel getInstance() {return INSTANCE;}

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
    public void updateView(Course course) {
        int i = 0;
        for (Component component : partsLabels.getComponents()) {
            component.setVisible(i < 3);
            i++;
        }
        for (JPanel radioPanel : radioPanels) {
            i = 0;
            for (Component component : radioPanel.getComponents()) {
                component.setVisible(i < 3);
                i++;
            }
        }
    }

    // Action listener methods
    private void updateRadioButtons() {
        int i = 0;
        for (Component component : partsLabels.getComponents()) {
            component.setVisible(!isConnectedToAll.isSelected() && i < 3);
            i++;
        }
        for (JPanel radioPanel : radioPanels) {
            i = 0;
            for (Component component : radioPanel.getComponents()) {
                component.setVisible(!isConnectedToAll.isSelected() && i < 3);
                i++;
            }
        }
    }

    // Getters to Controller
    public JPanel[] getPartPanels() {
        return new JPanel[]{
                goalPanel1,
                goalPanel2,
                goalPanel3,
                goalPanel4,
                goalPanel5,
                goalPanel6,
                goalPanel7,
                goalPanel8
        };
    }


    // Print out
    public void printOut(Course course) {

    }
}
