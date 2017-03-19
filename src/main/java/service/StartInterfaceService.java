package service;

import model.ResultQuize;
import model.User;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Professional on 2017-03-19.
 */
public class StartInterfaceService {
    private Boolean isLogin;
    private Boolean exit;
    private User user;

    public StartInterfaceService() {
        this.exit = false;
    }

    public Boolean getExit() {
        return exit;
    }


    public User getUser() {
        return user;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public Integer notLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[1358]");

        System.out.println(" Quiz");
        System.out.println("What do you want to do?");
        System.out.println("1. Login to Quiz");
        System.out.println("3. Show all result");
        System.out.println("5. Create new accoun.");
        System.out.println("8. Exit.");

        Integer swithCase = scanner.nextInt();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(swithCase.toString());

        while (!matcher.matches()) {
            System.out.println("Your choise is't in menu List. ");
            System.out.println("Please select number 1, 3, 5, 8 ");
            System.out.println("1. Login to Quiz");
            System.out.println("3. Show all result");
            System.out.println("5. Create new accoun.");
            System.out.println("8. Exit.");
            swithCase = scanner.nextInt();
            scanner.nextLine();
            matcher = pattern.matcher(swithCase.toString());
        }
        return swithCase;
    }

    public Integer loginMenu() {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("[234678]");

        System.out.println(" Quiz");
        System.out.println("What do you want to do?");
        System.out.println("2. Start Quize.");
        System.out.println("3. Show all result.");
        System.out.println("4. Show my best result.");
        System.out.println("6. Change password.");
        System.out.println("7. Logout");
        System.out.println("8. Exit.");

        Integer swithCase = scanner.nextInt();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(swithCase.toString());

        while (!matcher.matches()) {
            System.out.println("Your choise is't in menu List. ");
            System.out.println("Please select number 2, 3, 4, 6, 7 ");
            System.out.println("2. Start Quize.");
            System.out.println("3. Show all result.");
            System.out.println("4. Show my best result.");
            System.out.println("6. Change password.");
            System.out.println("7. Logout");
            System.out.println("8. Exit.");
            swithCase = scanner.nextInt();
            scanner.nextLine();
            matcher = pattern.matcher(swithCase.toString());
        }
        return swithCase;
    }

    public void swithCaseMenu(Integer switchCase) {
        this.user = getUser();
        MyEntityMenagerService entityMenagerService = new MyEntityMenagerService();
        entityMenagerService.openMyEntity();
        EntityManager entityManager = entityMenagerService.getEntityManager();
        UserService userService = new UserService(entityManager);

        switch (switchCase) {
            case 1:// zaloguj
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write your login");
                String login = scanner.nextLine();
                System.out.println("Write your password");
                String password = scanner.nextLine();
                User tmpUser = new User(login, password);

                userService = userService.setUser(tmpUser);
                Boolean isCorrect = userService.verificationLogin() && userService.verificationPassword();

                if (!isCorrect) {
                    System.out.println("Password is not correct or Login is not exist in Database!");
                } else if (isCorrect) {
                    System.out.println("Login successfully completed");
                    this.isLogin = true;
                    this.user = tmpUser;
                }
                this.exit = false;
                break;

            case 2://start quize
                QuestionService questionService = new QuestionService(entityManager);
                ResultQuize resultQuize = questionService.startQuize();
                ResultQuizeService resultQuizeService = new ResultQuizeService(resultQuize, getUser(), entityManager);

                boolean flag = resultQuizeService.saveToDB(resultQuize);
                System.out.println(resultQuize.getQuizeResult());
                System.out.println(flag);
                break;

            case 3://show all result
                userService.showAllResult();
                break;

            case 4://show my best result
                userService = userService.setUser(getUser());
                userService.showMyBestResult();
                break;

            case 5://create new acount
                userService.createNewAccount();
                break;

            case 6:
                userService = userService.setUser(getUser());
                userService.changePassword();
                break;

            case 7://loginout
                this.isLogin = false;
                break;

            case 8://exit
                this.exit = true;
                break;

            default:
                this.exit = true;
                break;
        }

        entityMenagerService.closeMyEntity();

    }
}
