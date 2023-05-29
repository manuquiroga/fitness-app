import API.ConnectionAPI;
import Users.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("name", "password", "email", 1, null);
        user.toFile();
    }
}