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
    private JPanel part1Panel;
    private JPanel part2Panel;
    private JPanel part3Panel;
    private JPanel part4Panel;
    private JPanel part5Panel;
    private JPanel part6Panel;


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

    public JPanel[] getPartPanels() {
        return new JPanel[]{
                part1Panel,
                part2Panel,
                part3Panel,
                part4Panel,
                part5Panel,
                part6Panel
        };
    }



    public void printOut(Course course) {

    }


}
