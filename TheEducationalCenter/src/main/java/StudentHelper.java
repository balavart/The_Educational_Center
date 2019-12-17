import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class StudentHelper {

  private Document document;
  private CurriculumHelper curriculumHelper;
  private TaskHelper taskHelper;

  public StudentHelper(CurriculumHelper curriculumHelper, TaskHelper taskHelper)
      throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
    this.curriculumHelper = curriculumHelper;
    this.taskHelper = taskHelper;
  }

  public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    Element studentProfile = document.getDocumentElement();
    NodeList nodeStudentList = studentProfile.getElementsByTagName("student");

    int taskIdCount = 0;

    for (int i = 0; i < nodeStudentList.getLength(); i++) {
      Node studentNode = nodeStudentList.item(i);
      Student student = new Student();

      Integer id = Integer.valueOf(studentNode.getAttributes().item(0).getNodeValue());
      String fullName =
          studentProfile.getElementsByTagName("fullName").item(i).getFirstChild().getTextContent();
      String city =
          studentProfile.getElementsByTagName("city").item(i).getFirstChild().getTextContent();
      String email =
          studentProfile.getElementsByTagName("email").item(i).getFirstChild().getTextContent();
      String startDate =
          studentProfile.getElementsByTagName("startDate").item(i).getFirstChild().getTextContent();
      boolean contractSigned =
          Boolean.parseBoolean(
              studentProfile
                  .getElementsByTagName("contractSigned")
                  .item(i)
                  .getFirstChild()
                  .getTextContent());
      Integer curriculumId =
          Integer.valueOf(
              studentProfile
                  .getElementsByTagName("curriculumId")
                  .item(i)
                  .getFirstChild()
                  .getTextContent());
      Curriculum curriculum = curriculumHelper.getCurriculum().get(curriculumId);
      Map<Task, String> taskResults = new HashMap<>();


      String grade =
          studentProfile.getElementsByTagName("grade").item(i).getFirstChild().getTextContent();

      NodeList taskProfileNodeList = document.getElementsByTagName("taskResults");
      Node taskProfileNode = taskProfileNodeList.item(taskIdCount);
      Element elementTaskProfile = (Element) taskProfileNode;
      NodeList elementTaskList = elementTaskProfile.getChildNodes();

      for (int j = 0; j < elementTaskList.getLength(); j++) {
        Node taskNode = elementTaskList.item(j);
        if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
          Element elementTask = (Element) taskNode;

          Integer taskId = Integer.valueOf(elementTask.getAttributes().item(0).getNodeValue());
          String taskStatus =
              studentProfile.getElementsByTagName("status").item(i).getFirstChild().getTextContent();

          taskResults.put(taskHelper.getAllTasks().get(taskId), taskStatus);

          System.out.println(taskId);
          System.out.println(taskStatus);
        }
      }
      taskIdCount++;

      student.setId(id);
      student.setFullName(fullName);
      student.setCity(city);
      student.setEmail(email);
      student.setStartDate(startDate);
      student.setContractSigned(contractSigned);
      student.setCurriculum(curriculum);
      student.setTaskResults(taskResults);
//      student.setGrade(grade);
      students.add(student);
    }
    return students;
  }
}
