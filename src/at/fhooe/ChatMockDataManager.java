package at.fhooe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ChatMockDataManager implements ChatDataManager {
  private static ChatMockDataManager instance;

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

  @Override
  public ObservableList<ChatGroup> loadChatGroups() {

    ObservableList<ChatGroup> chatGroups = FXCollections.observableArrayList();
    chatGroups.add(new ChatGroup("Michael", true));
    chatGroups.add(new ChatGroup("Friends", false));
    chatGroups.add(new ChatGroup("Kollegen", false));
    chatGroups.add(new ChatGroup("John", true));
    chatGroups.add(new ChatGroup("Family", false));
    chatGroups.add(new ChatGroup("Emily", true));
    chatGroups.add(new ChatGroup("Colleagues", false));
    chatGroups.add(new ChatGroup("Sarah", true));
    chatGroups.add(new ChatGroup("Schoolmates", false));
    chatGroups.add(new ChatGroup("David", true));
    chatGroups.add(new ChatGroup("Sports Team", false));
    chatGroups.add(new ChatGroup("Jessica", true));
    chatGroups.add(new ChatGroup("Hobby Club", false));
    chatGroups.add(new ChatGroup("Daniel", true));
    chatGroups.add(new ChatGroup("Neighborhood", false));
    chatGroups.add(new ChatGroup("Sophia", true));
    chatGroups.add(new ChatGroup("Travel Group", false));
    chatGroups.add(new ChatGroup("Christopher", true));
    chatGroups.add(new ChatGroup("Study Group", false));
    chatGroups.add(new ChatGroup("Olivia", true));
    chatGroups.add(new ChatGroup("Music Band", false));
    chatGroups.add(new ChatGroup("Andrew", true));
    chatGroups.add(new ChatGroup("Gaming Squad", false));
    return chatGroups;
  }

  @Override
  public ArrayList<ChatMessage> loadChatMessages(String chatRoomName) {
    ArrayList<ChatMessage> chatMessages = new ArrayList<>();
    chatMessages.add(new ChatMessage("John", "18.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Hello, how are you?"));
    chatMessages.add(new ChatMessage("Emily", "19.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I'm good, thanks! What about you?"));
    chatMessages.add(new ChatMessage("Sarah", "20.05.2023", ChatMessage.MessageType.MY_MESSAGE, "I'm doing great. Just finished my project."));
    chatMessages.add(new ChatMessage("David", "21.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "That's awesome! Congrats!"));
    chatMessages.add(new ChatMessage("Jessica", "22.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Thanks! I worked hard on it."));
    chatMessages.add(new ChatMessage("Daniel", "23.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Hey, have you seen the latest movie?"));
    chatMessages.add(new ChatMessage("Sophia", "24.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Yes, I watched it yesterday. It was amazing!"));
    chatMessages.add(new ChatMessage("Christopher", "25.05.2023", ChatMessage.MessageType.MY_MESSAGE, "I heard it has great reviews."));
    chatMessages.add(new ChatMessage("Olivia", "26.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Indeed! It's a must-watch."));
    chatMessages.add(new ChatMessage("Andrew", "27.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Let's plan a movie night then!"));
    chatMessages.add(new ChatMessage("Isabella", "28.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Sounds like a plan!"));
    chatMessages.add(new ChatMessage("Matthew", "29.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Great! I'll bring the popcorn."));
    chatMessages.add(new ChatMessage("Ava", "30.05.2023", ChatMessage.MessageType.OTHER_MESSAGE, "I'll get the drinks. See you then!"));
    chatMessages.add(new ChatMessage("William", "31.05.2023", ChatMessage.MessageType.MY_MESSAGE, "Looking forward to it!"));
    chatMessages.add(new ChatMessage("Emma", "01.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Me too!"));
    chatMessages.add(new ChatMessage("Joseph", "02.06.2023", ChatMessage.MessageType.MY_MESSAGE, "See you soon!"));
    chatMessages.add(new ChatMessage("Mia", "03.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Take care!"));
    chatMessages.add(new ChatMessage("Ethan", "04.06.2023", ChatMessage.MessageType.MY_MESSAGE, "You too!"));
    chatMessages.add(new ChatMessage("Charlotte", "05.06.2023", ChatMessage.MessageType.OTHER_MESSAGE, "Bye!"));
    chatMessages.add(new ChatMessage("Lucas", "06.06.2023", ChatMessage.MessageType.MY_MESSAGE, "Goodbye!"));
    chatMessages.add(new ChatMessage("SYSTEM", "06.06.2023", ChatMessage.MessageType.SYSTEM_MESSAGE, "Error, Server is dead!"));
    return chatMessages;
  }

  @Override
  public ObservableList<Member> loadGroupChatMember(String chatRoomName) {
    ObservableList<Member> groupChatMember = FXCollections.observableArrayList();
    groupChatMember.add(new Member("Michael", "Mike", "asdfjköl"));
    groupChatMember.add(new Member("John", "Johny", "qwer1234"));
    groupChatMember.add(new Member("Emily", "Emi", "zxcv0987"));
    groupChatMember.add(new Member("Sarah", "Sara", "12345678"));
    groupChatMember.add(new Member("David", "Dave", "poiuytrew"));
    groupChatMember.add(new Member("Jessica", "Jess", "mnbvcxz"));
    groupChatMember.add(new Member("Daniel", "Dan", "lkjhgfdsa"));
    return groupChatMember;
  }

  @Override
  public ObservableList<Member> loadRegisteredMember() {
    ObservableList<Member> registeredMember = FXCollections.observableArrayList();
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
    return registeredMember;
  }
}
