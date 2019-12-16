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
public class StudentHelper {

  private Document document;

  public StudentHelper() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
  }

  public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    Element theEducationalCenter = document.getDocumentElement();
    NodeList nodeStudentList = theEducationalCenter.getElementsByTagName("student");

    for (int i = 0; i < nodeStudentList.getLength(); i++) {
      Node studentNode = nodeStudentList.item(i);
      Student student = new Student();

      Integer id = Integer.valueOf(studentNode.getAttributes().item(0).getNodeValue());
      String fullName =
          theEducationalCenter
              .getElementsByTagName("fullName")
              .item(i)
              .getFirstChild()
              .getTextContent();
      String city =
          theEducationalCenter
              .getElementsByTagName("city")
              .item(i)
              .getFirstChild()
              .getTextContent();
      String email =
          theEducationalCenter
              .getElementsByTagName("email")
              .item(i)
              .getFirstChild()
              .getTextContent();
      String startDate =
          theEducationalCenter
              .getElementsByTagName("startDate")
              .item(i)
              .getFirstChild()
              .getTextContent();
      boolean contractSigned = Boolean.parseBoolean(
          theEducationalCenter.getElementsByTagName("contractSigned").item(i).getFirstChild()
              .getTextContent());

      student.setId(id);
      student.setFullName(fullName);
      student.setCity(city);
      student.setEmail(email);
      student.setStartDate(startDate);
      student.setContractSigned(contractSigned);
      students.add(student);
    }
    return students;
  }
}
