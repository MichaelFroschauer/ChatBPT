package at.fhooe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
Color Palette:
https://coolors.co/palette/353535-3c6e71-ffffff-d9d9d9-284b63

 */


public class ChatBPT extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Chat-BPT");
    Button btn = new Button();
    btn.setText("Say 'Hello World'");

    LoginWindow loginWindow = new LoginWindow();
    loginWindow.show();
    btn.setOnAction(event -> {
      loginWindow.show();
    });

    StackPane root = new StackPane();
    root.getChildren().add(btn);
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
