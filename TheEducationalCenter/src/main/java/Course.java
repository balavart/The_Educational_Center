import java.util.List;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
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

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public void setCourseTaskList(List<Task> courseTaskList) {
    this.courseTaskList = courseTaskList;
  }

  @Override
  public String toString() {
    return "Course{"
        + "id="
        + id
        + ", Title='"
        + title
        + '\''
        + ", Author='"
        + author
        + '\''
        + ", Creation Date='"
        + creationDate
        + '\''
        + ", Course Task List="
        + courseTaskList
        + '}';
  }
}
