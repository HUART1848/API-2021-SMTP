package ch.coolsmtp;

import ch.coolsmtp.mail.Group;
import ch.coolsmtp.mail.Message;

public class App {
    public static void main(String[] args) {
        System.out.println("=== coolsmtp : SMTP-based app to prank your friends ===");

        /* Config loading */
        Config config = new Config();
        System.out.println("Loading config...");

        if (!config.parseConfigFromFile("config/config.txt")) {
            System.out.println("ERROR while attempting to load the config file.\n" +
                    "Please check for config file existence and/or fix config file syntax");
            return;
        }

        System.out.println("Config loaded");
        System.out.println(config);

        /* Groups loading */
        Group[] groups;
        try {
            groups = Group.parseGroupFromFile("config/users.txt", config.getNumberOfGroups());
        } catch (ArithmeticException e) {
            System.out.printf("Erreur : %s", e.getMessage());
            return;
        }

        /* Sending command */
        SmtpClient client = new SmtpClient(config.getAddress(), config.getPort());
        Message message = new Message();
        for (int i = 0; i < groups.length; ++i) {
            if (!message.setRandomMessageFromFile("config/messages.txt")) {
                System.out.println("ERROR while attempting to load the message.\n" +
                        "Please check for 'messages.txt' file existence and/or fix 'messages.txt' file syntax");
                return;
            }

            if (!client.sendMailPrank(groups[i].getSender(), groups[i].getVictims(), message.getMessage()))
                System.out.printf("Error sending prank for Group %d\n", i);
            else
                System.out.printf("Prank for group %d sent\n", i);
        }
    }
}
