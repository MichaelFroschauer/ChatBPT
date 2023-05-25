package at.fhooe;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Objects;

public class ChatWindow {
  private final Stage stage;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();
  private ObservableList<ChatGroup> chatGroups;
  private ListView<ChatGroup> chatListItems;
  private VBox mainChatView;
  private ScrollPane mainChatScrollPane;
  private boolean showChatSettings = false;
  private boolean showChatSearch = false;
  private final Member loggedInUser;
  private TextField searchTextField;

  public ChatWindow(Member user) {
    loggedInUser = user;
    stage = new Stage();
    stage.setTitle("Chat-BPT");
    stage.setWidth(1000);
    stage.setHeight(600);
    stage.setMinWidth(920);
    stage.setMinHeight(520);
    stage.getIcons().add(new Image("appIcon.png"));

    HBox mainChatWindowCol = new HBox();
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
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  public void show() {
    stage.show();
  }

  private void initMainChatView() {
    mainChatView = new VBox();
    HBox.setHgrow(mainChatView, Priority.ALWAYS);
    mainChatView.fillWidthProperty();
    mainChatView.setPadding(new Insets(0, 5, 0, 0));
    mainChatScrollPane = new ScrollPane();
    mainChatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    mainChatScrollPane.prefHeightProperty().bind(mainChatView.heightProperty());
  }

  private void updateMainChatView() {
    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    if (selectedChat == null) {
      Text text = new Text("Öffne einen Chat um deine Nachrichten zu sehen.");
      mainChatView.setAlignment(Pos.CENTER);
      mainChatView.getChildren().clear();
      mainChatView.getChildren().add(text);
    } else {
      BorderPane mainChatHeader = drawMainChatHeader();
      mainChatView.setAlignment(Pos.TOP_LEFT);
      mainChatView.getChildren().clear();
      if (showChatSettings) {
        HBox settings = drawSettingsPane();
        mainChatView.getChildren().addAll(mainChatHeader, settings);
      } else {
        if (showChatSearch) {
          HBox searchBox = drawMainChatSearchBox();
          updateMainViewChatMessages();
          mainChatView.getChildren().addAll(mainChatHeader, mainChatScrollPane, searchBox);
        } else {
          updateMainViewChatMessages();
          HBox inputBox = drawMainChatInputBox();
          mainChatView.getChildren().addAll(mainChatHeader, mainChatScrollPane, inputBox);
        }
      }
    }
  }

  private void updateMainViewChatMessages() {
    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    VBox content = new VBox();
    content.setSpacing(10);
    for (ChatMessage chatMessage : selectedChat.getMessages()) {
      if (!showChatSearch || (searchTextField != null &&
              chatMessage.getTextMessage().toLowerCase().contains(
                      searchTextField.getText().toLowerCase()))) {
        HBox messageRectangle = chatMessage.getMessageBox();
        HBox chatMessageLine = new HBox(messageRectangle);
        chatMessageLine.prefWidthProperty().bind(mainChatView.widthProperty());
        chatMessageLine.setPadding(new Insets(5, 20, 8, 5));
        messageRectangle.minWidthProperty().bind(mainChatView.widthProperty().multiply(0.6));
        messageRectangle.maxWidthProperty().bind(mainChatView.widthProperty().multiply(0.6));

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
    }
    mainChatScrollPane.setContent(content);
    content.heightProperty().addListener(observable -> mainChatScrollPane.setVvalue(1D));
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
    TextArea textField = new TextArea();
    textField.setPrefHeight(inputBox.getPrefHeight());
    textField.setMinHeight(80);
    textField.setPrefColumnCount(3);
    textField.prefWidthProperty().bind(inputBox.widthProperty());
    textField.setPadding(new Insets(5));
    textField.getStyleClass().add("textarea");
    textField.setWrapText(true);
    Button sendButton = new Button("Send");
    sendButton.setMinWidth(100);
    sendButton.setOnAction((event) -> {
      if (!textField.getText().isEmpty()) {
        selectedChat.addChatMessage(
                loggedInUser.getShortName(),
                loggedInUser.getUserName(),
                textField.getText().trim());
        textField.setText("");
        updateMainViewChatMessages();
      }
    });
    textField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER && !textField.getText().trim().isEmpty()) {
        sendButton.fire();
      } else if (textField.getText().trim().isEmpty()) {
        textField.setText("");
      }
    });
    inputBox.getChildren().addAll(textField, sendButton);
    return inputBox;
  }

  private HBox drawSettingsPane() {
    ChatGroup selectedChat = chatListItems.getSelectionModel().getSelectedItem();
    HBox settings;

    if (selectedChat.isPrivate()) {
      Button deleteChat = new Button("Privatchat löschen");
      deleteChat.setMinWidth(250);
      deleteChat.getStyleClass().add("button-critical");
      settings = new HBox(deleteChat);
    } else {
      VBox settingsButtons = new VBox();
      Button changeToPrivateChat = new Button("Zu privatem Chat wechseln");
      changeToPrivateChat.setMinWidth(250);

      Button banMember = new Button("Mitglied bannen (entbannen)");
      banMember.setMinWidth(250);

      Button deleteChat = new Button("Gruppenchat löschen");
      deleteChat.setMinWidth(250);
      deleteChat.getStyleClass().add("button-critical");

      settingsButtons.setSpacing(10);
      settingsButtons.getChildren().addAll(changeToPrivateChat, banMember, deleteChat);
      settingsButtons.setPadding(new Insets(20));
      settingsButtons.setAlignment(Pos.CENTER);

      VBox memberList = new VBox();
      ListView<Member> groupMemberList = new ListView<>();
      groupMemberList.setItems(selectedChat.getGroupMemberAsList());
      groupMemberList.setPadding(new Insets(20));
      groupMemberList.setCellFactory(listView -> new ListCell<Member>() {
        @Override
        protected void updateItem(Member member, boolean empty) {
          super.updateItem(member, empty);
          setText(empty || member == null ? null : member.toString());
          if (member != null && selectedChat.getBannedGroupMemberAsList().contains(member)) {
            setText(getText() + " (banned)");
          } else if (member != null && selectedChat.getChatGroupAdmin().equals(member.getShortName())) {
            setText(getText() + " (ADMIN)");
          }
        }
      });

      memberList.getChildren().addAll(groupMemberList);
      memberList.setPadding(new Insets(20));
      memberList.setAlignment(Pos.CENTER);

      changeToPrivateChat.setOnAction(event -> {
        Member selectedUser = groupMemberList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
          getToPrivateChat(selectedUser);
        }
      });
      banMember.setOnAction(event -> {
        Member selectedUser = groupMemberList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
          banOrUnbanUser(selectedUser);
          updateMainChatView();
        }
      });
      deleteChat.setOnAction(event -> {
        showChatSettings = false;
        if (selectedChat.getChatGroupAdmin().equals(loggedInUser.getShortName()) ||
                selectedChat.isPrivate()) {
          dataManager.deleteGroupChat(selectedChat.getGroupName());
        }
        updateMainChatView();
      });
      groupMemberList.getSelectionModel().getSelectedItem();

      settings = new HBox(settingsButtons, memberList);
    }

    settings.setAlignment(Pos.CENTER);
    settings.setFillHeight(true);
    settings.prefHeightProperty().bind(mainChatView.heightProperty());
    return settings;
  }

  private HBox drawMainChatSearchBox() {
    searchTextField = new TextField();
    searchTextField.prefWidthProperty().bind(mainChatView.widthProperty());
    searchTextField.setFont(Font.font("Verdana", 16));
    searchTextField.setPadding(new Insets(5, 10, 5, 10));
    searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      updateMainViewChatMessages();
    });
    HBox searchField = new HBox(searchTextField);
    searchField.prefWidthProperty().bind(mainChatView.widthProperty());
    searchField.setPadding(new Insets(40, 60, 40, 60));
    searchField.setMinHeight(50);
    searchField.setAlignment(Pos.CENTER);

    return searchField;
  }

  private VBox initChatSelection() {
    Label chatsTitle = new Label("Chats from " + loggedInUser.getUserName());
    chatsTitle.getStyleClass().addAll("label", "label-title");
    chatsTitle.setId("label-title-chats");
    chatsTitle.setPadding(new Insets(10));
    Button newChatBtn = new Button("+");
    newChatBtn.getStyleClass().add("button");
    newChatBtn.setFont(Font.font("Verdana", 20));
    newChatBtn.setAlignment(Pos.CENTER);
    newChatBtn.setOnAction(event -> {
      NewChatWindow newChatWindow = new NewChatWindow(loggedInUser.getShortName());
      newChatWindow.show();
    });
    BorderPane chatListTitle = new BorderPane();
    chatListTitle.setLeft(chatsTitle);
    chatListTitle.setRight(newChatBtn);
    BorderPane.setAlignment(newChatBtn, Pos.CENTER_RIGHT);
    BorderPane.setMargin(newChatBtn, new Insets(0,10,0,0));

    updateChatGroupList();
    chatListItems = new ListView<>();
    chatListItems.setId("chatList");
    chatListItems.setItems(chatGroups);
    chatListItems.setFixedCellSize(70);
    chatListItems.prefHeightProperty().bind(stage.heightProperty());
    VBox chatSelection = new VBox(chatListTitle, chatListItems);
    chatSelection.setId("chatSelection");
    chatSelection.minWidthProperty().bind(stage.widthProperty().multiply(0.3));
    chatSelection.maxWidthProperty().bind(stage.widthProperty().multiply(0.6));

    chatListItems.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldValue, newValue) ->  {
              if (oldValue != null) {
                oldValue.storeNewMessages();
              }
              updateMainChatView();
            });
    chatListItems.setCellFactory(listView -> new ListCell<>() {
      @Override
      protected void updateItem(ChatGroup chatGroup, boolean empty) {
        super.updateItem(chatGroup, empty);
        if (empty || chatGroup == null) {
          setText("Gruppe nicht gefunden");
        } else {
          setText(chatGroup.toString());
          setFont(Font.font("Verdana", 16));
          setPadding(new Insets(0, 0, 0, 20));
        }
      }
    });

    return chatSelection;
  }

  private void updateChatGroupList() {
    chatGroups = dataManager.loadChatGroupListForMember(loggedInUser.getShortName());
    for (ChatGroup cg : chatGroups) {
      cg.loadChatGroup();
    }
  }

  private void banOrUnbanUser(Member selectedUser) {
    ChatGroup selectedChatGroup = chatListItems.getSelectionModel().getSelectedItem();
    selectedChatGroup.banOrUnbanUser(selectedUser, loggedInUser);
  }

  private void getToPrivateChat(Member user) {
    if (!dataManager.privateChatExists(user.getShortName())){
      dataManager.createNewPrivateChat(user.getShortName());
    }
    int index = 0;
    for (ChatGroup chat : chatListItems.getItems()) {
      if (chat.getGroupName().equals(user.getShortName())) {
        break;
      }
      index++;
    }
    chatListItems.getSelectionModel().select(index);
    chatListItems.getFocusModel().focus(index);
    chatListItems.scrollTo(index);
    showChatSettings = false;
    updateMainChatView();
  }
}
