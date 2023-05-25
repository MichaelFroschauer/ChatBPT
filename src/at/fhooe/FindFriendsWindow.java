package at.fhooe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class FindFriendsWindow {
  private Stage stage;
  VBox windowLayout;
  private String loggedInUser;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();
  private ObservableList<Member> registeredMembers;
  private ListView<Member> registerdMemberList;

  public FindFriendsWindow(String userShortName) {
    loggedInUser = userShortName;
    registeredMembers = dataManager.getRegisteredMember();
    stage = new Stage();
    stage.setTitle("Freunde finden");
    stage.setWidth(480);
    stage.setHeight(600);
    stage.getIcons().add(new Image("appIcon.png"));
    stage.setResizable(false);

    windowLayout = new VBox(10);
    windowLayout.setPadding(new Insets(20));
    windowLayout.setAlignment(Pos.CENTER);

    Label labelShortUserName = new Label("Kurzname");
    labelShortUserName.getStyleClass().addAll("label", "label-title");
    labelShortUserName.setMinWidth(180);
    TextField textBoxShortUserName = new TextField();
    textBoxShortUserName.getStyleClass().add("textbox");
    HBox titleAndInput = new HBox(labelShortUserName, textBoxShortUserName);
    titleAndInput.setSpacing(10);
    titleAndInput.prefWidthProperty().bind(windowLayout.widthProperty());
    textBoxShortUserName.prefWidthProperty().bind(windowLayout.widthProperty());
    textBoxShortUserName.textProperty().addListener((observable, oldValue, newValue) -> {
      updateFriendsList(textBoxShortUserName.getText());
    });

    titleAndInput.setAlignment(Pos.CENTER_RIGHT);

    VBox memberList = new VBox();
    registerdMemberList = new ListView<>();
    updateFriendsList("");
    registerdMemberList.setPadding(new Insets(20));
    memberList.getChildren().addAll(registerdMemberList);
    memberList.setPadding(new Insets(20));
    memberList.setAlignment(Pos.CENTER);

    Button btnAddFriend = new Button();
    btnAddFriend.setText("Freund hinzufügen");
    btnAddFriend.prefWidthProperty().bind(windowLayout.widthProperty());
    btnAddFriend.setOnAction(event -> addFriend());


    windowLayout.getChildren().addAll(
            titleAndInput,
            memberList,
            btnAddFriend
    );

    Scene scene = new Scene(windowLayout);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  private void updateFriendsList(String pattern) {
    ObservableList<Member> filteredMembers = FXCollections.observableList(registeredMembers.stream()
            .filter(member -> (member.getShortName().toLowerCase().contains(pattern.toLowerCase()) ||
                               member.getUserName().toLowerCase().contains(pattern.toLowerCase())) &&
                              !dataManager.privateChatExists(member.getShortName()) &&
                              !member.getShortName().equals(loggedInUser))
            .toList());
    registerdMemberList.setItems(filteredMembers);
  }

  private void addFriend() {
    Member selectedMember = registerdMemberList.getSelectionModel().getSelectedItem();
    if (selectedMember != null) {
      dataManager.createNewPrivateChat(selectedMember.getShortName());
      updateFriendsList("");
      stage.close();
    }
  }

  public void show() {
    stage.show();
  }
}
