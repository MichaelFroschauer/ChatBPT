package at.fhooe;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Objects;

public class ChatWindow {
  private final Stage stage;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();
  private ObservableList<ChatGroup> chatGroups;
  private ListView<ChatGroup> chatListItems;
  private VBox mainChatView;
  private HBox mainChatWindowCol;
  private boolean showChatSettings = false;
  private boolean showChatSearch = false;

  public ChatWindow() {
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(1000);
    stage.setHeight(600);
    stage.setMinWidth(920);
    stage.setMinHeight(520);

    mainChatWindowCol = new HBox();
    Rectangle rect = new Rectangle();
    rect.heightProperty().bind(stage.heightProperty());
    rect.setWidth(60);
    rect.getStyleClass().add("color-box");

    VBox chatSelection = initChatSelection();

    initMainChatView();
    updateMainChatView();

    mainChatWindowCol.getChildren().addAll(
            rect,
            chatSelection,
            mainChatView
    );

    Scene scene = new Scene(mainChatWindowCol);
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  private void initMainChatView() {
    mainChatView = new VBox();
    HBox.setHgrow(mainChatView, Priority.ALWAYS);
    mainChatView.fillWidthProperty();
    mainChatView.setPadding(new Insets(0, 5, 0, 0));
  }

  private void updateMainChatView() {

    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    if (selectedChat == null) {
      Text text = new Text("Öffne einen Chat um deine Nachrichten zu sehen.");
      // todo text zentrieren
      mainChatView.getChildren().clear();
      mainChatView.getChildren().add(text);
    } else {
      BorderPane mainChatHeader = drawMainChatHeader();

      mainChatView.getChildren().clear();
      if (showChatSettings) {

        VBox settingsButtons = new VBox();
        Button changeToPrivateChat = new Button("Zu privatem Chat wechseln");
        Button banMember = new Button("Mitglied bannen (entbannen)");
        Button deleteChat = new Button("Gruppenchat löschen");
        changeToPrivateChat.setMinWidth(250);
        banMember.setMinWidth(250);
        deleteChat.setMinWidth(250);
        deleteChat.getStyleClass().add("button-critical");
        settingsButtons.setSpacing(10);
        settingsButtons.getChildren().addAll(changeToPrivateChat, banMember, deleteChat);
        settingsButtons.setPadding(new Insets(20));
        settingsButtons.setAlignment(Pos.CENTER);

        VBox memberList = new VBox();
        ListView<Member> groupMemberList = new ListView<>();
        groupMemberList.setItems(dataManager.loadGroupChatMember(selectedChat.getChatName()));
        groupMemberList.setPadding(new Insets(20));
        memberList.getChildren().addAll(groupMemberList);
        memberList.setPadding(new Insets(20));
        memberList.setAlignment(Pos.CENTER);

        BorderPane settings = new BorderPane();
        settings.setLeft(settingsButtons);
        settings.setRight(memberList);
        mainChatView.getChildren().addAll(mainChatHeader, settings);
      } else if (showChatSearch) {



        mainChatView.getChildren().addAll(mainChatHeader);
      } else {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox content = new VBox();
        content.setSpacing(10);

        for (ChatMessage chatMessage : selectedChat.getMessages()) {
          HBox messageRectangle = chatMessage.getMessageBox();
          HBox chatMessageLine = new HBox(messageRectangle);
          chatMessageLine.prefWidthProperty().bind(mainChatView.widthProperty());
          chatMessageLine.setPadding(new Insets(5, 20, 5, 5));
          switch (chatMessage.getMessageType()) {
            case MY_MESSAGE:
              chatMessageLine.setAlignment(Pos.CENTER_RIGHT);
              break;
            case OTHER_MESSAGE:
              chatMessageLine.setAlignment(Pos.CENTER_LEFT);
              break;
            case SYSTEM_MESSAGE:
              chatMessageLine.setAlignment(Pos.CENTER);
              break;
          }
          content.getChildren().add(chatMessageLine);
        }
        scrollPane.setContent(content);
        scrollPane.setVvalue(Double.MAX_VALUE);
        scrollPane.prefHeightProperty().bind(mainChatView.heightProperty());

        HBox inputBox = drawMainChatInputBox();
        mainChatView.getChildren().addAll(mainChatHeader, scrollPane, inputBox);
      }
    }
  }

  private BorderPane drawMainChatHeader() {
    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    Label headerLabel = new Label(selectedChat.toSingleLineString());
    HBox buttonsBox;
    if (showChatSearch || showChatSettings) {
      Button exitButton = new Button("Exit");
      exitButton.setMinWidth(100);
      exitButton.setOnAction((event -> {
        showChatSearch = false;
        showChatSettings = false;
        updateMainChatView();
      }));
      buttonsBox = new HBox(exitButton);
    } else {
      Button searchButton = new Button("Search");
      searchButton.setMinWidth(100);
      searchButton.setOnAction((event -> {
        showChatSearch = true;
        updateMainChatView();
      }));

      Button settingButton = new Button("Settings");
      settingButton.setMinWidth(100);
      settingButton.setOnAction((event -> {
        showChatSettings = true;
        updateMainChatView();
      }));
      buttonsBox = new HBox(searchButton, settingButton);
    }

    buttonsBox.setAlignment(Pos.CENTER_RIGHT);

    BorderPane mainChatHeader = new BorderPane();
    mainChatHeader.setCenter(headerLabel);
    mainChatHeader.setRight(buttonsBox);
    BorderPane.setMargin(mainChatHeader, new Insets(10));
    mainChatHeader.setMinHeight(40);
    return mainChatHeader;
  }

  private HBox drawMainChatInputBox() {
    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    HBox inputBox = new HBox();
    inputBox.setSpacing(10);
    inputBox.setPadding(new Insets(10));
    inputBox.setMinHeight(100);
    inputBox.setAlignment(Pos.CENTER);
    inputBox.prefWidthProperty().bind(mainChatView.widthProperty());
    inputBox.setPadding(new Insets(5));
    TextField textField = new TextField();
    textField.setPrefHeight(inputBox.getPrefHeight());
    textField.setMinHeight(80);
    textField.setPrefColumnCount(3);
    textField.prefWidthProperty().bind(inputBox.widthProperty());
    textField.setPadding(new Insets(5));
    Button sendButton = new Button("Send");
    sendButton.setMinWidth(100);
    sendButton.setOnAction((event) -> {
      selectedChat.addChatMessage(textField.getText());
      textField.setText("");
      updateMainChatView();
    });
    inputBox.getChildren().addAll(textField, sendButton);
    return inputBox;
  }

  private VBox initChatSelection() {
    Label chatsTitle = new Label("Chats");
    chatsTitle.getStyleClass().addAll("label", "label-title");
    chatsTitle.setId("label-title-chats");
    chatsTitle.setPadding(new Insets(10));
    Button newChatBtn = new Button("+");
    newChatBtn.getStyleClass().add("button");
    newChatBtn.setPadding(new Insets(10, 40, 10, 10));
    newChatBtn.setOnAction(event -> {
      // TODO new chat window
      NewChatWindow newChatWindow = new NewChatWindow();
      newChatWindow.show();
    });
    HBox chatTitleCol = new HBox(chatsTitle, newChatBtn);
    chatTitleCol.setId("chatTitleHbox");

    chatGroups = dataManager.loadChatGroups();
    chatListItems = createChatList();
    chatListItems.setId("chatList");
    chatListItems.prefHeightProperty().bind(stage.heightProperty());
    VBox chatSelection = new VBox(chatTitleCol, chatListItems);
    chatSelection.setId("chatSelection");

    chatListItems.getSelectionModel()
            .selectedItemProperty()
            .addListener((ov) ->  updateMainChatView());

    return chatSelection;
  }

  private ListView<ChatGroup> createChatList() {
    ListView<ChatGroup> chatList = new ListView<>();
    chatList.setId("chatListCell");
    chatList.setItems(chatGroups);
    chatList.setFixedCellSize(70);
    return chatList;
  }

  public void show() {
    stage.show();
  }
}
