package service;

import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The type Task helper.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class TaskHelperImpl implements TaskHelper {

  private DocumentBuilderFactory documentBuilderFactory;
  private DocumentBuilder documentBuilder;
  private Document document;

  /**
   * Instantiates a new Task helper.
   *
   * @param documentBuilderFactory the document builder factory
   * @param documentBuilder the document builder
   * @param document the document
   */
  public TaskHelperImpl(
      DocumentBuilderFactory documentBuilderFactory,
      DocumentBuilder documentBuilder,
      Document document) {
    this.documentBuilderFactory = documentBuilderFactory;
    this.documentBuilder = documentBuilder;
    this.document = document;
  }

  /**
   * Gets all tasks.
   *
   * @return the all tasks
   */
  @Override
  public Map<Integer, Task> getAllTasks() {
    NodeList taskProfileNodeList = document.getElementsByTagName("taskProfiles");
    Node taskProfileNode = taskProfileNodeList.item(0);
    Element elementTaskProfile = (Element) taskProfileNode;
    NodeList elementTaskList = elementTaskProfile.getChildNodes();

    Map<Integer, Task> taskMap = new HashMap<>();

    for (int i = 0; i < elementTaskList.getLength(); i++) {
      Node taskNode = elementTaskList.item(i);
      if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
        Task task = new Task();
        Element elementTask = (Element) taskNode;

        int id = Integer.parseInt(elementTask.getAttributes().item(0).getNodeValue());
        String type = elementTask.getAttributes().item(1).getNodeValue();
        String title = elementTask.getElementsByTagName("title").item(0).getTextContent();
        int duration =
            Integer.parseInt(elementTask.getElementsByTagName("duration").item(0).getTextContent());

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
