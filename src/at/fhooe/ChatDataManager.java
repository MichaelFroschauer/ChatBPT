package at.fhooe;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ChatDataManager {
  public ObservableList<ChatGroup> loadChatGroups();
  public ArrayList<ChatMessage> loadChatMessages(String chatRoomName);
  public ObservableList<Member> loadGroupChatMember(String chatRoomName);
  public ObservableList<Member> loadRegisteredMember();
}
