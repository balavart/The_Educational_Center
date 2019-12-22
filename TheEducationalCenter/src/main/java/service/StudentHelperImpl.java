package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Curriculum;
import model.Student;
import model.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The type Student helper.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class StudentHelperImpl implements StudentHelper {

  private DocumentBuilderFactory documentBuilderFactory;
  private DocumentBuilder documentBuilder;
  private Document document;
  private CurriculumHelper curriculumHelper;
  private TaskHelper taskHelper;

  /**
   * Instantiates a new Student helper.
   *
   * @param curriculumHelper the curriculum helper
   * @param taskHelper the task helper
   * @param documentBuilderFactory the document builder factory
   * @param documentBuilder the document builder
   * @param document the document
   */
  public StudentHelperImpl(
      CurriculumHelper curriculumHelper,
      TaskHelper taskHelper,
      DocumentBuilderFactory documentBuilderFactory,
      DocumentBuilder documentBuilder,
      Document document) {
    this.documentBuilderFactory = documentBuilderFactory;
    this.documentBuilder = documentBuilder;
    this.document = document;
    this.curriculumHelper = curriculumHelper;
    this.taskHelper = taskHelper;
  }

  /**
   * Gets all students.
   *
   * @return the all students
   */
  @Override
  public List<Student> getAllStudents() {
    Element studentProfile = document.getDocumentElement();
    NodeList nodeStudentList = studentProfile.getElementsByTagName("student");

    List<Student> students = new ArrayList<>();
    int taskIdCount = 0;

    for (int i = 0; i < nodeStudentList.getLength(); i++) {
      Node studentNode = nodeStudentList.item(i);
      Student student = new Student();

      int id = Integer.parseInt(studentNode.getAttributes().item(0).getNodeValue());
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
      int grade;

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
              elementTask.getElementsByTagName("status").item(0).getFirstChild().getTextContent();
          grade =
              Integer.parseInt(
                  elementTask
                      .getElementsByTagName("grade")
                      .item(0)
                      .getFirstChild()
                      .getTextContent());

          taskResults.put(
              taskHelper.getAllTasks().get(taskId),
              taskStatus + ((grade == 0) ? "" : ", Grade: " + grade));
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
      students.add(student);
    }
    return students;
  }
}
