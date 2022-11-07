package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Andrew", "Andreevskiy", "andrew@gmail.com");
        User user2 = new User("Oleg", "Olegovskiy", "oleg@gmail.com");
        User user3 = new User("Egor", "Egorovskiy", "egor@gmail.com");
        User user4 = new User("Kirill", "Kirillovskiy", "kirill@gmail.com");

        Car car1 = new Car("BMW", 1996);
        Car car2 = new Car("Chevrolet", 2006);
        Car car3 = new Car("Nissan", 2022);
        Car car4 = new Car("Toyota", 2019);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));


        for (User user : userService.listUsers()) {
            System.out.println("User : " + user + " with car " + user.getCar());
        }

        System.out.println();

        String testModel = "BMW";

        int testSeries = 1996;

        System.out.println(userService.getUserByCarModelAndSeries(testModel, testSeries));

        System.out.println();

        try {
            User noSuchUser = userService.getUserByCarModelAndSeries(testModel, testSeries + 5);
        } catch (Exception e) {
            System.out.println("There is no User with satisfying parameters");
        }


//      List<User> users = userService.listUsers();
//
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }

        context.close();
    }
}
