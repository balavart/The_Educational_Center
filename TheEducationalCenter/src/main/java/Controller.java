import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/17/2019
 */
public class Controller {
  private TaskHelper taskHelper;
  private CourseHelper courseHelper;
  private CurriculumHelper curriculumHelper;
  private StudentHelper studentHelper;

  public void startApp() throws IOException, SAXException, ParserConfigurationException {
    taskHelper = new TaskHelper();
    courseHelper = new CourseHelper(taskHelper);
    curriculumHelper = new CurriculumHelper(courseHelper);
    studentHelper = new StudentHelper(curriculumHelper,taskHelper);

    List<Student> students = studentHelper.getAllStudents();
    for (Student student : students) {
      System.out.println(student);
    }
  }
}
