package at.fhooe;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ChatDataManager {
  public ObservableList<ChatGroup> loadChatGroupListForMember(String shortName);
  public ArrayList<ChatMessage> loadChatMessages(boolean privateChat, String chatRoomName);
  public ObservableList<Member> getGroupChatMember(boolean privateChat, String chatRoomName);
  public ObservableList<Member> getBannedGroupChatMember(boolean privateChat, String chatRoomName);
  public ObservableList<Member> getRegisteredMember();
  public void saveChatMessages(boolean privateChat, String chatRoomName, ArrayList<ChatMessage> chatRoomMessages);
  public void addBannedGroupChatMember(Member user, String chatGroupName);
  public void removeBannedGroupChatMember(Member user, String chatGroupName);
}
