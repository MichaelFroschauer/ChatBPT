package at.fhooe;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class ChatMessage {
  private String message;
  private String userId;
  private String userName;
  private String dateTime;
  public enum MessageType {
    MY_MESSAGE,
    OTHER_MESSAGE,
    SYSTEM_MESSAGE
  };

  MessageType messageType;

  public ChatMessage(String userShortName, String userName, String dateTime, MessageType type, String message) {
    this.userId = userShortName;
    this.userName = userName;
    this.dateTime = dateTime;
    this.messageType = type;
    this.message = message;
  }

  public HBox getMessageBox() {
    HBox msgBox = new HBox();
    msgBox.setSpacing(10);
    msgBox.setPadding(new Insets(8));

    Text usernameText = new Text(userName);
    usernameText.setFont(Font.font("Verdana", 14));
    usernameText.setFill(Color.WHITE);
    Text dateTimeText = new Text(dateTime);
    dateTimeText.setFont(Font.font("Verdana", 14));
    dateTimeText.setFill(Color.WHITE);

    BorderPane messageHead = new BorderPane();
    messageHead.setLeft(usernameText);
    messageHead.setRight(dateTimeText);
    messageHead.setPadding(new Insets(0, 15, 10, 0));

    Pane messageTextBox = new Pane();
    Text messageText = new Text(message);

    messageText.setFont(Font.font("Verdana", 16));
    messageText.wrappingWidthProperty().bind(messageTextBox.widthProperty());
    messageText.setFill(Color.WHITE);

    messageTextBox.prefWidthProperty().bind(msgBox.widthProperty());
    messageTextBox.getChildren().add(messageText);

    switch (messageType) {
      case MY_MESSAGE:
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(60,110,113),
                new CornerRadii(10),
                Insets.EMPTY)));
        break;
      case OTHER_MESSAGE:
        msgBox.setBackground(new Background(new BackgroundFill(
                Color.rgb(40,75,99),
                new CornerRadii(10),
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

    VBox message = new VBox(messageHead, messageTextBox);
    message.prefWidthProperty().bind(msgBox.widthProperty());
    message.setSpacing(10);
    msgBox.getChildren().addAll(message);

    return msgBox;
  }

  public MessageType getMessageType() {
    return messageType;
  }
  public boolean isMyMessage() {
    return messageType == MessageType.MY_MESSAGE;
  }
  public String getTextMessage() {
    return message;
  }
  public String getMessageUser() {
    return userName;
  }
}