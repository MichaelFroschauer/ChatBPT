package at.fhooe;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ChatMessage {
  private String message;
  private String user;
  private String dateTime;
  public enum MessageType {
    MY_MESSAGE,
    OTHER_MESSAGE,
    SYSTEM_MESSAGE
  };

  MessageType messageType;

  public ChatMessage(String user, String dateTime, MessageType type, String message) {
    this.user = user;
    this.dateTime = dateTime;
    this.messageType = type;
    this.message = message;
  }

  public HBox getMessageBox() {
    HBox msgBox = new HBox();
    msgBox.setSpacing(10);
    msgBox.setPadding(new Insets(10));

    Text usernameText = new Text(user);
    usernameText.setFont(Font.font("Arial", 14));
    usernameText.setFill(Color.WHITE);
    Text dateTimeText = new Text(dateTime);
    dateTimeText.setFont(Font.font("Arial", 14));
    dateTimeText.setFill(Color.WHITE);

    BorderPane messageHead = new BorderPane();
    messageHead.setLeft(usernameText);
    messageHead.setRight(dateTimeText);
    Text messageText = new Text(message);
    messageText.setFont(Font.font("Arial", 16));
    messageText.setWrappingWidth(500);
    messageText.setFill(Color.WHITE);
    messageHead.setPadding(new Insets(0, 0, 8, 0));

    switch (messageType) {
      case MY_MESSAGE:
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(60,110,113),
                new CornerRadii(5),
                Insets.EMPTY)));
        break;
      case OTHER_MESSAGE:
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(40,75,99),
                new CornerRadii(5),
                Insets.EMPTY)));
        break;
      case SYSTEM_MESSAGE:
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.YELLOW,
                new CornerRadii(5),
                Insets.EMPTY)));
        msgBox.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        usernameText.setFill(Color.BLACK);
        dateTimeText.setFill(Color.BLACK);
        messageText.setFill(Color.BLACK);
        break;
      default:
        // should not occur
    }

    VBox message = new VBox(messageHead, messageText);
    msgBox.getChildren().addAll(message);

    return msgBox;
  }

  public MessageType getMessageType() {
    return messageType;
  }
  public boolean isMyMessage() {
    return messageType == MessageType.MY_MESSAGE;
  }
}