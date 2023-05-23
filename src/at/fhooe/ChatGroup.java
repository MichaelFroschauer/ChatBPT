package at.fhooe;

import java.util.ArrayList;

public class ChatGroup {
  private String chatName;
  private boolean isPrivateChat;
  private ArrayList<ChatMessage> chatMessages;
  private ChatMockDataManager dataManager = ChatMockDataManager.getInstance();

  ChatGroup(String chatName, boolean isPrivateChat) {
    this.chatName = chatName;
    this.isPrivateChat = isPrivateChat;
    this.chatMessages = dataManager.loadChatMessages(chatName);
  }

  public ArrayList<ChatMessage> getMessages() {
    return chatMessages;
  }

  public void addChatMessage(String text) {
    chatMessages.add(new ChatMessage("Michael", "16.05.2023", ChatMessage.MessageType.MY_MESSAGE, text));
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

  public ArrayList<ChatMessage> searchMessages(String searchPattern) {
    // TODO
    return null;
  }

  public String getChatName() {
    return chatName;
  }
}