import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class Main {
  public static void main(String[] args)
      throws ParserConfigurationException, SAXException, IOException {
    Controller controller = new Controller();
    controller.startApp();
//    TaskHelper taskHelper = new TaskHelper();
//    CourseHelper courseHelper = new CourseHelper(taskHelper);
//    CurriculumHelper curriculumHelper = new CurriculumHelper(courseHelper);
//    StudentHelper studentHelper = new StudentHelper(curriculumHelper,taskHelper);
//    studentHelper.getAllStudents();

  }
}
