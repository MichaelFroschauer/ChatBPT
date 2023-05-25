package at.fhooe;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChatGroup {
  private String chatName;
  private boolean isPrivateChat;
  private ArrayList<ChatMessage> storedChatMessages;
  private ArrayList<ChatMessage> newChatMessages;
  private ObservableList<Member> groupMember;
  private ObservableList<Member> bannedGroupMember;
  private ChatMockDataManager dataManager = ChatMockDataManager.getInstance();
  private String adminShortUserName;


  ChatGroup(String chatName, boolean isPrivateChat) {
    this.chatName = chatName;
    this.isPrivateChat = isPrivateChat;
    this.newChatMessages = new ArrayList<>();
    this.adminShortUserName = ""; // private chat got no admin
    loadChatGroup();
  }

  ChatGroup(String chatName, boolean isPrivateChat, String adminUserId) {
    this.chatName = chatName;
    this.isPrivateChat = isPrivateChat;
    this.newChatMessages = new ArrayList<>();
    this.adminShortUserName = adminUserId;
    loadChatGroup();
  }

  public void loadChatGroup() {
    this.storedChatMessages = dataManager.loadChatMessages(isPrivateChat, chatName);
    this.groupMember = dataManager.getGroupChatMember(isPrivateChat, chatName);
    this.bannedGroupMember = dataManager.getBannedGroupChatMember(isPrivateChat, chatName);
  }

  public String getGroupName() {
    return chatName;
  }

  public boolean isPrivate() {
    return isPrivateChat;
  }

  public ArrayList<ChatMessage> getMessages() {
    ArrayList<ChatMessage> messageList = new ArrayList<>();
    messageList.addAll(storedChatMessages);
    messageList.addAll(newChatMessages);
    return messageList;
  }

  public void addChatMessage(String userId, String userName, String text) {
    if (bannedGroupMember.stream().noneMatch((member -> member.getShortName().equals(userId)))) {
      newChatMessages.add(new ChatMessage(
              userId,
              userName,
              LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
              ChatMessage.MessageType.MY_MESSAGE,
              text));
    }
  }

  public void storeNewMessages() {
    dataManager.saveChatMessages(isPrivateChat, chatName, newChatMessages);
    storedChatMessages.addAll(newChatMessages);
    newChatMessages.clear();
  }

  public ObservableList<Member> getGroupMemberAsList() {
    return groupMember;
  }

  public ObservableList<Member> getBannedGroupMemberAsList() {
    return bannedGroupMember;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(this.chatName);
    sb.append("\n").append(this.isPrivateChat ? "Privat" : "Gruppen").append(" Chat");
    return sb.toString();
  }

  public String toSingleLineString() {
    return this.chatName + " " + (this.isPrivateChat ? "Privat" : "Gruppen") + " Chat";
  }

  public void banOrUnbanUser(Member user, Member loggedInUser) {
    if (loggedInUser.getShortName().equals(adminShortUserName)) {
      if (bannedGroupMember.contains(user)) {
        bannedGroupMember.remove(user);
        dataManager.removeBannedGroupChatMember(user, chatName);
      } else {
        bannedGroupMember.add(user);
        dataManager.addBannedGroupChatMember(user, chatName);
      }
    }
  }

  public String getChatGroupAdmin() {
    return adminShortUserName;
  }
}