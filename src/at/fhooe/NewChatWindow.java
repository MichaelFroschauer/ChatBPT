package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

public class NewChatWindow {
  private Stage stage;

  public NewChatWindow() {
    stage = new Stage();
    stage.setTitle("Neuer Chat");
    stage.setWidth(380);
    stage.setHeight(350);

    VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20));
    vbox.setAlignment(Pos.CENTER_LEFT);

    Button newChatGroupButton = new Button("Neue Chatgruppe erstellen");
    newChatGroupButton.getStyleClass().add("button");
    newChatGroupButton.prefWidthProperty().bind(stage.widthProperty());
    newChatGroupButton.setOnAction(event -> {

    });

    Button joinChatGroupButton = new Button("Chatgruppe beitreten");
    joinChatGroupButton.getStyleClass().add("button");
    joinChatGroupButton.prefWidthProperty().bind(stage.widthProperty());
    joinChatGroupButton.setOnAction(event -> {

    });

    Button newPrivateChatButton = new Button("Freunde finden");
    newPrivateChatButton.getStyleClass().add("button");
    newPrivateChatButton.prefWidthProperty().bind(stage.widthProperty());
    newPrivateChatButton.setOnAction(event -> {

    });

    vbox.getChildren().addAll(
            newChatGroupButton,
            joinChatGroupButton,
            newPrivateChatButton);

    Scene scene = new Scene(vbox);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }
}

