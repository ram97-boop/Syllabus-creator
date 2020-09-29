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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private final JTextField[][] partFields = {
            {part1S, part1E, credits1},
            {part2S, part2E, credits2},
            {part3S, part3E, credits3},
            {part4S, part4E, credits4},
            {part5S, part5E, credits5},
            {part6S, part6E, credits6},
    };

    private final JLabel[] labels = {
            label1, label2, label3
    };

    private int nParts = 0;

    // Constructors
    private CourseContentPanel() {
        nPartsComboBox.setEditable(false);
        int[] possibleNParts = {0, 1, 2, 3, 4, 5, 6};
        for (int possibleNPart : possibleNParts) {
            nPartsComboBox.addItem(possibleNPart);
        }
        nPartsComboBox.setSelectedIndex(0);
        nPartsComboBox.addActionListener(e -> updatePartFields());
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
        for (JLabel label : labels) label.setVisible(nParts > 0);
        int i = 0;
        for (JTextField[] row : partFields) {
            for (JTextField field : row) {
                field.setVisible(i < nParts);
            }
            i++;
        }
    }

    // Action listener methods
    private void updatePartFields() {
        Object nPartsObject;
        if ((nPartsObject = nPartsComboBox.getSelectedItem()) != null) {
            nParts = (int) nPartsObject;
        }
        for (JLabel label : labels) label.setVisible(nParts > 0);
        int i = 0;
        for (JTextField[] row : partFields) {
            for (JTextField field : row) {
                field.setVisible(i < nParts);
            }
            i++;
        }
    }

    public int getNParts() {
        return nParts;
    }

    // Getters to Controller

    public JTextField[][] getPartFields() {
        return partFields;
    }

    // Print out

    public void printOut(Course course) {

    }


}
