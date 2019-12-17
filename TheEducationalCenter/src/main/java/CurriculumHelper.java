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
public class CurriculumHelper {

  private Document document;
  private CourseHelper courseHelper;

  public CurriculumHelper(CourseHelper courseHelper) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
    this.courseHelper = courseHelper;
  }

  public Map<Integer, Curriculum> getCurriculum() {
    Map<Integer, Curriculum> curriculumMap = new HashMap<>();

    Element curriculumInfo = document.getDocumentElement();
    NodeList nodeCurriculumList = curriculumInfo.getElementsByTagName("curriculum");

    int curriculumIdCount = 0;

    for (int i = 0; i < nodeCurriculumList.getLength(); i++) {
      Node curriculumNode = nodeCurriculumList.item(i);
      Curriculum curriculum = new Curriculum();

      Integer id = Integer.valueOf(curriculumNode.getAttributes().item(0).getNodeValue());
      String title =
          curriculumInfo.getElementsByTagName("title").item(i).getFirstChild().getTextContent();
      String author =
          curriculumInfo.getElementsByTagName("author").item(i).getFirstChild().getTextContent();
      String creationDate =
          curriculumInfo
              .getElementsByTagName("creationDate")
              .item(i)
              .getFirstChild()
              .getTextContent();
      List<Course> courseList = new ArrayList<>();

      NodeList courseProfileNodeList = document.getElementsByTagName("courseTasksList");
      Node courseProfileNode = courseProfileNodeList.item(curriculumIdCount);
      Element elementCourseProfile = (Element) courseProfileNode;
      NodeList elementCourseList = elementCourseProfile.getChildNodes();

      for (int j = 0; j < elementCourseList.getLength(); j++) {
        Node courseNode = elementCourseList.item(j);
        if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
          Element elementCourse = (Element) courseNode;

          Integer curId =
              Integer.valueOf(elementCourse.getElementsByTagName("id").item(0).getTextContent());
          courseList.add(courseHelper.getAllCourses().get(curId));
        }
      }
      curriculumIdCount++;

      curriculum.setId(id);
      curriculum.setTitle(title);
      curriculum.setAuthor(author);
      curriculum.setCreationDate(creationDate);
      curriculum.setCurriculumCourseList(courseList);
      curriculumMap.put(id, curriculum);
    }
    return curriculumMap;
  }
}
