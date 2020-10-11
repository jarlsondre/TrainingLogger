package traininglogger.ui;

import java.io.File;

public class FileDeleter {

    public static void deleteFile(String filePath) {
        File exerciseFile = new File(filePath);
            if (exerciseFile.delete() == false) {
                System.out.println("Could not delete exercise file");
            } else {
                System.out.println("Successfully deleted exercise file");
            }
    }
}
