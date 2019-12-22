package gui;

import javafx.scene.control.TreeItem;

/**
 * The type Student values.
 *
 * @author Vardan_Balaian
 * @created 12/20/2019
 * @since 1.8
 */
public class StudentValues extends TreeItem<Object> {

  private int studentId = -1;
  private String description;
  private int taskID;

  /**
   * Instantiates a new Student values.
   *
   * @param value the value
   * @param description the description
   */
  public StudentValues(String value, String description) {
    super(value);
    this.description = description;
  }

  /**
   * Instantiates a new Student values.
   *
   * @param value the value
   * @param description the description
   * @param studentId the student id
   * @param taskID the task id
   */
  public StudentValues(String value, String description, int studentId, int taskID) {
    super(value);
    this.description = description;
    this.studentId = studentId;
    this.taskID = taskID;
  }

  public int getStudentId() {
    return studentId;
  }

  public String getDescription() {
    return description;
  }

  public int getTaskID() {
    return taskID;
  }
}
