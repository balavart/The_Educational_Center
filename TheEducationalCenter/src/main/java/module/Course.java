package module;

import java.util.List;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12 /16/2019
 */
public class Course {

  private Integer id;
  private String title;
  private String author;
  private String creationDate;
  private List<Task> courseTaskList;

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public List<Task> getCourseTaskList() {
    return courseTaskList;
  }

  public void setCourseTaskList(List<Task> courseTaskList) {
    this.courseTaskList = courseTaskList;
  }

  private String getTasksTitles() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Task task : courseTaskList) {
      stringBuilder.append(task.getTitle()).append("\n");
    }
    return stringBuilder.toString();
  }

  @Override
  public String toString() {
    return "Title: "
        + title
        + "\n"
        + "Author: "
        + author
        + "\n"
        + "Creation Date: "
        + creationDate
        + "\n"
        + "\n"
        + "Course Task List: "
        + "\n"
        + getTasksTitles();
  }
}
