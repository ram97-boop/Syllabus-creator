package view;

import View.CourseContentPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * CourseContentPanelTest
 * Test class for CourseContentPanel
 *
 * @author Mikael Stener
 */

public class CourseContentPanelTest {

    private CourseContentPanel panel;

    @Before
    public void setUp() {
        panel = new CourseContentPanel();

        panel.getCourseContentTextPane().setText("Systematiska principer för konstruktion av korrekt och robust mjukvara.\n" +
                "Projektplanering och projektdokumentation.\n" +
                "Versionshantering, kontinuerlig integration, och kvalitetssäkring.\n" +
                "Testning och underhåll.");

        JComboBox<Integer> nPartsComboBox = panel.getNPartsComboBox();
        nPartsComboBox.setSelectedIndex(2); // 2 parts
        JTextField[][] partFields = panel.getPartFields();

        partFields[0][0].setText("Teori");
        partFields[0][1].setText("Theory");
        partFields[0][2].setText("3");

        partFields[1][0].setText("Projekt");
        partFields[1][1].setText("Project");
        partFields[1][2].setText("4.5");

        panel.printOut();
    }

    @Test
    public void printOutShouldPrintOutCorrect() {
        String actualOutput = panel.getPrintOutPane().getText();
        String expectedOutput = "a. Kursen behandlar: Systematiska principer för konstruktion av korrekt och robust mjukvara.\n" +
                "Projektplanering och projektdokumentation.\n" +
                "Versionshantering, kontinuerlig integration, och kvalitetssäkring.\n" +
                "Testning och underhåll." +
                "\n" +
                "\n" +
                "b. Kursen består av följande moment: \n" +
                "Del 1, Teori (Theory), 3 hp\n" +
                "Del 2, Projekt (Project), 4,5 hp\n";

        assertEquals(expectedOutput, actualOutput);
    }
}
