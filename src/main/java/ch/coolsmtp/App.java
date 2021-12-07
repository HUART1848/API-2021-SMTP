package ch.coolsmtp;

import ch.coolsmtp.mail.Group;

public class App {
    public static void main(String[] args) {
        System.out.println("=== coolsmtp : SMTP-based app to prank your friends ===");

        Config config = new Config();
        System.out.println("Loading config...");

        if (!config.parseConfigFromFile("config/config.txt")) {
            System.out.println("ERROR while attempting to load the config file.\n" +
                    "Please check for config file existence and/or fix config file syntax");
            return;
        }

        System.out.println("Config loaded");
        System.out.println(config);

        SmtpClient client = new SmtpClient(config.getAddress(), config.getPort());


    }
}
