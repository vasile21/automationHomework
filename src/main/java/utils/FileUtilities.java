/**
 * Created by vasilev on 28/03/2019.
 */
package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FileUtilities {
    private static final Logger LOGGER = LogManager.getLogger(FileUtilities.class);

    public static void deleteAllFilesFromDirectory(String directoryPath) {
        File directoryFile = new File(directoryPath);
        File[] files = directoryFile.listFiles();
        if (files != null) {
            for (File currentFile : files) {
                if (currentFile.isDirectory()) {
                    try {
                        FileUtils.deleteDirectory(currentFile);
                    } catch (IOException e) {
                        LOGGER.error("Could not delete current file: " + currentFile.getName() + ". Check error: " + e);
                    }
                } else {
                    currentFile.delete();
                }
            }
        }
    }
}