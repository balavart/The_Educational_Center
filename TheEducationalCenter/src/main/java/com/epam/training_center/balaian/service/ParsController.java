package com.epam.training_center.balaian.service;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.epam.training_center.balaian.model.Student;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * The type Parsing controller.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/17/2019
 */
public class ParsController {

  private DocumentBuilderFactory documentBuilderFactory;
  private DocumentBuilder documentBuilder;
  private Document document;

  private TaskHelper taskHelper;
  private CourseHelper courseHelper;
  private CurriculumHelper curriculumHelper;
  private StudentHelper studentHelper;

  /**
   * Instantiates a new Parsing controller.
   *
   * @param pathXmlFile the path xml file
   * @throws ParserConfigurationException the parser configuration exception
   * @throws IOException the io exception
   * @throws SAXException the sax exception
   */
  public ParsController(String pathXmlFile)
      throws ParserConfigurationException, IOException, SAXException {
    documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setIgnoringElementContentWhitespace(true);
    documentBuilder = documentBuilderFactory.newDocumentBuilder();
    document = documentBuilder.parse(pathXmlFile);
  }

  /**
   * Start parsing.
   *
   * @return the student list
   */
  public List<Student> startParsing() {
    taskHelper = new TaskHelperImpl(documentBuilderFactory, documentBuilder, document);
    courseHelper =
        new CourseHelperImpl(taskHelper, documentBuilderFactory, documentBuilder, document);
    curriculumHelper =
        new CurriculumHelperImpl(courseHelper, documentBuilderFactory, documentBuilder, document);
    studentHelper =
        new StudentHelperImpl(
            curriculumHelper, taskHelper, documentBuilderFactory, documentBuilder, document);

    return studentHelper.getAllStudents();
  }
}
