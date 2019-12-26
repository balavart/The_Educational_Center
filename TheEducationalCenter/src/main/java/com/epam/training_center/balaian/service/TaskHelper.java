package com.epam.training_center.balaian.service;

import java.util.Map;
import com.epam.training_center.balaian.model.Task;

/**
 * The interface Task helper.
 *
 * @author balavart
 * @created 22.12.2019
 * @since 1.8
 */
@FunctionalInterface
public interface TaskHelper {

  /**
   * Gets all tasks.
   *
   * @return the all tasks
   */
  Map<Integer, Task> getAllTasks();
}
