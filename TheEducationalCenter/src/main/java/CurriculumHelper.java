import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

  public CurriculumHelper() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
  }

  public List<Curriculum> getCurriculum() {
    List<Curriculum> curriculumList = new ArrayList<>();
    Element theEducationalCenter = document.getDocumentElement();
    NodeList nodeCurriculumList = theEducationalCenter.getElementsByTagName("curriculum");

    for (int i = 0; i < nodeCurriculumList.getLength(); i++) {
      Node curriculumNode = nodeCurriculumList.item(i);
      Curriculum curriculum = new Curriculum();

      Integer id = Integer.valueOf(curriculumNode.getAttributes().item(0).getNodeValue());
      String title =
          theEducationalCenter
              .getElementsByTagName("title")
              .item(i)
              .getFirstChild()
              .getTextContent();
      String author =
          theEducationalCenter
              .getElementsByTagName("author")
              .item(i)
              .getFirstChild()
              .getTextContent();
      String creationDate =
          theEducationalCenter
              .getElementsByTagName("creationDate")
              .item(i)
              .getFirstChild()
              .getTextContent();

      curriculum.setId(id);
      curriculum.setTitle(title);
      curriculum.setAuthor(author);
      curriculum.setCreationDate(creationDate);
      curriculumList.add(curriculum);
    }

    return curriculumList;
  }
}
