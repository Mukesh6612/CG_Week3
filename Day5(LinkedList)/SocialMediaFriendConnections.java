import java.util.*;

class UserNode {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    UserNode next;

    public UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private UserNode head;
    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        System.out.println("User added successfully.");
    }
    private UserNode getUserById(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }
    public void addFriend(int userId1, int userId2) {
        UserNode user1 = getUserById(userId1);
        UserNode user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friendIds.contains(userId2)) user1.friendIds.add(userId2);
        if (!user2.friendIds.contains(userId1)) user2.friendIds.add(userId1);

        System.out.println("Friend connection added.");
    }
    public void removeFriend(int userId1, int userId2) {
        UserNode user1 = getUserById(userId1);
        UserNode user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friendIds.remove(Integer.valueOf(userId2));
        user2.friendIds.remove(Integer.valueOf(userId1));

        System.out.println("Friend connection removed.");
    }
    public void displayFriends(int userId) {
        UserNode user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Friends of " + user.name + ":");
        for (int id : user.friendIds) {
            UserNode friend = getUserById(id);
            if (friend != null) {
                System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
            }
        }
    }
    public void searchUser(String keyword) {
        UserNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(keyword) || Integer.toString(temp.userId).equals(keyword)) {
                System.out.println("User found: " + temp.name + ", ID: " + temp.userId + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No matching user found.");
    }
    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = getUserById(userId1);
        UserNode user2 = getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        Set<Integer> set = new HashSet<>(user1.friendIds);
        System.out.println("Mutual Friends:");
        boolean found = false;
        for (int id : user2.friendIds) {
            if (set.contains(id)) {
                UserNode mutual = getUserById(id);
                if (mutual != null) {
                    System.out.println("- " + mutual.name + " (ID: " + mutual.userId + ")");
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No mutual friends.");
    }
    public void countFriends() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friend(s).");
            temp = temp.next;
        }
    }
}
public class SocialMediaFriendConnections {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 30);
        sm.addUser(3, "Charlie", 22);
        sm.addUser(4, "David", 27);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(2, 4);

        sm.displayFriends(1);
        sm.displayFriends(2);

        sm.findMutualFriends(1, 2);

        sm.removeFriend(1, 2);
        sm.displayFriends(1);

        sm.searchUser("Charlie");
        sm.searchUser("3");

        sm.countFriends();
    }
}
