package ch.coolsmtp;

import ch.coolsmtp.mail.Group;
import ch.coolsmtp.mail.Person;

public class App {
    public static void main(String[] args) {
        System.out.println("=== coolsmtp : SMTP-based app to prank your friends ===\n");

        Config config = new Config();
        if (!config.parseConfigFromFile("src/main/config/config.txt")) {
            System.out.println("ERROR while attempting to load the config file.\n" +
                    "Please check for config file existence and/or fix config file syntax");
        } else {
            System.out.println("Config loaded");
        }
    }
}
