package ma.youcoude.batiCuisine.config;

public class DatabaseConfig {

    private static final String url = "jdbc:postgresql://localhost:5432/baticuisine_db";
    private static final String username = "postgres";
    private static final String password = "Fasha321@";

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
