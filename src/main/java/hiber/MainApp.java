package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Renault Duster", 121);
        Car car2 = new Car("Toyota Land Cruiser", 515);
        Car car3 = new Car("BMW X7", 959);
        Car car4 = new Car("Mercedes-Benz GLE", 112);

        User user1 = new User("Ольга", "Иванова", "ivanova@mail.ru");
        User user2 = new User("Михаил", "Чернов", "chernov@mail.ru");
        User user3 = new User("Иван", "Смирнов", "smirnov@mail.ru");
        User user4 = new User("Ксения", "Молотова", "molotova@mail.ru");
        user1.setCar(car4);
        user2.setCar(car2);
        user3.setCar(car1);
        user4.setCar(car3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        userService.getUserByModelAndSeries("Toyota Land Cruiser", 515);
        userService.getUserByModelAndSeries("BMW X6", 959); //нет в таблице, поиск выдаст сообщение об ошибке
        userService.getUserByModelAndSeries("Renault Duster", 121);
        userService.getUserByModelAndSeries("Mercedes-Benz GLE", 112);


        context.close();
    }
}
