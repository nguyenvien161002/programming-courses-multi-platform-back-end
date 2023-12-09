package nvv.dev.programingcourses.utilities;

import nvv.dev.programingcourses.services.UserService;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Provides utility methods to create the necessary components for the project.
 */
public class Generate {

    /**
     * Generates a unique filename for storing uploaded files.
     *
     * @param originalFilename The original filename of the file.
     * @return A unique filename with a timestamp and random number.
     */
    public static String generateUploadFileNameStored(String originalFilename) {
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt(10000);

        return timestamp + "_" + randomNumber + extension;
    }

    /**
     * Generates a unique username for the given first name and last name.
     *
     * @param userService The user service to use to check for existing usernames.
     * @param firstname The user's first name.
     * @param lastname The user's last name.
     *
     * @return A unique username.
     */
    public static String generateUsername(UserService userService, String firstname, String lastname) {
        String username = normalizeName(firstname) + normalizeName(lastname);
        int suffix = 1;
        String originalUsername = username;
        while (userService.existsByUsername(username)) {
            username = originalUsername + suffix;
            suffix++;
        }

        return username;
    }

    /**
     * Normalizes the given name by removing combining diacritical marks and whitespace.
     *
     * @param name The name to normalize.
     * @return The normalized name.
     */
    public static String normalizeName(String name) {
        return Normalizer.normalize(name.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("\\s", "");
    }
}
