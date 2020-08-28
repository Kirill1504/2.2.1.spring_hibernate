package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(new Car("lada_priora", 123));
        userService.add(user1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(new Car("lada_Granta", 12345));
        userService.add(user2);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("CarName = " + user.getCar().getName());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("lada_Granta", 12345));

        context.close();
    }
}
