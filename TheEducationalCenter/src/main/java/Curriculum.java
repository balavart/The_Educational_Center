import java.util.List;
import java.util.Map;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class Curriculum {

  private Integer id;
  private String title;
  private String author;
  private String creationDate;
  private List<Course> curriculumCourseList;

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

  public void setCurriculumCourseList(List<Course> curriculumCourseList) {
    this.curriculumCourseList = curriculumCourseList;
  }

  @Override
  public String toString() {
    return "Curriculum{"
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
        + ", Curriculum Course List="
        + curriculumCourseList
        + '}';
  }
}
