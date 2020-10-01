package View;

import model.Course;
import model.CoursePart;

import javax.swing.*;
import java.awt.*;

// TODO Add more parts
// TODO Ta bort alla delar om man väljer 0 delar efter att ha fyllt i delar
// TODO Sätt - i printOut innan Del 1, 2 osv


public class CourseContentPanel implements CoursePanel {
    private MainFrame frame;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JPanel partsPanel;

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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextPane printOutPane;
    private JButton printOutButton;


    private JTextField[][] partFields = {
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
//        nPartsComboBox.setSelectedIndex(0);
        nPartsComboBox.addActionListener(e -> updatePartFields());
        printOutButton.addActionListener(e -> printOut());
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

    public void updateView(MainFrame frame, Course course) {
        for (JLabel label : labels) label.setVisible(nParts > 0);
        int i = 0;
        for (JTextField[] row : partFields) {
            for (JTextField field : row) {
                field.setVisible(i < nParts);
            }
            i++;
        }
        this.frame = frame;
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
        frame.keepSize();
    }

    public int getNParts() {
        return nParts;
    }

    // Getters to Controller

    public JTextField[][] getPartFields() {
        return partFields;
    }

    // Print out

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
