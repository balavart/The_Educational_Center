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
      throws IOException, SAXException, ParserConfigurationException {

    StudentHelper studentHelper = new StudentHelper();
    CurriculumHelper curriculumHelper = new CurriculumHelper();
    CourseHelper courseHelper = new CourseHelper();
    TaskHelper taskHelper = new TaskHelper();

    List<Student> studentList = studentHelper.getAllStudents();
    List<Curriculum> curriculumList = curriculumHelper.getCurriculum();
    Map<Integer, Course> courseMap = courseHelper.getAllCourses();
    Map<Integer, Task> taskMap = taskHelper.getAllTasks();

    for (Student student : studentList) {
      System.out.println(student);
    }

    for (Curriculum curriculum : curriculumList) {
      System.out.println(curriculum);
    }

    for (Map.Entry<Integer, Course> entry : courseMap.entrySet()) {
      Integer key = entry.getKey();
      Course value = entry.getValue();
      System.out.println(key + " " + value);
    }

    for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
      Integer key = entry.getKey();
      Task value = entry.getValue();
      System.out.println(key + " " + value);
    }
  }
}
