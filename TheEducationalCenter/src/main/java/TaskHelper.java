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
public class TaskHelper {

  private Document document;

  public TaskHelper() throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringElementContentWhitespace(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    document = documentBuilder.parse("src/main/resources/StudentReport.xml");
  }

  public Map<Integer, Task> getAllTasks() {
    Map<Integer, Task> taskMap = new HashMap<>();

    NodeList taskProfileNodeList = document.getElementsByTagName("taskProfiles");
    Node taskProfileNode = taskProfileNodeList.item(0);
    Element elementTaskProfile = (Element) taskProfileNode;
    NodeList elementTaskList = elementTaskProfile.getChildNodes();

    for (int i = 0; i < elementTaskList.getLength(); i++) {
      Node taskNode = elementTaskList.item(i);
      if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
        Task task = new Task();
        Element elementTask = (Element) taskNode;

        Integer id = Integer.valueOf(elementTask.getAttributes().item(0).getNodeValue());
        String type = elementTask.getAttributes().item(1).getNodeValue();
        String title = elementTask.getElementsByTagName("title").item(0).getTextContent();
        Integer duration =
            Integer.valueOf(elementTask.getElementsByTagName("duration").item(0).getTextContent());

        task.setId(id);
        task.setType(type);
        task.setTitle(title);
        task.setDuration(duration);
        taskMap.put(id, task);
      }
    }
    return taskMap;
  }
}
