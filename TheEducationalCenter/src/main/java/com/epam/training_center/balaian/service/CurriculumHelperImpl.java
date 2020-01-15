package com.epam.training_center.balaian.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.epam.training_center.balaian.model.Course;
import com.epam.training_center.balaian.model.Curriculum;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The type Curriculum helper.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/16/2019
 */
public class CurriculumHelperImpl implements CurriculumHelper {

  private DocumentBuilderFactory documentBuilderFactory;
  private DocumentBuilder documentBuilder;
  private Document document;
  private CourseHelper courseHelper;

  /**
   * Instantiates a new Curriculum helper.
   *
   * @param courseHelper the course helper
   * @param documentBuilderFactory the document builder factory
   * @param documentBuilder the document builder
   * @param document the document
   */
  public CurriculumHelperImpl(
      CourseHelper courseHelper,
      DocumentBuilderFactory documentBuilderFactory,
      DocumentBuilder documentBuilder,
      Document document) {
    this.documentBuilderFactory = documentBuilderFactory;
    this.documentBuilder = documentBuilder;
    this.document = document;
    this.courseHelper = courseHelper;
  }

  /**
   * Gets curriculum.
   *
   * @return the curriculum
   */
  @Override
  public Map<Integer, Curriculum> getCurriculum() {
    Element curriculumInfo = document.getDocumentElement();
    NodeList nodeCurriculumList = curriculumInfo.getElementsByTagName("curriculum");

    Map<Integer, Curriculum> curriculumMap = new HashMap<>();
    int curriculumIdCount = 0;

    for (int i = 0; i < nodeCurriculumList.getLength(); i++) {
      Node curriculumNode = nodeCurriculumList.item(i);
      Curriculum curriculum = new Curriculum();

      int id = Integer.parseInt(curriculumNode.getAttributes().item(0).getNodeValue());
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

      NodeList courseProfileNodeList = document.getElementsByTagName("courseList");
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
