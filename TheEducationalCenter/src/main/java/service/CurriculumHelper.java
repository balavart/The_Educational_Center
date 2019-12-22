package service;

import java.util.Map;
import model.Curriculum;

/**
 * The interface Curriculum helper.
 *
 * @author balavart
 * @created 22.12.2019
 * @since 1.8
 */
@FunctionalInterface
public interface CurriculumHelper {

  /**
   * Gets curriculum.
   *
   * @return the curriculum
   */
  Map<Integer, Curriculum> getCurriculum();
}
