import java.io.IOException;
import java.util.HashMap;
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

  public CourseHelper() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
  }

  public Map<Integer, Course> getAllCourses() {
    Map<Integer, Course> courseMap = new HashMap<>();
    NodeList courseProfileNodeList = document.getElementsByTagName("courseProfiles");
    Node courseProfileNode = courseProfileNodeList.item(0);
    Element elementCourseProfile = (Element) courseProfileNode;
    NodeList elementCourseList = elementCourseProfile.getChildNodes();

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

        course.setId(id);
        course.setTitle(title);
        course.setAuthor(author);
        course.setCreationDate(creationDate);
        courseMap.put(id, course);
      }
    }

    return courseMap;
  }
}
