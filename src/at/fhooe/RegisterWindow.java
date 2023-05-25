package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class RegisterWindow {
  private Stage stage;
  VBox windowLayout;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();

  public RegisterWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(360);
    stage.setHeight(510);
    stage.getIcons().add(new Image("appIcon.png"));

    windowLayout = new VBox(10);
    windowLayout.setPadding(new Insets(20));
    windowLayout.setAlignment(Pos.CENTER_LEFT);

    Label labelRegister = new Label("Registrieren");
    labelRegister.getStyleClass().addAll("label", "label-title");

    Label labelShortUserName = new Label("Kurzname");
    labelShortUserName.getStyleClass().add("label");
    TextField textBoxShortUserName = new TextField();
    textBoxShortUserName.getStyleClass().add("textbox");
    VBox shortNameInput = new VBox(labelShortUserName, textBoxShortUserName);

    Label labelUserName = new Label("Benutzername");
    labelUserName.getStyleClass().add("label");
    TextField textBoxUserName = new TextField();
    textBoxUserName.getStyleClass().add("textbox");
    VBox userNameInput = new VBox(labelUserName, textBoxUserName);

    Label labelPassword = new Label("Passwort");
    labelPassword.getStyleClass().add("label");
    PasswordField textBoxPassword = new PasswordField();
    textBoxPassword.getStyleClass().add("textbox");
    VBox passwordInput = new VBox(labelPassword, textBoxPassword);

    Label labelPasswordRepeat = new Label("Passwort wiederholen");
    labelPasswordRepeat.getStyleClass().add("label");
    PasswordField textBoxPasswordRepeat = new PasswordField();
    textBoxPasswordRepeat.getStyleClass().add("textbox");
    VBox passwordRepeatInput = new VBox(labelPasswordRepeat, textBoxPasswordRepeat);


    Button btnRegister = new Button("Registrieren");
    btnRegister.prefWidthProperty().bind(stage.widthProperty());
    btnRegister.setOnAction(event -> register(
            textBoxShortUserName.getText(),
            textBoxUserName.getText(),
            textBoxPassword.getText(),
            textBoxPasswordRepeat.getText()
    ));


    windowLayout.getChildren().addAll(
            labelRegister,
            shortNameInput,
            userNameInput,
            passwordInput,
            passwordRepeatInput,
            btnRegister
    );

    Scene scene = new Scene(windowLayout);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void register(String shortName, String userName, String password, String passwordRepeat) {
    String helpText = "";
    if (shortName.equals("") || userName.equals("") || password.equals("") || passwordRepeat.equals("")) {
      helpText = "Keine Gültige Eingabe.";
    } else if (!password.equals(passwordRepeat)) {
      helpText = "Passwörter nicht gleich.";
    } else if (dataManager.memberExists(shortName)) {
      helpText = "Benutzer existiert bereits. Kurzname soll eindeutig sein.";
    } else {
      // Todo: change to password hash when saved in a db
      String passwordHash = password;
      dataManager.addNewMember(shortName, userName, passwordHash);

      LoginWindow loginWindow = new LoginWindow();
      loginWindow.show();
      stage.close();
      return;
    }

    Node lastChild = windowLayout.getChildren().get(windowLayout.getChildren().size() - 1);
    if (lastChild instanceof Text) {
      ((Text) lastChild).setText(helpText);
    } else {
      windowLayout.getChildren().add(new Text(helpText));
    }
  }
}
