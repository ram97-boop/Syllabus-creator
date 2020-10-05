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


    private JTextField[][] partFields = {
            {part1S, part1E, credits1},
            {part2S, part2E, credits2},
            {part3S, part3E, credits3},
            {part4S, part4E, credits4},
            {part5S, part5E, credits5},
            {part6S, part6E, credits6},
    };

    private int nParts = 0;

    // Constructors
    private CourseContentPanel() {
        setVisibilityOfComponents();
        setUpComboBox();
        addActionListeners();
        setToolTips();
    }
    private static final CourseContentPanel INSTANCE = new CourseContentPanel();
    public static CourseContentPanel getInstance() {return INSTANCE;}

    private void setVisibilityOfComponents() {
        nPartsComboBox.setEditable(false);
        partsPanel.setVisible(false);
    }
    private void setUpComboBox() {
        int[] possibleNParts = {0, 1, 2, 3, 4, 5, 6};
        for (int possibleNPart : possibleNParts) {
            nPartsComboBox.addItem(possibleNPart);
        }
    }
    private void addActionListeners() {
        nPartsComboBox.addActionListener(e -> updatePartFields());
        printOutButton.addActionListener(e -> printOut());
    }

    private void setToolTips() {
        courseContentLabel.setToolTipText("");
        creditsLabel.setToolTipText("Använd punkt som kommatecken.");
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
    public void updateView(MainFrame frame, Course course) {
        this.frame = frame;
    }

    // Action listener methods
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
        frame.keepSize();
    }

    // Getters to Controller
    public int getNParts() {
        return nParts;
    }

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
