package service;

import java.util.Map;
import model.Task;

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
