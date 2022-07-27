package studio.dreamys;

import org.eclipse.jgit.api.Git;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.net.URI;

public class Hephaestus {
    public static File path = new File(System.getenv("APPDATA") + "\\Hephaestus");

    public static void main(String[] args) {
        System.out.println(" /$$   /$$                     /$$                                       /$$                        \n" +
                "| $$  | $$                    | $$                                      | $$                        \n" +
                "| $$  | $$  /$$$$$$   /$$$$$$ | $$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$   /$$   /$$  /$$$$$$$\n" +
                "| $$$$$$$$ /$$__  $$ /$$__  $$| $$__  $$ |____  $$ /$$__  $$ /$$_____/|_  $$_/  | $$  | $$ /$$_____/\n" +
                "| $$__  $$| $$$$$$$$| $$  \\ $$| $$  \\ $$  /$$$$$$$| $$$$$$$$|  $$$$$$   | $$    | $$  | $$|  $$$$$$ \n" +
                "| $$  | $$| $$_____/| $$  | $$| $$  | $$ /$$__  $$| $$_____/ \\____  $$  | $$ /$$| $$  | $$ \\____  $$\n" +
                "| $$  | $$|  $$$$$$$| $$$$$$$/| $$  | $$|  $$$$$$$|  $$$$$$$ /$$$$$$$/  |  $$$$/|  $$$$$$/ /$$$$$$$/\n" +
                "|__/  |__/ \\_______/| $$____/ |__/  |__/ \\_______/ \\_______/|_______/    \\___/   \\______/ |_______/ \n" +
                "                    | $$                                                                            \n" +
                "                    | $$                                                                            \n" +
                "                    |__/                                                                            ");

        //clone repository
        if (!path.exists()) {
            log("Repository not found. Cloning repository...");

            try {
                Git.cloneRepository().setURI("https://github.com/DxxxxY/R.A.T.git").setDirectory(path).call();
            } catch (Exception e) {
                log("Error while cloning R.A.T repository.");
                e.printStackTrace();
                return;
            }

            log("Repository cloned successfully.");
        } else {
            log("Repository already exists. Skipping cloning.");
        }

        //run setupDecompWorkspace
        log("Running setupDecompWorkspace... (This may take a while)");

        try {
            ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(path).useDistribution(new URI("https://services.gradle.org/distributions/gradle-3.1-bin.zip")).connect();
            connection.newBuild().forTasks("setupDecompWorkspace").run();
        } catch (Exception e) {
            log("Error while running setupDecompWorkspace.");
            e.printStackTrace();
            return;
        }

        log("SetupDecompWorkspace finished successfully.");
    }

    public static void log(String message) {
        System.out.println("[Hephaestus] " + message);
    }
}
