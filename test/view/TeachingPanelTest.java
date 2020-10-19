package view;

import View.TeachingPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TeachingPanelTest
 * Test class for TeachingPanel
 *
 * @author Mikael Stener
 */

public class TeachingPanelTest {

    private TeachingPanel panel;

    @Before
    public void setUp() {
        panel = new TeachingPanel();

        panel.getTeachingPane().setText("föreläsningar, seminarier, och projektarbete. ");

        panel.getOtherThanSwedishCheckBox().setSelected(true);
        panel.getRadioButtonCourseLanguageNotSpecified().setSelected(true);

        panel.printOut();
    }

    @Test
    public void printOutShouldPrintOutCorrect() {
        String actualOutput = panel.getPrintOutPane().getText();
        String expectedOutput = "Undervisningen består av föreläsningar, seminarier, och projektarbete. \n" +
                "\n" +
                "Kursens undervisningsspråk anges inför varje kurstillfälle och framgår av den digitala utbildningskatalogen.\n" +
                "\n";

        assertEquals(expectedOutput, actualOutput);
    }
}
