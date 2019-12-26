package com.epam.training_center.balaian.service;

import java.util.Map;
import com.epam.training_center.balaian.model.Curriculum;

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
