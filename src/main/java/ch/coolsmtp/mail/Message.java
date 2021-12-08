/* Par  : Alice Grunder et Hugo Huart
   Date : 2021-12-07
   Desc : Classe modélisant un message
*/

package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class Message {
    private String message;

    /**
     * Constructeur standard
     */
    public Message() {

    }

    /**
     * Obtient un message aléatoire parmi ceux présents dans le fichier de configuration 'messages'
     *
     * @param filename nom du fichier de configuration 'messages'
     * @return réussite de la requête
     */
    public boolean setRandomMessageFromFile(String filename) {
        String content = Parser.getContentFromFile(filename);
        if (content == null)
            return false;

        String[] messages = content.split("===\n");
        String tmp = messages[new Random().nextInt(messages.length)];

        if (!tmp.startsWith("Subject:"))
            return false;

        /* Prise en charge d'utf-8 dans le sujet */
        String newSubject = tmp.split("\n")[0].split(": ")[1];
        newSubject = String.format("Subject: =?utf-8?B?%s?=\n", Base64.getEncoder().encodeToString(newSubject.getBytes(StandardCharsets.UTF_8)));

        message = tmp.replaceFirst(".*?\n", newSubject);
        System.out.println(message);
        return true;
    }

    /**
     * Getter du message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }
}
