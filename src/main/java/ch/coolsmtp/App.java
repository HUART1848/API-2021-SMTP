package ch.coolsmtp;

public class App {
    public static void main(String[] args) {
        System.out.println("=== coolsmtp : SMTP-based app to prank your friends ===");

        Config config = new Config();
        System.out.println("Loading config (config.txt)...");
        if (!config.parseConfigFromFile("src/main/config/config.txt")) {
            System.out.println("ERROR while attempting to load the config file.\n" +
                    "Please check for config file existence and/or fix config file syntax");
        }

        System.out.println("Config loaded");
        System.out.println(config);
    }
}
