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

public class RegisterWindow {
  private Stage stage;
  public RegisterWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(300);
    stage.setHeight(600);

    StackPane layout = new StackPane();

    VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Label labelRegister = new Label("Registrieren");
    labelRegister.setStyle("-fx-font-size: 20px;");
    Label labelShortUserName = new Label("Kurzname");
    TextField textBoxShortUserName = new TextField();
    Label labelUserName = new Label("Benutzername");
    TextField textBoxUserName = new TextField();
    Label labelPassword = new Label("Passwort");
    TextField textBoxPassword = new TextField();
    Label labelPasswordRepeat = new Label("Passwort wiederholen");
    TextField textBoxPasswordRepeat = new TextField();

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
    layout.getChildren().add(vbox);

    Scene scene = new Scene(layout);
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
