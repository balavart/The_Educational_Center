package gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import model.Course;
import model.Student;
import model.Task;
import org.xml.sax.SAXException;
import service.ParsController;

/**
 * The type Controller.
 *
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/18/2019
 */
public class Controller {

  @FXML private TreeView<Object> treeView;
  @FXML private AnchorPane mainWindow;
  @FXML private TextFlow textFlow;

  /**
   * Menu item action.
   *
   * @throws IOException the io exception
   * @throws SAXException the sax exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  @FXML
  public void menuItemAction() throws IOException, SAXException, ParserConfigurationException {

    FileChooser fileChooser = new FileChooser();
    Stage stage = (Stage) mainWindow.getScene().getWindow();
    fileChooser.setInitialDirectory(new File("src/main/resources"));
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"));
    File selectedFile = fileChooser.showOpenDialog(stage);

    if (selectedFile != null) {
      showData(selectedFile.getPath());
    }
  }

  /** Exit action. */
  @FXML
  public void exitAction() {
    Stage stage = (Stage) mainWindow.getScene().getWindow();
    stage.close();
  }

  private void showData(String pathXmlFile)
      throws ParserConfigurationException, IOException, SAXException {
    ParsController parsing = new ParsController(pathXmlFile);
    List<Student> studentList = parsing.startParsing();

    StudentValues students = new StudentValues("Students", "Students");
    StudentValues studentFullNames;
    StudentValues studentCurriculumTitles;
    StudentValues courseTitle;
    StudentValues studentTaskTitles;

    treeView.setRoot(students);

    for (int i = 0; i < studentList.size(); i++) {
      studentFullNames =
          new StudentValues(studentList.get(i).getFullName(), (studentList.get(i).toString()));
      students.getChildren().add(studentFullNames);

      studentCurriculumTitles =
          new StudentValues(
              studentList.get(i).getCurriculum().getTitle(),
              studentList.get(i).getCurriculum().toString());
      studentFullNames.getChildren().add(studentCurriculumTitles);

      List<Course> courseList = studentList.get(i).getCurriculum().getCurriculumCourseList();
      int coursesNum = studentList.get(i).getCurriculum().getCurriculumCourseList().size();

      for (int j = 0; j < coursesNum; j++) {
        courseTitle = new StudentValues(courseList.get(j).getTitle(), courseList.get(j).toString());
        studentCurriculumTitles.getChildren().add(courseTitle);

        List<Task> taskList = courseList.get(j).getCourseTaskList();
        int tasksNum = courseList.get(j).getCourseTaskList().size();

        for (int k = 0; k < tasksNum; k++) {
          studentTaskTitles =
              new StudentValues(
                  taskList.get(k).getTitle(),
                  taskList.get(k).toString(),
                  i,
                  taskList.get(k).getId());
          courseTitle.getChildren().add(studentTaskTitles);
        }
      }
    }

    treeView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              StudentValues studentValue = (StudentValues) newValue;
              textFlow.getChildren().clear();
              Text text = new Text(((StudentValues) newValue).getDescription());
              textFlow.getChildren().add(text);

              if (studentValue.getStudentId() >= 0) {
                studentList
                    .get(studentValue.getStudentId())
                    .getTaskResults()
                    .forEach(
                        ((task, status) -> {
                          if (studentValue.getTaskID() == task.getId()) {
                            Text taskResultText = new Text("\n" + status);
                            textFlow.getChildren().add(taskResultText);
                          }
                        }));
              }
            });
  }
}
