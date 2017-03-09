package mock;

import com.sergiovanovi.model.User;

import java.util.Arrays;
import java.util.List;

public class MockUserService {
    public static User user = new User("User", "sergiovanovi@mail.ru", "123123", -5, 5);
    public static User admin = new User("Admin", "inbox@sergiovanovi.com", "123456", -10, 10);
    public static User expected = new User("exp", "expected@gmail.com", "321321", -12, 12);
    public static List<User> users = Arrays.asList(user, admin);
}
