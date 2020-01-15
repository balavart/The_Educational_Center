package com.epam.training_center.balaian.service;

import java.util.List;
import com.epam.training_center.balaian.model.Student;

/**
 * The interface Student helper.
 *
 * @author balavart
 * @created 22.12.2019
 * @since 1.8
 */
@FunctionalInterface
public interface StudentHelper {

  /**
   * Gets all students.
   *
   * @return the all students
   */
  List<Student> getAllStudents();
}
