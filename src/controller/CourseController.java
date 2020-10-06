package controller;

import View.CoursePanel;
import model.Course;

public interface CourseController {
    CoursePanel getPanel();
    Course getCourse();
    void updateModel();
}
