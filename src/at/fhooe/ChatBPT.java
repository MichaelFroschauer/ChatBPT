package at.fhooe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

/*
Color Palette:
https://coolors.co/palette/353535-3c6e71-ffffff-d9d9d9-284b63

https://jenkov.com/tutorials/javafx/region.html
 */


public class ChatBPT extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    LoginWindow loginWindow = new LoginWindow();
    loginWindow.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
