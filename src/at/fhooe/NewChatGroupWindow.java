package at.fhooe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class NewChatGroupWindow {
  private Stage stage;
  VBox windowLayout;
  private String loggedInUser;
  private final ChatMockDataManager dataManager = ChatMockDataManager.getInstance();

  public NewChatGroupWindow(String userShortName, boolean joinExistingGroup) {
    loggedInUser = userShortName;
    stage = new Stage();
    stage.setWidth(480);
    stage.setHeight(200);
    stage.getIcons().add(new Image("appIcon.png"));

    windowLayout = new VBox(10);
    windowLayout.setPadding(new Insets(20));
    windowLayout.setAlignment(Pos.CENTER);

    Label labelGroupName = new Label("Gruppenname:");
    labelGroupName.getStyleClass().addAll("label", "label-title");
    labelGroupName.setMinWidth(180);
    TextField textBoxGroupName = new TextField();
    textBoxGroupName.getStyleClass().add("textbox");
    HBox titleAndInput = new HBox(labelGroupName, textBoxGroupName);
    titleAndInput.setSpacing(10);
    titleAndInput.prefWidthProperty().bind(windowLayout.widthProperty());
    textBoxGroupName.prefWidthProperty().bind(windowLayout.widthProperty());
    titleAndInput.setAlignment(Pos.CENTER_RIGHT);

    Button btnCreateNewGroup = new Button();
    btnCreateNewGroup.prefWidthProperty().bind(windowLayout.widthProperty());

    VBox groupNameInput = new VBox(titleAndInput, btnCreateNewGroup);
    groupNameInput.setAlignment(Pos.CENTER_RIGHT);
    groupNameInput.setSpacing(10);

    if (joinExistingGroup) {
      stage.setTitle("Chatgruppe beitreten");
      btnCreateNewGroup.setText("Beitreten");
      btnCreateNewGroup.setOnAction(event -> joinChatGroup(textBoxGroupName.getText()));
    } else {
      stage.setTitle("Neue Chatgruppe");
      btnCreateNewGroup.setText("Erstellen");
      btnCreateNewGroup.setOnAction(event -> createNewChatGroup(textBoxGroupName.getText()));
    }



      windowLayout.getChildren().addAll(
            groupNameInput
    );

    Scene scene = new Scene(windowLayout);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/style.css")).toExternalForm());
    stage.setScene(scene);
  }

  private void joinChatGroup(String groupName) {
    if (dataManager.groupExists(groupName)) {
      dataManager.userEntersGroup(groupName, loggedInUser);
      stage.close();
    } else {
      Node lastChild = windowLayout.getChildren().get(windowLayout.getChildren().size() - 1);
      if (!(lastChild instanceof Text)) {
        Text text = new Text("Gruppe nicht gefunden.");
        windowLayout.getChildren().addAll(text);
      }
    }
  }

  private void createNewChatGroup(String groupName) {
    if (!dataManager.groupExists(groupName)) {
      dataManager.createNewGroupChat(groupName, loggedInUser);
      stage.close();
    } else {
      Node lastChild = windowLayout.getChildren().get(windowLayout.getChildren().size() - 1);
      if (!(lastChild instanceof Text)) {
        Text text = new Text("Gruppenname existiert bereits.");
        windowLayout.getChildren().addAll(text);
      }
    }
  }

  public void show() {
    stage.show();
  }
}
