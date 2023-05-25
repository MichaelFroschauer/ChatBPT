package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class NewChatWindow {
  private Stage stage;
  private String loggedInUser;

  public NewChatWindow(String userName) {
    loggedInUser = userName;
    stage = new Stage();
    stage.setTitle("Neuer Chat");
    stage.setWidth(300);
    stage.setHeight(260);
    stage.getIcons().add(new Image("appIcon.png"));

    VBox vbox = new VBox(25);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Button newChatGroupButton = new Button("Neue Chatgruppe erstellen");
    newChatGroupButton.getStyleClass().add("button");
    newChatGroupButton.prefWidthProperty().bind(stage.widthProperty());
    newChatGroupButton.setOnAction(event -> {
      NewChatGroupWindow newChatGroupWindow = new NewChatGroupWindow(loggedInUser, false);
      newChatGroupWindow.show();
      stage.close();
    });

    Button joinChatGroupButton = new Button("Chatgruppe beitreten");
    joinChatGroupButton.getStyleClass().add("button");
    joinChatGroupButton.prefWidthProperty().bind(stage.widthProperty());
    joinChatGroupButton.setOnAction(event -> {
      NewChatGroupWindow joinChatGroupWindow = new NewChatGroupWindow(loggedInUser, true);
      joinChatGroupWindow.show();
      stage.close();
    });

    Button newPrivateChatButton = new Button("Freunde finden");
    newPrivateChatButton.getStyleClass().add("button");
    newPrivateChatButton.prefWidthProperty().bind(stage.widthProperty());
    newPrivateChatButton.setOnAction(event -> {
      FindFriendsWindow findFriendsWindow = new FindFriendsWindow(loggedInUser);
      findFriendsWindow.show();
      stage.close();
    });

    vbox.getChildren().addAll(
            newChatGroupButton,
            joinChatGroupButton,
            newPrivateChatButton);

    Scene scene = new Scene(vbox);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }
}

