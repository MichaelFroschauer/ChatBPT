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

import java.util.Objects;

public class RegisterWindow {
  private Stage stage;
  public RegisterWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(300);
    stage.setHeight(550);

    VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Label labelRegister = new Label("Registrieren");
    labelRegister.getStyleClass().addAll("label", "label-title");

    Label labelShortUserName = new Label("Kurzname");
    labelShortUserName.getStyleClass().add("label");

    TextField textBoxShortUserName = new TextField();
    textBoxShortUserName.getStyleClass().add("textbox");

    Label labelUserName = new Label("Benutzername");
    labelUserName.getStyleClass().add("label");

    TextField textBoxUserName = new TextField();
    textBoxUserName.getStyleClass().add("textbox");

    Label labelPassword = new Label("Passwort");
    labelPassword.getStyleClass().add("label");

    TextField textBoxPassword = new TextField();
    textBoxPassword.getStyleClass().add("textbox");

    Label labelPasswordRepeat = new Label("Passwort wiederholen");
    labelPasswordRepeat.getStyleClass().add("label");

    TextField textBoxPasswordRepeat = new TextField();
    textBoxPasswordRepeat.getStyleClass().add("textbox");


    Button btnRegister = new Button("Registrieren");
    btnRegister.prefWidthProperty().bind(stage.widthProperty());
    btnRegister.setOnAction(event -> register(
            textBoxShortUserName.getText(),
            textBoxUserName.getText(),
            textBoxPassword.getText(),
            textBoxPasswordRepeat.getText()
    ));


    vbox.getChildren().addAll(
            labelRegister,
            labelShortUserName,
            textBoxShortUserName,
            labelUserName,
            textBoxUserName,
            labelPassword,
            textBoxPassword,
            labelPasswordRepeat,
            textBoxPasswordRepeat,
            btnRegister
    );

    Scene scene = new Scene(vbox);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void register(String shortName, String userName, String password, String passwordRepeat) {
    System.out.println("Kurzname: " + shortName);
    System.out.println("Benutzername: " + userName);
    System.out.println("Passwort: " + password);
    System.out.println("Passwort Wiederholung: " + passwordRepeat);
  }
}
