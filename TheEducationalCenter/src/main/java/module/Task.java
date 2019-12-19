package module;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Type: " + type + "\n" + "Title: " + title + "\n" + "Duration: " + duration;
  }
}
