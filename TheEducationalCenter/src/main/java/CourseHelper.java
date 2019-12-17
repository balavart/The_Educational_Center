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
public class CourseHelper {

  private Document document;
  private TaskHelper taskHelper;

  public CourseHelper(TaskHelper taskHelper) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
    this.taskHelper = taskHelper;
  }

  public Map<Integer, Course> getAllCourses() {
    Map<Integer, Course> courseMap = new HashMap<>();
    NodeList courseProfileNodeList = document.getElementsByTagName("courseProfiles");
    Node courseProfileNode = courseProfileNodeList.item(0);
    Element elementCourseProfile = (Element) courseProfileNode;
    NodeList elementCourseList = elementCourseProfile.getChildNodes();
    int taskIdCount = 0;

    for (int i = 0; i < elementCourseList.getLength(); i++) {
      Node courseNode = elementCourseList.item(i);
      if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
        Course course = new Course();
        Element elementCourse = (Element) courseNode;

        Integer id = Integer.valueOf(elementCourse.getAttributes().item(0).getNodeValue());
        String title = elementCourse.getElementsByTagName("title").item(0).getTextContent();
        String author = elementCourse.getElementsByTagName("author").item(0).getTextContent();
        String creationDate =
            elementCourse.getElementsByTagName("creationDate").item(0).getTextContent();

        List<Task> taskList = new ArrayList<>();

        NodeList taskProfileNodeList = document.getElementsByTagName("courseTasksList");
        Node taskProfileNode = taskProfileNodeList.item(taskIdCount);
        Element elementTaskProfile = (Element) taskProfileNode;
        NodeList elementTaskList = elementTaskProfile.getChildNodes();

        for (int j = 0; j < elementTaskList.getLength(); j++) {
          Node taskNode = elementTaskList.item(j);
          if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
            Element elementTask = (Element) taskNode;

            Integer taskId =
                Integer.valueOf(elementTask.getElementsByTagName("id").item(0).getTextContent());
            taskList.add(taskHelper.getAllTasks().get(taskId));
          }
        }

        taskIdCount++;

        course.setId(id);
        course.setTitle(title);
        course.setAuthor(author);
        course.setCreationDate(creationDate);
        course.setCourseTaskList(taskList);
        courseMap.put(id, course);
      }
    }
    return courseMap;
  }
}
