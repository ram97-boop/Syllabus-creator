package controller;

import View.ExpectedResultPanel;
import model.Course;
import model.CoursePart;
import model.Goal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpectedResultController implements CourseController {
    private final Course course;
    private final ExpectedResultPanel expectedResultPanel;

    public ExpectedResultController(Course course, ExpectedResultPanel expectedResultPanel) {
        this.course = course;
        this.expectedResultPanel = expectedResultPanel;
    }

    public Course getCourse() {
        return course;
    }

    public ExpectedResultPanel getPanel() {
        return expectedResultPanel;
    }


    public void updateModel() {
        ArrayList<CoursePart> courseParts = course.getCourseParts();
        ArrayList<Goal> goalList = new ArrayList<>();

        HashMap<JTextField, JRadioButton[]> goalsMap = expectedResultPanel.getGoals();

        goalsMap.forEach((key, value) -> {
            Goal goal = new Goal();

            if (!key.getText().isEmpty()) {

                goal.describeGoal(key.getText());

                if (!expectedResultPanel.getIsConnectedToAll().isSelected() && !courseParts.isEmpty()) {
                    JRadioButton[] buttonPanel = value;
                    setCoursePartForGoal(buttonPanel, goal);
                } else if (!courseParts.isEmpty()) {
                    goal.setCourseParts(courseParts);
                }

                goalList.add(goal);

            }
        });

        course.setGoals(goalList);

    }

    private void setCoursePartForGoal(JRadioButton[] buttonPanel, Goal goal) {
        ArrayList<CoursePart> courseParts = course.getCourseParts();
        AtomicInteger index = new AtomicInteger();

        Arrays.stream(buttonPanel).forEach(button -> {
            if (index.get() <= courseParts.size() && button.isVisible() && button.isSelected()) {
                goal.addCoursePart(courseParts.get(index.get()));
            }
            index.getAndIncrement();
        });
    }

}
