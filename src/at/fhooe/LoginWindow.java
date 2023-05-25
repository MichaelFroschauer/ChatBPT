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

import java.util.List;
import java.util.Objects;

public class LoginWindow {

  private Stage stage;
  VBox windowLayout;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();

  public LoginWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(360);
    stage.setHeight(400);
    stage.getIcons().add(new Image("appIcon.png"));
    stage.setResizable(false);

    windowLayout = new VBox(10);
    windowLayout.setPadding(new Insets(20));
    windowLayout.setAlignment(Pos.CENTER_LEFT);

    Label labelLogin = new Label("Login");
    labelLogin.getStyleClass().addAll("label", "label-title");

    Label labelShortUserName = new Label("Benutzer");
    labelShortUserName.getStyleClass().add("label");
    TextField textBoxShortUserName = new TextField();
    textBoxShortUserName.getStyleClass().add("textbox");
    VBox userInput = new VBox(labelShortUserName, textBoxShortUserName);

    Label labelPassword = new Label("Passwort");
    labelPassword.getStyleClass().add("label");
    PasswordField textBoxPassword = new PasswordField();
    textBoxPassword.getStyleClass().add("textbox");
    VBox passwordInput = new VBox(labelPassword, textBoxPassword);

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

    windowLayout.getChildren().addAll(
            labelLogin,
            userInput,
            passwordInput,
            btnLogin,
            btnRegister);

    Scene scene = new Scene(windowLayout);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void login(String userName, String password) {
    Member loginMember = null;
    List<Member> members = dataManager.getRegisteredMember().stream()
            .filter(m -> m.getShortName().equals(userName) && m.getPasswordHash().equals(password))
            .toList();

    if (members.size() == 1) {
      loginMember = members.get(0);
      ChatWindow chatWindow = new ChatWindow(loginMember);
      chatWindow.show();
      stage.close();
    } else {
      Node lastChild = windowLayout.getChildren().get(windowLayout.getChildren().size() - 1);
      if (!(lastChild instanceof Text)) {
        Text text = new Text("Falscher Benutzername oder Passwort.");
        windowLayout.getChildren().addAll(text);
      }
    }
  }

  private void register() {
    stage.close();
    RegisterWindow registerWindow = new RegisterWindow();
    registerWindow.show();
  }
}
