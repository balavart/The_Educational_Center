package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Course;
import model.Curriculum;
import model.Student;
import model.Task;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/** The type Xml parsing test. */
class XMLParsingTest {

  private DocumentBuilderFactory documentBuilderFactory;
  private DocumentBuilder documentBuilder;
  private Document document;

  /**
   * Instantiates a new Xml parsing test.
   *
   * @throws ParserConfigurationException the parser configuration exception
   * @throws IOException the io exception
   * @throws SAXException the sax exception
   */
  public XMLParsingTest() throws ParserConfigurationException, IOException, SAXException {
    this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setIgnoringElementContentWhitespace(true);
    this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
    document = documentBuilder.parse("src/test/java/sources/xml_for_testing.xml");
  }

  /** Gets all tasks test. */
  @Test
  void getAllTasks() {
    Map<Integer, Task> taskTestMap =
        new HashMap<Integer, Task>() {
          {
            put(
                1,
                new Task() {
                  {
                    setId(1);
                    setType("Task's Type Test");
                    setTitle("Task's Title Test");
                    setDuration(8);
                  }
                });
          }
        };
    TaskHelper taskHelperExample =
        new TaskHelperImpl(documentBuilderFactory, documentBuilder, document);

    assertEquals(taskTestMap.toString(), taskHelperExample.getAllTasks().toString());
  }

  /** Gets all courses test. */
  @Test
  void getAllCourses() {
    TaskHelper taskHelperExample =
        new TaskHelperImpl(documentBuilderFactory, documentBuilder, document);
    Map<Integer, Course> courseTestMap =
        new HashMap<Integer, Course>() {
          {
            put(
                1,
                new Course() {
                  {
                    setId(1);
                    setTitle("Course's Title Test");
                    setAuthor("Course's Author Test");
                    setCreationDate("Course's Creation Date Test");
                    setCourseTaskList(
                        Collections.singletonList(taskHelperExample.getAllTasks().get(1)));
                  }
                });
          }
        };
    CourseHelper courseHelperExample =
        new CourseHelperImpl(taskHelperExample, documentBuilderFactory, documentBuilder, document);

    assertEquals(courseTestMap.toString(), courseHelperExample.getAllCourses().toString());
  }

  /** Gets curriculum test. */
  @Test
  void getCurriculum() {
    CourseHelper courseHelperExample =
        new CourseHelperImpl(
            new TaskHelperImpl(documentBuilderFactory, documentBuilder, document),
            documentBuilderFactory,
            documentBuilder,
            document);
    Map<Integer, Curriculum> curriculumTestMap =
        new HashMap<Integer, Curriculum>() {
          {
            put(
                1,
                new Curriculum() {
                  {
                    setId(1);
                    setTitle("Curriculum's Title Test");
                    setAuthor("Curriculum's Author Test");
                    setCreationDate("Curriculum's Creation Date Test");
                    setCurriculumCourseList(
                        Collections.singletonList(courseHelperExample.getAllCourses().get(1)));
                  }
                });
          }
        };
    CurriculumHelper curriculumHelperExample =
        new CurriculumHelperImpl(
            courseHelperExample, documentBuilderFactory, documentBuilder, document);

    assertEquals(curriculumTestMap.toString(), curriculumHelperExample.getCurriculum().toString());
  }

  /** Gets all students test. */
  @Test
  void getAllStudents() {
    TaskHelper taskHelperExample =
        new TaskHelperImpl(documentBuilderFactory, documentBuilder, document);
    CourseHelper courseHelperExample =
        new CourseHelperImpl(taskHelperExample, documentBuilderFactory, documentBuilder, document);
    CurriculumHelper curriculumHelperExample =
        new CurriculumHelperImpl(
            courseHelperExample, documentBuilderFactory, documentBuilder, document);

    Map<Task, String> taskResultsTestMap =
        new HashMap<Task, String>() {
          {
            put(
                new TaskHelperImpl(documentBuilderFactory, documentBuilder, document)
                    .getAllTasks()
                    .get(1),
                "Task's Type Test, Grade: 5");
          }
        };
    List<Student> studentTestList =
        Collections.singletonList(
            new Student() {
              {
                setId(1);
                setFullName("Student's Full Name Test");
                setCity("City Test");
                setEmail("E-mail Test");
                setStartDate("Start Date Test");
                setContractSigned(false);
                setCurriculum(curriculumHelperExample.getCurriculum().get(1));
                setTaskResults(taskResultsTestMap);
              }
            });

    StudentHelper studentHelperExample =
        new StudentHelperImpl(
            curriculumHelperExample,
            taskHelperExample,
            documentBuilderFactory,
            documentBuilder,
            document);

    assertEquals(studentTestList.toString(), studentHelperExample.getAllStudents().toString());
  }
}
