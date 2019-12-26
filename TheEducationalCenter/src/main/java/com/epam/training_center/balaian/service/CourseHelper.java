package com.epam.training_center.balaian.service;

import java.util.Map;
import com.epam.training_center.balaian.model.Course;

/**
 * The interface Course helper.
 *
 * @author balavart
 * @created 22.12.2019
 * @since 1.8
 */
@FunctionalInterface
public interface CourseHelper {

  /**
   * Gets all courses.
   *
   * @return the all courses
   */
  Map<Integer, Course> getAllCourses();
}
