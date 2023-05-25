package at.fhooe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatMockDataManager implements ChatDataManager {
  private static ChatMockDataManager instance;
  private static class MemberMessage {
    private String groupId;
    private ChatMessage message;
    private boolean privateMessage;
    public MemberMessage(String groupId, boolean privateMessage, ChatMessage message) {
      this.groupId = groupId;
      this.privateMessage = privateMessage;
      this.message = message;
    }
  }

  private ObservableList<ChatGroup> chatGroups = FXCollections.observableArrayList();
  private ArrayList<MemberMessage> chatMessages = new ArrayList<>();
  private ObservableList<Member> groupChatMember = FXCollections.observableArrayList();
  private ObservableList<Member> bannedGroupChatMember = FXCollections.observableArrayList();
  private ObservableList<Member> registeredMember = FXCollections.observableArrayList();

  private ChatMockDataManager() {}
  public static ChatMockDataManager getInstance() {
    if (instance == null) {
      synchronized (ChatMockDataManager.class) {
        if (instance == null) {
          instance = new ChatMockDataManager();
        }
      }
    }
    return instance;
  }

  public void loadMockData() {
    chatGroups.add(new ChatGroup("INFORMATION", true));
    chatGroups.add(new ChatGroup("Friends", false, "m"));
    chatGroups.add(new ChatGroup("Kollegen", false, "m"));
    chatGroups.add(new ChatGroup("John", true));
    chatGroups.add(new ChatGroup("Family", false, "m"));
    chatGroups.add(new ChatGroup("Emily", true));
    chatGroups.add(new ChatGroup("Colleagues", false, "m"));
    chatGroups.add(new ChatGroup("Sarah", true));
    chatGroups.add(new ChatGroup("Schoolmates", false, "m"));
    chatGroups.add(new ChatGroup("David", true));
    chatGroups.add(new ChatGroup("Sports Team", false, "m"));
    chatGroups.add(new ChatGroup("Jessica", true));
    chatGroups.add(new ChatGroup("Hobby Club", false, "m"));
    chatGroups.add(new ChatGroup("Daniel", true));
    chatGroups.add(new ChatGroup("Neighborhood", false, "m"));
    chatGroups.add(new ChatGroup("Travel Group", false, "m"));
    chatGroups.add(new ChatGroup("Study Group", false, "m"));
    chatGroups.add(new ChatGroup("Music Band", false, "m"));
    chatGroups.add(new ChatGroup("Gaming Squad", false, "m"));


    chatMessages.add(new MemberMessage("INFORMATION", true, new ChatMessage("TheComputer", "TheComputer", "18.05.2023", ChatMessage.MessageType.OTHER_MESSAGE,
            "Hi and welcome to Chat-BPT!")));
    chatMessages.add(new MemberMessage("INFORMATION", true, new ChatMessage("TheComputer", "TheComputer", "18.05.2023", ChatMessage.MessageType.OTHER_MESSAGE,
            "This is a private Chat Room, you can create or join public group chats by clicking on the plus sign on the top left side. You can also search for your friends there :)")));
    chatMessages.add(new MemberMessage("INFORMATION", true, new ChatMessage("TheComputer", "TheComputer", "18.05.2023", ChatMessage.MessageType.OTHER_MESSAGE,
            "You can easily write messages into chat rooms by selecting one on the left side and start typing into the box on the bottom.")));
    chatMessages.add(new MemberMessage("INFORMATION", true, new ChatMessage("SYSTEM", "SYSTEM", "", ChatMessage.MessageType.SYSTEM_MESSAGE,
            "If something happens you get immediately informed by a system message like this.")));
    chatMessages.add(new MemberMessage("INFORMATION", true, new ChatMessage("TheComputer", "TheComputer", "18.05.2023", ChatMessage.MessageType.OTHER_MESSAGE,
            "Now lets try it out, start typing into the box on the bottom and send the message by clicking 'Send' or pressing 'ENTER'.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "18.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Hello, how are you?")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Emily", "Emily", "19.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I'm good, thanks! What about you?")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "20.05.2023", ChatMessage.MessageType.MY_MESSAGE, "I'm doing great. Just finished my project.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("David", "David", "21.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "That's awesome! Congrats!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "22.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Thanks! I worked hard on it.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Daniel", "Daniel", "23.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Hey, have you seen the latest movie?")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Sophia", "Sophia", "24.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Yes, I watched it yesterday. It was amazing!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "25.05.2023", ChatMessage.MessageType.MY_MESSAGE, "I heard it has great reviews.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Olivia", "Olivia", "26.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Indeed! It's a must-watch.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Andrew", "Andrew", "27.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Let's plan a movie night then!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Isabella", "Isabella", "28.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Sounds like a plan!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "29.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Great! I'll bring the popcorn.")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Ava", "Ava", "30.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I'll get the drinks. See you then!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "31.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Looking forward to it!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Emma", "Emma", "01.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Me too!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("m", "Mike", "02.06.2023", ChatMessage.MessageType.MY_MESSAGE, "See you soon!")));
    chatMessages.add(new MemberMessage("Friends", false, new ChatMessage("Mia", "Mia", "03.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Take care!")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("m", "Mike", "04.06.2023", ChatMessage.MessageType.MY_MESSAGE, "You too!")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Charlotte", "Charlotte", "05.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Bye!")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Lucas", "Lucas", "06.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Goodbye!")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("m", "Mike", "07.06.2023", ChatMessage.MessageType.MY_MESSAGE, "How was your weekend?")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Emma", "Emma", "07.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "It was great! I went hiking.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Noah", "Noah", "08.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I love hiking too. Where did you go?")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Olivia", "Olivia", "08.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I went to the mountains. The view was breathtaking.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("William", "William", "09.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I need some recommendations for good books to read. Any suggestions?")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Sophia", "Sophia", "09.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Have you read 'The Great Gatsby' by F. Scott Fitzgerald? It's a classic!")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("m", "Mike", "10.06.2023", ChatMessage.MessageType.MY_MESSAGE, "Yes, I've heard about it. I'll add it to my reading list.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Isabella", "Isabella", "10.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "You won't be disappointed. It's a brilliant novel.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Benjamin", "Benjamin", "11.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "What are your plans for the summer vacation?")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Mia", "Mia", "11.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I'm going to visit my grandparents in the countryside.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Lucas", "Lucas", "12.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "That sounds lovely. Enjoy your time with family.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("Charlotte", "Charlotte", "12.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Thank you! I'm looking forward to it.")));
    chatMessages.add(new MemberMessage("Kollegen", false, new ChatMessage("SYSTEM", "SYSTEM", "06.06.2023", ChatMessage.MessageType.SYSTEM_MESSAGE, "Error, Server is dead!")));



    registeredMember.add(new Member("m", "Mike", "m"));
    registeredMember.add(new Member("Michael", "Mike", "asdfjköl"));
    registeredMember.add(new Member("John", "Johny", "qwer1234"));
    registeredMember.add(new Member("Emily", "Emi", "zxcv0987"));
    registeredMember.add(new Member("Sarah", "Sara", "12345678"));
    registeredMember.add(new Member("David", "Dave", "poiuytrew"));
    registeredMember.add(new Member("Jessica", "Jess", "mnbvcxz"));
    registeredMember.add(new Member("Daniel", "Dan", "lkjhgfdsa"));
    registeredMember.add(new Member("Sophia", "Sophie", "98765432"));
    registeredMember.add(new Member("Christopher", "Chris", "ytrewqpoi"));
    registeredMember.add(new Member("Olivia", "Oli", "asdf1234"));
    registeredMember.add(new Member("Andrew", "Andy", "qwer5678"));
    registeredMember.add(new Member("Isabella", "Bella", "zxcv8765"));
    registeredMember.add(new Member("Matthew", "Matt", "87654321"));
    registeredMember.add(new Member("Ava", "Ava", "0987poiuyt"));
    registeredMember.add(new Member("William", "Will", "mnbv0987"));
    registeredMember.add(new Member("Emma", "Emmy", "54321zxcv"));
    registeredMember.add(new Member("Joseph", "Joe", "0987lkjhgf"));
    registeredMember.add(new Member("Mia", "Mia", "0987qwert"));
    registeredMember.add(new Member("Ethan", "Ethan", "rewq5432"));
    registeredMember.add(new Member("Charlotte", "Charly", "6789asdf"));
    registeredMember.add(new Member("Bernd", "BE", "zxcv8765"));



    groupChatMember.add(registeredMember.get(0));
    groupChatMember.add(registeredMember.get(1));
    groupChatMember.add(registeredMember.get(2));
    groupChatMember.add(registeredMember.get(3));
    groupChatMember.add(registeredMember.get(4));
    groupChatMember.add(registeredMember.get(5));
    groupChatMember.add(registeredMember.get(6));

    bannedGroupChatMember.add(registeredMember.get(1));
    bannedGroupChatMember.add(registeredMember.get(2));
  }

  @Override
  public ObservableList<ChatGroup> loadChatGroupListForMember(String shortName) {
    // TODO only load chats for the current user when db exists
    return chatGroups;
  }

  @Override
  public ArrayList<ChatMessage> loadChatMessages(boolean privateChat, String chatRoomName) {
    return chatMessages.stream()
            .filter(message -> message.privateMessage == privateChat && message.groupId.equals(chatRoomName))
            .map(message -> message.message)
            .collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public void saveChatMessages(boolean privateChat, String chatRoomName, ArrayList<ChatMessage> chatRoomMessages) {
    for (ChatMessage m: chatRoomMessages) {
      chatMessages.add(new MemberMessage(chatRoomName, privateChat , m));
    }
  }

  @Override
  public ObservableList<Member> getGroupChatMember(boolean privateChat, String chatRoomName) {
    // TODO only load members for the selected group chat when db exists
    return groupChatMember;
  }

  @Override
  public ObservableList<Member> getBannedGroupChatMember(boolean privateChat, String chatRoomName) {
    // TODO only load members for the selected group chat when db exists
    return bannedGroupChatMember;
  }

  @Override
  public void addBannedGroupChatMember(Member user, String chatGroupName) {
    // TODO only for the given chatGroupName when db exists
    bannedGroupChatMember.add(user);
  }

  @Override
  public void removeBannedGroupChatMember(Member user, String chatGroupName) {
    // TODO only for the given chatGroupName when db exists
    bannedGroupChatMember.remove(user);
  }

  @Override
  public ObservableList<Member> getRegisteredMember() {
    return registeredMember;
  }

  public boolean memberExists(String shortName) {
    List<Member> members = getRegisteredMember().stream()
            .filter(m -> m.getShortName().equals(shortName))
            .toList();
    return (members.size() >= 1);
  }

  public Member getMemberByShortName(String shortName) {
    List<Member> members = getRegisteredMember().stream()
            .filter(m -> m.getShortName().equals(shortName))
            .toList();
    if (members.size() == 1)
      return members.get(0);
    else
      return null;
  }

  public void addNewMember(String shortName, String userName, String passwordHash) {
    registeredMember.add(new Member(shortName, userName, passwordHash));
  }



  public void createNewPrivateChat(String shortUserName) {
    chatGroups.add(new ChatGroup(shortUserName, true));
  }

  public boolean privateChatExists(String shortUserName) {
    return chatGroups.stream().anyMatch(chat -> chat.getGroupName().equals(shortUserName) && chat.isPrivate());
  }



  public boolean groupExists(String groupName) {
    return chatGroups.stream().anyMatch(group -> group.getGroupName().equals(groupName) && !group.isPrivate());
  }

  public void createNewGroupChat(String groupName, String adminUser) {
    chatGroups.add(new ChatGroup(groupName, false, adminUser));
  }

  public boolean userExistsInGroup(String groupName, String userShortName) {
    if (groupExists(groupName)) {
      return groupChatMember.stream().anyMatch(member -> member.getShortName().equals(userShortName));
    }
    return false;
  }

  public void userEntersGroup(String groupName, String loggedInUser) {
    // Todo add him to the right group
    if (!userExistsInGroup(groupName, loggedInUser)) {
      Member newGroupMember = getMemberByShortName(loggedInUser);
      groupChatMember.add(newGroupMember);
    }
  }

  public void deleteGroupChat(String chatGroupName) {
    for (int i = 0; i < chatGroups.size(); i++) {
      if (chatGroups.get(i).getGroupName().equals(chatGroupName)) {
        chatGroups.remove(i);
        break;
      }
    }
  }
}
