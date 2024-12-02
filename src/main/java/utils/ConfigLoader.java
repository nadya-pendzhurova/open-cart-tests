package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }

    public static String getAdminUsername() {
        return dotenv.get("ADMIN_USERNAME");
    }

    public static String getAdminPassword() {
        return dotenv.get("ADMIN_PASSWORD");
    }
}
