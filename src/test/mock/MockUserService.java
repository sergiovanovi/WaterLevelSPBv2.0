package mock;

import com.sergiovanovi.model.User;
import com.sergiovanovi.util.PasswordUtil;

import java.util.Arrays;
import java.util.List;

public class MockUserService {
    public static User user = new User("sergiovanovi@mail.ru", PasswordUtil.encode("123123"), -5, 5);
    public static User admin = new User("inbox@sergiovanovi.com", PasswordUtil.encode("123456"), -10, 10);
    public static User expected = new User("expected@gmail.com", "321321", -12, 12);
    public static List<User> users = Arrays.asList(user, admin);
}
