package service;

import java.util.List;
import model.Student;

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
