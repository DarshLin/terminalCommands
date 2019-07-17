import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Scanner;

public class terminalCommands {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String response;
        boolean active = true;

        while(active) {
            System.out.println("\nEnter a command: ");
            response = scan.nextLine();

            active = ProcessRequest(response);
        }

    }

    private static boolean ProcessRequest(String response) {
        String[] paths = null;
        String target = "";

        if(response.contains(" ")) {
            paths = response.split(" ");
            response = paths[0];
            target = paths[1];
            System.out.println("target: " + target);
        }

        switch (response) {
            case "ls":
                ListFilesInDirectory(target);
                return true;
            case "pwd":
                PresentWorkingDirectory();
                return true;
            case "mkdir":
                MakeDirectory(target);
                return true;
            case "exit":
                System.err.println("exiting...");
                return false;
            default:
                System.err.println(response + " is not a proper command");
                return true;
        }
    }

    private static void PresentWorkingDirectory() {
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
    }

    private static void MakeDirectory(String target) {
        String currentDir = System.getProperty("user.dir");
        File createDir = new File(currentDir + "\\" + target);

        try{
            createDir.mkdir();
            System.out.printf(createDir + " is created");
        }
        catch(Exception e) {
            System.err.printf(createDir + " is not created");
        }
    }

    private static void ListFilesInDirectory(String path) {
        File currentDir;

        if(path.length() == 0) {
            currentDir = new File(".");
        }
        else {
            currentDir = new File(path);
        }
        try {
            File[] filesList = currentDir.listFiles();
            for (File f : filesList) {
                System.out.println(f.getName());
            }
        }
        catch(Exception f) {
            System.out.println(currentDir + " not found");
            return;
        }
    }
}
