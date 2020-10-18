package view;

import View.ExpectedResultPanel;
import model.Course;
import model.CoursePart;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * ExpectedResultPanelTest
 * Test class for ExpectedResultPanel
 *
 * @author Mikael Stener
 */

public class ExpectedResultPanelTest {

    private Course course;
    private ExpectedResultPanel panel;

    @Before
    public void setUp() {
        course = new Course();
        panel = new ExpectedResultPanel();

        ArrayList<CoursePart> courseParts = new ArrayList<>();
        CoursePart part1 = new CoursePart();
        part1.setName("Teori");
        CoursePart part2 = new CoursePart();
        part2.setName("Projekt");
        courseParts.add(part1);
        courseParts.add(part2);
        course.setCourseParts(courseParts);
        panel.updateView(course);

        LinkedHashMap<JTextField, JRadioButton[]> goalsMap = (LinkedHashMap<JTextField, JRadioButton[]>) panel.getGoals();
        ArrayList<JTextField> goals = new ArrayList<>();
        goalsMap.forEach((key, value) -> {
            goals.add(key);
        });
        goals.get(0).setText("- redogöra för olika utvecklingsmetoder för programvara");
        goals.get(1).setText("- tillämpa lämpliga metoder för design och implementation av moderna programvarusystem");
        goals.get(2).setText("- systematiskt testa och kvalitetssäkra program");
        goals.get(3).setText("- använda versionshantering i parallellt arbetande projektgrupp");
        goals.get(4).setText("- utveckla mjukvara i projektgrupp");

        goalsMap.get(goals.get(0))[0].setSelected(true);
        goalsMap.get(goals.get(0))[1].setSelected(false);

        goalsMap.get(goals.get(1))[0].setSelected(true);
        goalsMap.get(goals.get(1))[1].setSelected(true);

        goalsMap.get(goals.get(2))[0].setSelected(true);
        goalsMap.get(goals.get(2))[1].setSelected(true);

        goalsMap.get(goals.get(3))[0].setSelected(true);
        goalsMap.get(goals.get(3))[1].setSelected(true);

        goalsMap.get(goals.get(4))[0].setSelected(true);
        goalsMap.get(goals.get(4))[1].setSelected(true);

        panel.getPrintAlt1Radio().setSelected(true);

        panel.printOut();
    }

    @Test
    public void printOutShouldPrintOutCorrect() {
        String actualOutput = panel.getPrintOutPane().getText();
        String expectedOutput = "Efter att ha genomgått kursen ska studenten kunna:\n" +
                "- redogöra för olika utvecklingsmetoder för programvara (Teori)\n" +
                "- tillämpa lämpliga metoder för design och implementation av moderna programvarusystem (Teori, Projekt)\n" +
                "- systematiskt testa och kvalitetssäkra program (Teori, Projekt)\n" +
                "- använda versionshantering i parallellt arbetande projektgrupp (Teori, Projekt)\n" +
                "- utveckla mjukvara i projektgrupp (Teori, Projekt)\n";

        assertEquals(actualOutput, expectedOutput);
    }
}
