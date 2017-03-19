package applicationInterface;

import service.StartInterfaceService;

/**
 * Created by Professional on 2017-03-19.
 */
public class StartInterface {


    public void start() {
        StartInterfaceService startInterfaceService = new StartInterfaceService();
        Boolean exit = startInterfaceService.getExit();
        Boolean isLogin = false;

        while (!exit) {

            while (!isLogin && !exit) {
                Integer switchCase = startInterfaceService.notLoginMenu();
                startInterfaceService.swithCaseMenu(switchCase);
                exit = startInterfaceService.getExit();
                if (startInterfaceService.getLogin() != null) {
                    isLogin = startInterfaceService.getLogin();
                }
            }

            while (isLogin && !exit) {
                Integer switchCase = startInterfaceService.loginMenu();
                startInterfaceService.swithCaseMenu(switchCase);
                exit = startInterfaceService.getExit();
                if (startInterfaceService.getLogin() != null) {
                    isLogin = startInterfaceService.getLogin();
                }
            }

        }

    }
}
