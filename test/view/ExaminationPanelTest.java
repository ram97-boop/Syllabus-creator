package view;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class ExaminationPanelTest {

    private Course course;
    private ExaminationPanel panel;

    @Before
    public void setUp() {
        course = new Course();
        panel = new ExaminationPanel();

        ArrayList<CoursePart> courseParts = new ArrayList<>();
        CoursePart part1 = new CoursePart();
        part1.setName("Teori");
        CoursePart part2 = new CoursePart();
        part2.setName("Projekt");
        courseParts.add(part1);
        courseParts.add(part2);
        course.setCourseParts(courseParts);
        panel.updateView(course);

        // a.
        JTextField[] examinationFields = panel.getExaminationFields();
        examinationFields[0].setText("skriftliga och muntliga övningsuppgifter.");
        examinationFields[1].setText("skriftlig och muntlig redovisning av programutvecklingsprojekt.");

        panel.getExaminationOnEnglishCheckBox().setSelected(true);
        panel.getEnglishRadio2().setSelected(true);

        // b.
        panel.getHasAttendanceCheckBox().setSelected(true);
        panel.getNotDistanceAttendancePane().setText("kursens seminarier");

        // c.
        panel.getCourseGradingScaleComboBox().setSelectedIndex(0); // 7-gradingScale
        panel.getGradingScales().get(0).setSelectedIndex(0); // 7-gradingScale
        panel.getGradingScales().get(1).setSelectedIndex(0); // 7-gradingScale

        panel.getTotalGradeRadio1().setSelected(true);

        // d.
        // e.
        // f.
        panel.getSupplementCheckBox().setSelected(true);
        panel.getSupplementRadios()[0].setSelected(true); // Alternative 1

        panel.printOut();
    }

    @Test
    public void printOutShouldPrintOutCorrect() {
        String actualOutput = panel.getPrintOutPane().getText();
        String expectedOutput = "a. Kursen examineras på följande vis: \n" +
                "Kunskapskontroll för Teori sker genom skriftliga och muntliga övningsuppgifter.\n" +
                "Kunskapskontroll för Projekt sker genom skriftlig och muntlig redovisning av " +
                "programutvecklingsprojekt.\n" +
                "\n" +
                "Examinator har möjlighet att besluta om anpassad eller alternativ examination för studenter med " +
                "funktionsnedsättning.\n" +
                "\n" +
                "Om undervisningen sker på engelska kan även examination komma att genomföras på engelska.\n" +
                "\n" +
                "b. För godkänt slutbetyg krävs deltagande i kursens seminarier. Om särskilda skäl föreligger kan " +
                "examinator efter samråd med vederbörande lärare medge den studerande befrielse från skyldigheten " +
                "att delta i viss obligatorisk undervisning.\n" +
                "\n" +
                "c. Betygsättning: Kursens slutbetyg sätts enligt sjugradig målrelaterad skala\n" +
                "A = Utmärkt\n" +
                "B = Mycket bra\n" +
                "C = Bra\n" +
                "D = Tillfredsställande\n" +
                "E = Tillräckligt\n" +
                "Fx = Underkänd, något mer arbete krävs\n" +
                "F = Underkänd, mycket mer arbete krävs\n" +
                "\n" +
                "Betygsättning av Teori sker enligt sjugradig målrelaterad skala.\n" +
                "Betygsättning av Projekt sker enligt sjugradig målrelaterad skala.\n" +
                "\n" +
                "För godkänt slutbetyg krävs godkänt betyg på samtliga ingående delar.\n" +
                "\n" +
                "Kursens slutbetyg sätts genom en sammanvägning av betygen på kursens delar, där de olika " +
                "delarnas betyg viktas i förhållande till deras omfattning.\n" +
                "\n" +
                "d. Kursens betygskriterier delas ut vid kursstart.\n" +
                "\n" +
                "e. Studerande som underkänts i ordinarie prov har rätt att genomgå ytterligare prov så länge " +
                "kursen ges. Antalet provtillfällen är inte begränsat. Med prov jämställs också andra " +
                "obligatoriska kursdelar. Studerande som godkänts på prov får inte genomgå förnyat prov för " +
                "högre betyg. En student, som utan godkänt resultat har genomgått två prov för en kurs eller en " +
                "del av en kurs, har rätt att få en annan examinator utsedd, om inte särskilda skäl talar mot det. " +
                "Framställan härom ska göras till institutionsstyrelsen. Kursen har minst tre " +
                "examinationstillfällen (vid behov: för varje del) per läsår de år då undervisning ges. För de " +
                "läsår som kursen inte ges erbjuds minst ett examinationstillfälle. \n" +
                "\n" +
                "f. Vid betyget Fx ges möjlighet att komplettera upp till betyget E. Examinator beslutar om " +
                "vilka kompletteringsuppgifter som ska utföras och vilka kriterier som ska gälla för att bli " +
                "godkänd på kompletteringen. Kompletteringen ska äga rum före nästa examinationstillfälle.";

        assertEquals(actualOutput, expectedOutput);
    }
}
