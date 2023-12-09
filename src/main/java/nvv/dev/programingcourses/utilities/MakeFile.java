package nvv.dev.programingcourses.utilities;

import java.io.File;

public class MakeFile {
    public static void makeDirectoryUpload(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
