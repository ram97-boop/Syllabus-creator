package view;

import View.LiteraturePanel;
import model.Course;
import model.CoursePart;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

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
