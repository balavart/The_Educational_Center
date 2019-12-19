package module;

import java.util.List;

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

  public List<Course> getCurriculumCourseList() {
    return curriculumCourseList;
  }

  public void setCurriculumCourseList(List<Course> curriculumCourseList) {
    this.curriculumCourseList = curriculumCourseList;
  }

  private String getCoursesTitle() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Course course : curriculumCourseList) {
      stringBuilder.append(course.getTitle()).append("\n");
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
        + "Curriculum Course List: "
        + "\n"
        + getCoursesTitle();
  }
}
