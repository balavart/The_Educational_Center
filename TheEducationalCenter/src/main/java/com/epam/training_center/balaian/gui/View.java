package com.epam.training_center.balaian.gui;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The type View.
 *
 * @author Vardan_Balaian
 * @version 1.8
 * @since 12/18/2019
 */
public class View extends Application {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root =
        FXMLLoader.load(
            Objects.requireNonNull(getClass().getClassLoader().getResource("winfx.fxml")));
    primaryStage.setTitle("The Educational Center Information");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
