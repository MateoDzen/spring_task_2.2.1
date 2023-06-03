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

      userService.add(new User("Oleg", "Ivanov", "Ivanov@mail.ru", new Car("BMW", 3)));
      userService.add(new User("Vasya", "Petrov", "kiatop@mail.ru", new Car("Kia", 1)));
      userService.add(new User("Masha", "Zubova", "masha@mail.ru", new Car("Lada", 10)));
      userService.add(new User("Dima", "Koshkin", "dimasta@mail.ru", new Car("Tesla", 7)));

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }

      User user1 = userService.getUserByHisCar("BMW", 3);
      System.out.println(user1);

      context.close();
   }
}
