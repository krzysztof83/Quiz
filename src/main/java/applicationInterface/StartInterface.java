package applicationInterface;

import model.ResultQuize;
import model.User;
import service.MyEntityMenagerService;
import service.QuestionService;
import service.ResultQuizeService;
import service.UserService;

import javax.persistence.EntityManager;
import java.util.Scanner;

/**
 * Created by Professional on 2017-03-16.
 */
public class StartInterface {

    public void start() {
        MyEntityMenagerService entityMenagerService = new MyEntityMenagerService();
        entityMenagerService.openMyEntity();
        EntityManager entityManager = entityMenagerService.getEntityManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Quiz");

        System.out.println("Write your login");
        String login = scanner.nextLine();
        System.out.println("Write your password");
        String password = scanner.nextLine();
        User user =new User(login,password);
        UserService userService = new UserService(user,entityManager);


        Boolean isCorrect = userService.verificationLogin() && userService.verificationPassword();

        int swithCase;
        if (isCorrect) {
            swithCase = switchForLoginUser(scanner);
        } else {
            swithCase = switchForNotLoginUser(scanner);
        }

        switch (swithCase) {
            case 1:
                QuestionService questionService = new QuestionService(entityManager);
                ResultQuize resultQuize = questionService.startQuize();
                ResultQuizeService resultQuizeService=new ResultQuizeService(resultQuize,user,entityManager);

                boolean flag=resultQuizeService.saveToDB(resultQuize);
                System.out.println(resultQuize.getQuizeResult());
                System.out.println(flag);
                break;
            case 2:
                ResultQuize myBestResult = userService.getMyBestResult();
                System.out.println("your best result is: "+myBestResult.getQuizeResult());
                break;
            case 3:
               userService.showAllResult();
                break;
            case 4:
                userService.changePassword();
                break;
            case 5:
                userService.createNewAccount();
                break;
            case 6:
                break;
            default:
                break;
        }
        entityMenagerService.closeMyEntity();
    }

    private int switchForNotLoginUser(Scanner scanner) {
        int swithCase;
        System.out.println("Password is not correct or Login is not exist in Database!");
        System.out.println("What do you want to do?");
        System.out.println("5.Create new accoun.");
        System.out.println("6. Exit.");
        swithCase = scanner.nextInt();
        scanner.nextLine();
        return swithCase;
    }

    private int switchForLoginUser(Scanner scanner) {
        int swithCase;
        System.out.println("Login and password is correct.");
        System.out.println("What do you want to do?");
        System.out.println("1.Start quiz");
        System.out.println("2.show my best result");///////////
        System.out.println("3.show all result");//////////////
        System.out.println("4.change password");
        swithCase = scanner.nextInt();
        scanner.nextLine();
        return swithCase;
    }

}
