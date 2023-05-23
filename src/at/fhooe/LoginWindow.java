package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginWindow {

  private Stage stage;

  public LoginWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(380);
    stage.setHeight(350);

    VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Label labelLogin = new Label("Login");
    labelLogin.getStyleClass().addAll("label", "label-title");

    // TODO change to CSS as desciribed in https://docs.oracle.com/javafx/2/text/jfxpub-text.htm

    Label labelShortUserName = new Label("Benutzer");
    labelShortUserName.getStyleClass().add("label");

    TextField textBoxShortUserName = new TextField();
    textBoxShortUserName.getStyleClass().add("textbox");

    Label labelPassword = new Label("Passwort");
    labelPassword.getStyleClass().add("label");

    TextField textBoxPassword = new TextField();
    textBoxPassword.getStyleClass().add("textbox");

    Button btnLogin = new Button("Login");
    btnLogin.getStyleClass().add("button");
    btnLogin.prefWidthProperty().bind(stage.widthProperty());
    btnLogin.setOnAction((event) -> {
      login(textBoxShortUserName.getText(), textBoxPassword.getText());
    });

    Button btnRegister = new Button("Account erstellen");
    btnRegister.getStyleClass().add("button");
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

    Scene scene = new Scene(vbox);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void login(String userName, String password) {

    ChatWindow chatWindow = new ChatWindow();
    chatWindow.show();
    stage.close();
  }

  private void register() {
    stage.close();
    RegisterWindow registerWindow = new RegisterWindow();
    registerWindow.show();
  }
}
