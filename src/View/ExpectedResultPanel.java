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
    private JTextField textField2;
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
    private JPanel[] radioPanels = {
            partsRadios1,
            partsRadios2,
            partsRadios3,
            partsRadios4,
            partsRadios5,
            partsRadios6,
            partsRadios7,
            partsRadios8
    };

    private ExpectedResultPanel() {
        isConnectedToAll.addActionListener(e -> updateRadioButtons());
    }
    private static final ExpectedResultPanel INSTANCE = new ExpectedResultPanel();
    public static ExpectedResultPanel getInstance() {return INSTANCE;}

    public JPanel getPanel() {
        return mainPanel;
    }

    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    public void updateView() {
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

    private void updateRadioButtons() {
        if (isConnectedToAll.isSelected()) {
            for (Component component : partsLabels.getComponents()) {
                component.setVisible(false);

            }
            for (JPanel radioPanel : radioPanels) {
                for (Component component : radioPanel.getComponents()) {
                    component.setVisible(false);
                }
            }
        } else {
            updateView();
        }
    }

    public HashMap<String, Set<Integer>> getGoals() {
        return null;
    }

    public void printOut(Course course) {

    }
}
