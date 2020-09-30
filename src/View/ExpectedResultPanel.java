package View;

import model.Course;
import model.CoursePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class ExpectedResultPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JCheckBox isConnectedToAll;
    private JPanel goalsPanel;
    private JLabel partLabel1;
    private JLabel partLabel2;
    private JLabel partLabel3;
    private JLabel partLabel4;
    private JLabel partLabel5;
    private JLabel partLabel6;
    private JTextField goal01;
    private JTextField goal02;
    private JTextField goal03;
    private JTextField goal04;
    private JTextField goal05;
    private JTextField goal06;
    private JTextField goal07;
    private JTextField goal08;
    private JTextField goal09;
    private JTextField goal10;
    private JTextField goal11;
    private JTextField goal12;
    private JRadioButton r011;
    private JRadioButton r012;
    private JRadioButton r013;
    private JRadioButton r014;
    private JRadioButton r015;
    private JRadioButton r016;
    private JRadioButton r021;
    private JRadioButton r022;
    private JRadioButton r023;
    private JRadioButton r024;
    private JRadioButton r025;
    private JRadioButton r026;
    private JRadioButton r031;
    private JRadioButton r032;
    private JRadioButton r033;
    private JRadioButton r034;
    private JRadioButton r035;
    private JRadioButton r036;
    private JRadioButton r041;
    private JRadioButton r042;
    private JRadioButton r043;
    private JRadioButton r044;
    private JRadioButton r045;
    private JRadioButton r046;
    private JRadioButton r051;
    private JRadioButton r052;
    private JRadioButton r053;
    private JRadioButton r054;
    private JRadioButton r055;
    private JRadioButton r056;
    private JRadioButton r061;
    private JRadioButton r062;
    private JRadioButton r063;
    private JRadioButton r064;
    private JRadioButton r065;
    private JRadioButton r066;
    private JRadioButton r071;
    private JRadioButton r072;
    private JRadioButton r073;
    private JRadioButton r074;
    private JRadioButton r075;
    private JRadioButton r076;
    private JRadioButton r081;
    private JRadioButton r082;
    private JRadioButton r083;
    private JRadioButton r084;
    private JRadioButton r085;
    private JRadioButton r086;
    private JRadioButton r091;
    private JRadioButton r092;
    private JRadioButton r093;
    private JRadioButton r094;
    private JRadioButton r095;
    private JRadioButton r096;
    private JRadioButton r101;
    private JRadioButton r102;
    private JRadioButton r103;
    private JRadioButton r104;
    private JRadioButton r105;
    private JRadioButton r106;
    private JRadioButton r111;
    private JRadioButton r112;
    private JRadioButton r113;
    private JRadioButton r114;
    private JRadioButton r115;
    private JRadioButton r116;
    private JRadioButton r121;
    private JRadioButton r122;
    private JRadioButton r123;
    private JRadioButton r124;
    private JRadioButton r125;
    private JRadioButton r126;
    private JButton printOutButton;
    private JTextPane printOutPane;
    private JRadioButton printAlt1Radio;
    private JRadioButton printAlt2Radio;


    private final JLabel[] partLabels = {
        partLabel1,
        partLabel2,
        partLabel3,
        partLabel4,
        partLabel5,
        partLabel6,
    };

    private final HashMap<JTextField, JRadioButton[]> goals = new HashMap<>();

    int nParts = 0;

    //Constructors
    private ExpectedResultPanel() {
        isConnectedToAll.addActionListener(e -> updateRadioButtons());
        setGoalsMap();
        printAlt1Radio.setSelected(true);
        printAlt1Radio.addActionListener(e -> updatePrintOutRadios(printAlt2Radio));
        printAlt2Radio.addActionListener(e -> updatePrintOutRadios(printAlt1Radio));
        printOutButton.addActionListener(e -> printOut());
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
    public void updateView(MainFrame frame, Course course) {
        ArrayList<CoursePart> courseParts = course.getCourseParts();
        nParts = courseParts.size();
        int i = 0;
        for (JLabel partLabel : partLabels) {
            partLabel.setVisible(i < nParts);
            String courseName = i < nParts ? courseParts.get(i).getName() : null;
            partLabel.setText(courseName);
            i++;
        }
        for (JRadioButton[] radioPanel : goals.values()) {
            i = 0;
            for (JRadioButton radioButton : radioPanel) {
                radioButton.setVisible(i < nParts);
                i++;
            }
        }
        isConnectedToAll.setVisible(nParts > 0);
        printAlt1Radio.setVisible(nParts > 0);
        printAlt2Radio.setVisible(nParts > 0);
        frame.keepSize();
    }

    // Action listener methods
    private void updateRadioButtons() {
        for (JLabel partLabel : partLabels) {
            partLabel.setVisible(!isConnectedToAll.isSelected());
        }
        int i;
        for (JRadioButton[] radioPanel : goals.values()) {
            i = 0;
            for (JRadioButton radioButton : radioPanel) {
                radioButton.setVisible(!isConnectedToAll.isSelected() && i < nParts);
                i++;
            }
        }

        printAlt1Radio.setSelected(isConnectedToAll.isSelected() || printAlt1Radio.isSelected());
        printAlt2Radio.setSelected(!isConnectedToAll.isSelected() && printAlt2Radio.isSelected());
        printAlt2Radio.setEnabled(!isConnectedToAll.isSelected());
    }

    private void updatePrintOutRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    // Getters to Controller

    public JCheckBox getIsConnectedToAll() {
        return isConnectedToAll;
    }

    public HashMap<JTextField, JRadioButton[]> getGoals() {
        return goals;
    }


    // Helper methods

    private void setGoalsMap() {
        goals.put(goal01, new JRadioButton[]{r011, r012, r013, r014, r015, r016});
        goals.put(goal02, new JRadioButton[]{r021, r022, r023, r024, r025, r026});
        goals.put(goal03, new JRadioButton[]{r031, r032, r033, r034, r035, r036});
        goals.put(goal04, new JRadioButton[]{r041, r042, r043, r044, r045, r046});
        goals.put(goal05, new JRadioButton[]{r051, r052, r053, r054, r055, r056});
        goals.put(goal06, new JRadioButton[]{r061, r062, r063, r064, r065, r066});
        goals.put(goal07, new JRadioButton[]{r071, r072, r073, r074, r075, r076});
        goals.put(goal08, new JRadioButton[]{r081, r082, r083, r084, r085, r086});
        goals.put(goal09, new JRadioButton[]{r091, r092, r093, r094, r095, r096});
        goals.put(goal10, new JRadioButton[]{r101, r102, r103, r104, r105, r106});
        goals.put(goal11, new JRadioButton[]{r111, r112, r113, r114, r115, r116});
        goals.put(goal12, new JRadioButton[]{r121, r122, r123, r124, r125, r126});
    }


    // Print out
    public void printOut() {
        String outPutText = "";
        if (isConnectedToAll.isSelected()) {
            outPutText += "De förväntade studieresultaten behandlas i alla kursdelar.\n\n";
        }
        outPutText += "Efter att ha genomgått kursen ska studenten kunna:\n";
        if (printAlt1Radio.isSelected()) {
            for (JTextField goal : goals.keySet()) {
                if (!goal.getText().isEmpty()) {
                    outPutText += goal.getText() +",\n";
                }
            }
        }
        printOutPane.setText(outPutText);
    }
}
