package users;

public class User {

    public static User[] users;

    public static User loggedUser;

    private String ID;

    private String Firstname;

    private String Lastname;

    private String Password;

    private String Address;

    private Notification Notification;

    public User(String ID, String Firstname, String Lastname, String Password, String Address, Notification Notification) {
    }

    public String getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setID(String ID) {
    }

    public String getFirstname() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFirstname(String Firstname) {
    }

    public String getLastname() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLastname(String Lastname) {
    }

    public String setPassword() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPassword(String Password) {
    }

    public String getAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAddress(String Address) {
    }

    public Notification getNotification() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNotification(Notification Notification) {
    }

    public void removeUser(User removeUser) {
    }

    public void addUser(User newUser) {
    }

    public static void saveUsers() {
    }

    public static void getUsers() {
    }

    private static void sortUsers() {
    }

    private static void showUsers() {
    }

    public static void setUsers() {
    }
}
