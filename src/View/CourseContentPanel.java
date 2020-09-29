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

    private JPanel part1Panel;
    private JPanel part2Panel;
    private JPanel part3Panel;
    private JPanel part4Panel;
    private JPanel part5Panel;
    private JPanel part6Panel;
    private JComboBox<Integer> nPartsComboBox;

    private int nParts = 0;

    // Constructors
    private CourseContentPanel() {
        nPartsComboBox.setEditable(false);
        nPartsComboBox.addActionListener(e -> updatePartFields());

        int[] possibleNParts = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int possibleNPart : possibleNParts) {
            nPartsComboBox.addItem(possibleNPart);
        }
    }
    private static final CourseContentPanel INSTANCE = new CourseContentPanel();
    public static CourseContentPanel getInstance() {return INSTANCE;}

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
        for (Component component : partsPanel.getComponents()) {
            component.setVisible(i < nParts);
            i++;
        }
    }

    // Action listener methods
    private void updatePartFields() {
        Object nPartsObject;
        if ((nPartsObject = nPartsComboBox.getSelectedItem()) != null) {
            nParts = (int) nPartsObject;
        }
        int i = 0;
        for (Component component : partsPanel.getComponents()) {
            component.setVisible(i < nParts);
            i++;
        }
    }

    public int getNParts() {
        return nParts;
    }

    // Getters to Controller

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

    // Print out

    public void printOut(Course course) {

    }


}
