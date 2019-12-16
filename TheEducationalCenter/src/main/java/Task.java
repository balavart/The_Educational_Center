/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class Task {

  private Integer id;
  private String type;
  private String title;
  private Integer duration;

  public void setId(Integer id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Task{"
        + "id="
        + id
        + ", Type='"
        + type
        + '\''
        + ", Title='"
        + title
        + '\''
        + ", Duration="
        + duration
        + '}';
  }
}
