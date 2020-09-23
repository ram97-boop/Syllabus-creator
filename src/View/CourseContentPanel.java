package View;

import javax.swing.*;

public class CourseContentPanel implements CoursePanel {
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JPanel partsPanel;
    private JTextArea textArea1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private final JTextField[] partFields = {
        textField1,
        textField2,
        textField3,
        textField4,
        textField5
    };
    private JTextField nPartsField;
    private JButton button1;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private final JTextField[] pointFields = {
            textField6,
            textField7,
            textField8,
            textField9,
            textField10
    };

    private boolean consistsOfParts = false;

    private CourseContentPanel() {
        for (JTextField partField : partFields) {
            partField.setEditable(false);
        }
        for (JTextField pointField : pointFields) {
            pointField.setEditable(false);
        }
        button1.addActionListener(e -> showPartFields());
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


    private void showPartFields() {
        consistsOfParts = getNParts() != 0;
        int nParts = Integer.parseInt(nPartsField.getText());

        for (int i = 0; i < nParts; i++) {
            partFields[i].setEditable(true);
            pointFields[i].setEditable(true);
        }
    }

    public boolean consistsOfParts() {
        return consistsOfParts;
    }

    public int getNParts() {
        return Integer.parseInt(nPartsField.getText());
    }

    public String[] getParts() {
        int nParts = getNParts();
        String[] parts = new String[nParts];
        for (int i = 0; i < nParts; i++) {
            parts[i] = partFields[i].getText();
        }
        return parts;
    }


    public void printOut(Course course) {

    }


}
