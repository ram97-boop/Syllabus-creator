package controller;

import View.ExpectedResultPanel;
import model.Course;
import model.CoursePart;
import model.Goal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
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

        JPanel[] goalPanels = expectedResultPanel.getGoalPanels();

        Arrays.stream(goalPanels).forEach(goalPanel -> {
            Goal goal = new Goal();
            JTextField goalDescription = (JTextField) goalPanel.getComponent(0);

            if (!goalDescription.getText().isEmpty()) {

                goal.describeGoal(goalDescription.getText());

                if (!expectedResultPanel.getIsConnectedToAll().isSelected() && !courseParts.isEmpty()) {
                    JPanel buttonPanel = (JPanel) goalPanel.getComponent(1);
                    setCoursePartForGoal(buttonPanel, goal);
                } else if (!courseParts.isEmpty()) {
                    goal.setCourseParts(courseParts);
                }

                goalList.add(goal);

            }
        });

        course.setGoals(goalList);

    }

    private void setCoursePartForGoal(JPanel buttonPanel, Goal goal) {
        ArrayList<CoursePart> courseParts = course.getCourseParts();
        AtomicInteger index = new AtomicInteger();

        Arrays.stream(buttonPanel.getComponents()).forEach(component -> {
            JRadioButton button = (JRadioButton) component;

            if (index.get() <= courseParts.size() && button.isVisible() && button.isSelected()) {
                goal.addCoursePart(courseParts.get(index.get()));
            }

            index.getAndIncrement();
        });
    }
}
