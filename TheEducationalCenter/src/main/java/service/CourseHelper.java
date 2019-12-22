package service;

import java.util.Map;
import model.Course;

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
