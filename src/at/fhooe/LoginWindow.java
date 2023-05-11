package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {

  private Stage stage;

  public LoginWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(200);
    stage.setHeight(300);

    StackPane layout = new StackPane();

    VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Label labelLogin = new Label("Login");
    labelLogin.setStyle("-fx-font-size: 20px;");

    Label labelShortUserName = new Label("Benutzer");
    TextField textBoxShortUserName = new TextField();
    Label labelPassword = new Label("Passwort");
    TextField textBoxPassword = new TextField();

    Button btnLogin = new Button("Login");
    btnLogin.prefWidthProperty().bind(stage.widthProperty());
    btnLogin.setOnAction(event -> login(textBoxShortUserName.getText(), textBoxPassword.getText()));
    Button btnRegister = new Button("Account erstellen");
    btnRegister.prefWidthProperty().bind(stage.widthProperty());
    btnRegister.setOnAction(event -> register());

    vbox.getChildren().addAll(
            labelLogin,
            labelShortUserName,
            textBoxShortUserName,
            labelPassword,
            textBoxPassword,
            btnLogin,
            btnRegister);
    layout.getChildren().add(vbox);

    Scene scene = new Scene(layout);
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void login(String userName, String password) {

  }

  private void register() {
    stage.close();
    RegisterWindow registerWindow = new RegisterWindow();
    registerWindow.show();
  }
}
