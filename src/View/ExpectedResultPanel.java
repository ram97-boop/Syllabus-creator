package View;

import model.Course;
import model.CoursePart;
import model.Goal;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
    private JLabel goalsLabel;
    private JTextField goal13;
    private JTextField goal14;
    private JTextField goal15;
    private JTextField goal16;
    private JRadioButton r131;
    private JRadioButton r132;
    private JRadioButton r133;
    private JRadioButton r134;
    private JRadioButton r135;
    private JRadioButton r136;
    private JRadioButton r141;
    private JRadioButton r142;
    private JRadioButton r143;
    private JRadioButton r144;
    private JRadioButton r145;
    private JRadioButton r146;
    private JRadioButton r151;
    private JRadioButton r152;
    private JRadioButton r153;
    private JRadioButton r154;
    private JRadioButton r155;
    private JRadioButton r156;
    private JRadioButton r161;
    private JRadioButton r162;
    private JRadioButton r163;
    private JRadioButton r164;
    private JRadioButton r165;
    private JRadioButton r166;
    private JSplitPane splitPane;

    MainFrame frame;
    Properties properties;


    private final JLabel[] partLabels = {
        partLabel1,
        partLabel2,
        partLabel3,
        partLabel4,
        partLabel5,
        partLabel6,
    };

    private final LinkedHashMap<JTextField, JRadioButton[]> goals = new LinkedHashMap<>();

    ArrayList<CoursePart> courseParts;
    int nParts = 0;
    boolean hasParts = false;

    //Constructors
    public ExpectedResultPanel(MainFrame frame) {
        setUpGoalsMap();
        addActionListeners();
        printAlt1Radio.setSelected(true);
        this.frame = frame;
        properties = frame.getProperties();
        setToolTips();
    }

    public ExpectedResultPanel() {
        setUpGoalsMap();
    }

    public ExpectedResultPanel(MainFrame frame, Course course) {
        setUpGoalsMap();
        addActionListeners();
        printAlt1Radio.setSelected(course.getPrintGoalsAlt1());
        printAlt2Radio.setSelected(!course.getPrintGoalsAlt1());
        this.frame = frame;
        properties = frame.getProperties();
        setToolTips();
    }

    private void setUpGoalsMap() {
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
        goals.put(goal13, new JRadioButton[]{r131, r132, r133, r134, r135, r136});
        goals.put(goal14, new JRadioButton[]{r141, r142, r143, r144, r145, r146});
        goals.put(goal15, new JRadioButton[]{r151, r152, r153, r154, r155, r156});
        goals.put(goal16, new JRadioButton[]{r161, r162, r163, r164, r165, r166});
    }

    private void addActionListeners() {
        isConnectedToAll.addActionListener(e -> {
            updatePartsLabelsAndRadioButtons();
            updatePrintOutRadios();
        });
        printAlt1Radio.addActionListener(e -> printAlt2Radio.setSelected(false));
        printAlt2Radio.addActionListener(e -> printAlt1Radio.setSelected(false));
        printOutButton.addActionListener(e -> printOut());
    }

    private void setToolTips() {
        goalsLabel.setToolTipText(properties.getProperty("courseContentLabelToolTip"));
        printAlt1Radio.setToolTipText(properties.getProperty("expectedResultsPrintOutAlt1ToolTip"));
        printAlt2Radio.setToolTipText(properties.getProperty("expectedResultsPrintOutAlt2ToolTip"));
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
    public String getFrameName() {
        return properties.getProperty("ExpectedResultsTitle");
    }
    public JSplitPane getSplitPane() {
        return splitPane;
    }
    public void updateView(Course course) {
        updateCourseAttributes(course);
        setVisibilityOfComponents();
        setLabelNames();
        if (!course.getGoals().isEmpty()) {
            setGoalFields(course);
        }
//        frame.keepSize(getSplitPane());
    }

    private void setGoalFields(Course course) {
        ArrayList<Goal> goalsForCourse = course.getGoals();

        List<Goal> collect = goalsForCourse.stream().filter(goalArray -> goalArray.getCourseParts().size() < course.getCourseParts().size()).collect(Collectors.toList());

        IntStream.range(0, goalsForCourse.size()).forEach(index -> {
            JTextField component;

            if (index < 12) {
                component = (JTextField) goalsPanel.getComponent(7 * index);
            } else {
                component = (JTextField)goalsPanel.getComponent(7 * (index + 1));
            }

            JRadioButton[] jRadioButtons = goals.get(component);
            Goal goal = goalsForCourse.get(index);
            IntStream.range(0, jRadioButtons.length).forEach(buttonIndex -> {
                ArrayList<CoursePart> goalCourseParts = goal.getCourseParts();
                boolean present = goalCourseParts.stream().anyMatch(part -> part.getName().toLowerCase().equals(partLabels[buttonIndex].getText().toLowerCase()));
                if (present) {
                    jRadioButtons[buttonIndex].setSelected(true);
                }
            });
            component.setText(goal.getGoal());
        });

        if (collect.isEmpty() && !course.getCourseParts().isEmpty()) {
            isConnectedToAll.setSelected(true);
            updatePartsLabelsAndRadioButtons();
            updatePrintOutRadios();
        }

    }

    private void updateCourseAttributes(Course course) {
        courseParts = course.getCourseParts();
        nParts = courseParts.size();
        hasParts = nParts > 0;
    }

    private void setVisibilityOfComponents() {
        updatePartsLabelsAndRadioButtons();
        isConnectedToAll.setVisible(hasParts);
        printAlt1Radio.setVisible(hasParts);
        printAlt2Radio.setVisible(hasParts);
    }

    private void setLabelNames() {
        for (int i = 0; i < nParts; i++) {
            String partName = courseParts.get(i).getName();
            partLabels[i].setText(partName);
        }
    }

    // Action listener methods
    private void updatePartsLabelsAndRadioButtons() {
        for (int i = 0; i < partLabels.length; i++) {
            partLabels[i].setVisible(!isConnectedToAll.isSelected() && i < nParts);
        }

        for (JRadioButton[] radioPanel : goals.values()) {
            for (int i = 0; i < radioPanel.length; i++) {
                radioPanel[i].setVisible(!isConnectedToAll.isSelected() && i < nParts);
                radioPanel[i].setSelected(false);
            }
        }

    }

    private void updatePrintOutRadios() {
        printAlt1Radio.setSelected(isConnectedToAll.isSelected() || printAlt1Radio.isSelected());
        printAlt2Radio.setSelected(!isConnectedToAll.isSelected() && printAlt2Radio.isSelected());
        printAlt2Radio.setEnabled(!isConnectedToAll.isSelected());
    }

    // Getters to Controller

    public JCheckBox getIsConnectedToAll() {
        return isConnectedToAll;
    }

    public HashMap<JTextField, JRadioButton[]> getGoals() {
        return goals;
    }

    public JRadioButton getPrintAlt1Radio() {
        return printAlt1Radio;
    }


    // Print out
    public void printOut() {
        String outPutText = "";
        if (isConnectedToAll.isSelected()) {
            outPutText += "De förväntade studieresultaten behandlas i alla kursdelar.\n\n";
        }
        outPutText += "Efter att ha genomgått kursen ska studenten kunna:\n";

        if (printAlt1Radio.isSelected()) {
            outPutText += printOutAlternative1();
        } else if (printAlt2Radio.isSelected()) {
            outPutText += printOutAlternative2();
        }
        printOutPane.setText(outPutText);
    }

    private String printOutAlternative1() {
        String outPutText = "";
        for (Map.Entry<JTextField, JRadioButton[]> entry : goals.entrySet()) {
            if (!entry.getKey().getText().isEmpty()) {
                outPutText += entry.getKey().getText();
                if (hasParts && !isConnectedToAll.isSelected()) {
                    String connectedParts = " (";
                    int i = 0;
                    boolean firstPrinted = false;
                    for (JRadioButton radio : entry.getValue()) {
                        if (i < nParts) {
                            if (radio.isSelected()) {
                                connectedParts += firstPrinted ? ", " : "";
                                firstPrinted = true;
                                connectedParts += courseParts.get(i).getName();
                            }
                        }
                        i++;
                    }
                    connectedParts += ")";
                    outPutText += connectedParts;
                }
                outPutText += "\n";
            }
        }
        return outPutText;
    }

    private String printOutAlternative2() {
        String outPutText = "";
        int i = 0;
        for (CoursePart coursePart : courseParts) {
            outPutText += "Del " + (i + 1) + ", " + coursePart.getName() + ", " +
                    "(" + coursePart.getEngName() + ") " + coursePart.getCredits() + " hp:\n";
            for (Map.Entry<JTextField, JRadioButton[]> entry : goals.entrySet()) {
                if (entry.getValue()[i].isSelected()) {
                    outPutText += entry.getKey().getText() + "\n";
                }
            }
            i++;
            outPutText += "\n";
        }
        return outPutText;
    }
}
