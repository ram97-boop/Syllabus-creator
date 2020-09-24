package View;

import model.Course;

import javax.swing.*;
import java.awt.*;

public class CourseContentPanel implements CoursePanel {
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JPanel partsPanel;
    private JTextArea textArea1;

    private JTextField nPartsField;
    private JButton button1;

    private boolean consistsOfParts = false;

    private CourseContentPanel() {
        button1.addActionListener(e -> updatePartFields());
    }

    private static final CourseContentPanel INSTANCE = new CourseContentPanel();
    public static CourseContentPanel getInstance() {return INSTANCE;}

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
        for (Component component : partsPanel.getComponents()) {
            component.setVisible(false);
        }
    }

    private void updatePartFields() {
        updateView();
        int nParts = getNParts();
        consistsOfParts = nParts != 0;
        int i = 0;
        for (Component component : partsPanel.getComponents()) {
            if (i < nParts) {
                component.setVisible(true);
            }
            i++;
        }
    }

    public boolean consistsOfParts() {
        return consistsOfParts;
    }

    public int getNParts() {
        return Integer.parseInt(nPartsField.getText());
    }

//    public String[] getParts() {
//
//    }


    public void printOut(Course course) {

    }


}
