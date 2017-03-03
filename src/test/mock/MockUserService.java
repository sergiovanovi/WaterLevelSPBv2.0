package mock;

import com.sergiovanovi.model.User;

import java.util.Arrays;
import java.util.List;

public class MockUserService {
    public static User user = new User("User", "user@yandex.ru", "123123", -40, 30, "greetings from user");
    public static User admin = new User("Admin", "admin@gmail.com", "123456", -10, 10, "greetings from admin");
    public static User expected = new User("exp", "expected@gmail.com", "321321", -12, 12, "greetings from expected");
    public static List<User> users = Arrays.asList(user, admin);
}
