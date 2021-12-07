package ch.coolsmtp;

import ch.coolsmtp.mail.Group;
import ch.coolsmtp.mail.Message;

public class App {
    private static final String CONFIG_PATH = "config/config.txt";
    private static final String USERS_PATH = "config/users.txt";
    private static final String MESSAGES_PATH = "config/messages.txt";

    public static void main(String[] args) {
        System.out.println("=== coolsmtp : SMTP-based app to prank your friends ===");

        /* Config loading */
        Config config = new Config();
        System.out.println("Loading config...");

        if (!config.parseConfigFromFile(CONFIG_PATH)) {
            System.out.println("ERROR: Failure while attempting to load the config file.\n" +
                    "Please check for config file existence and/or fix config file syntax");
            return;
        }

        System.out.println("Config loaded");
        System.out.println(config);

        /* Groups loading */
        System.out.println("Loading groups...");
        Group[] groups;
        try {
            groups = Group.parseGroupFromFile(USERS_PATH, config.getNumberOfGroups());
        } catch (ArithmeticException e) {
            System.out.println("ERROR: There is not enough users for the specified number of groups");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid email address");
            return;
        }
        System.out.println("Groups loaded");

        /* Sending command */
        System.out.println("=== PRANK ===");

        SmtpClient client = new SmtpClient(config.getAddress(), config.getPort());
        Message message = new Message();
        for (int i = 0; i < groups.length; ++i) {
            if (!message.setRandomMessageFromFile(MESSAGES_PATH)) {
                System.out.println("ERROR: Failure while attempting to load the message.\n" +
                        "Please check for the 'messages' file existence and/or fix the 'messages' file syntax");
                return;
            }

            if (!client.sendMailPrank(groups[i].getSender(), groups[i].getVictims(), message.getMessage()))
                System.out.printf("ERROR: Failure while attempting to send prank for Group %d\n", i);
            else
                System.out.printf("OK : Prank for group %d sent\n", i);
        }
    }
}
