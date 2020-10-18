package view;

import View.LiteraturePanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * LiteraturePanelTest
 * Test class for LiteraturePanel
 *
 * @author Mikael Stener
 */

public class LiteraturePanelTest {

    private LiteraturePanel panel;

    @Before
    public void setUp() {
        panel = new LiteraturePanel();

        panel.getInstitutionField().setText("Matematiska institutionen");

        panel.printOut();
    }

    @Test
    public void printOutShouldPrintOutCorrect() {
        String actualOutput = panel.getPrintOutPane().getText();
        String expectedOutput = "Kurslitteratur beslutas av institutionsstyrelsen och publiceras på " +
                "Matematiska institutionens webbplats senast 2 månader före kursstart.\n" +
                "\n";

        assertEquals(actualOutput, expectedOutput);
    }
}
