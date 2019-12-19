package gui;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import module.Course;
import module.Student;
import module.Task;
import org.xml.sax.SAXException;
import service.ParsController;

/**
 * @author Vardan Balayan
 * @version 1.8
 * @created 12/18/2019
 */
public class Controller {

  @FXML private TreeView<String> treeView;

  @FXML private AnchorPane mainWindow;

  @FXML private TextFlow textFlow;

  @FXML
  public void exitAction() {
    Stage stage = (Stage) mainWindow.getScene().getWindow();
    stage.close();
  }

  @FXML
  void initialize() throws ParserConfigurationException, SAXException, IOException {
    ParsController parsController = new ParsController();
    List<Student> studentList = parsController.startParsing();
    int studentsNum = studentList.size();

    TreeItem<String> students = new TreeItem<>("Students");
    TreeItem<String> studentFullNames;
    TreeItem<String> studentCurriculumTitles;
    TreeItem<String> courseTitle;
    TreeItem<String> studentTaskTitles;

    treeView.setRoot(students);

    for (Student student : studentList) {
      studentFullNames = new TreeItem<>(student.getFullName());
      students.getChildren().add(studentFullNames);

      studentCurriculumTitles = new TreeItem<>(student.getCurriculum().getTitle());
      studentFullNames.getChildren().add(studentCurriculumTitles);

      List<Course> courseList = student.getCurriculum().getCurriculumCourseList();
      int coursesNum = student.getCurriculum().getCurriculumCourseList().size();

      for (int j = 0; j < coursesNum; j++) {
        courseTitle = new TreeItem<>(courseList.get(j).getTitle());
        studentCurriculumTitles.getChildren().add(courseTitle);

        List<Task> taskList = courseList.get(j).getCourseTaskList();
        int tasksNum = courseList.get(j).getCourseTaskList().size();

        for (int k = 0; k < tasksNum; k++) {
          studentTaskTitles = new TreeItem<>(taskList.get(k).getTitle());
          courseTitle.getChildren().add(studentTaskTitles);
        }
      }
    }

    treeView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              Text text;

              if (newValue.getValue().equals(students.getValue())) {
                textFlow.getChildren().clear();
                text = new Text("Students");
                textFlow.getChildren().addAll(text);
              }

              for (Student student : studentList) {
                List<Course> courseList = student.getCurriculum().getCurriculumCourseList();
                int coursesNum = student.getCurriculum().getCurriculumCourseList().size();

                if (newValue.getValue().equals(student.getFullName())) {
                  textFlow.getChildren().clear();
                  text = new Text(student.toString());
                  textFlow.getChildren().addAll(text);
                }

                if (newValue.getValue().equals(student.getCurriculum().getTitle())) {
                  textFlow.getChildren().clear();
                  text = new Text(student.getCurriculum().toString());
                  textFlow.getChildren().addAll(text);
                }

                for (int j = 0; j < coursesNum; j++) {
                  int tasksNum = courseList.get(j).getCourseTaskList().size();

                  if (newValue.getValue().equals(courseList.get(j).getTitle())) {
                    textFlow.getChildren().clear();
                    text = new Text(courseList.get(j).toString());
                    textFlow.getChildren().addAll(text);
                  }

                  for (int k = 0; k < tasksNum; k++) {
                    student
                        .getTaskResults()
                        .forEach(
                            ((task, s) -> {
                              if (newValue.getValue().equals(task.getTitle())) {
                                textFlow.getChildren().clear();
                                Text text2 = new Text(task.toString() + "\n" + s);
                                textFlow.getChildren().add(text2);
                              }
                            }));
                  }
                }
              }
            });
  }
}
