/* Par  : Alice Grunder et Hugo Huart
   Date : 2021-12-07
   Desc : Classe utilitaire fournissant des fonctions de 'parsing'
*/

package ch.coolsmtp.util;

import java.io.*;
import java.nio.charset.*;

public class Parser {
    private static final Charset STANDARD_CHARSET = StandardCharsets.UTF_8;

    /**
     * Obtient le contenu d'un fichier
     *
     * @param filename nom du fichier
     * @return contenu du fichier
     */
    public static String getContentFromFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), STANDARD_CHARSET));

            StringBuilder out = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
                out.append(String.format("%s\n", line));
            return out.toString();
        } catch (IOException e) {
            System.out.printf("Erreur : %s", e.getMessage());
            return null;
        }
    }

    /**
     * Obtient les lignes d'un fichier
     *
     * @param filename nom du fichier
     * @return tableau des lignes du fichier
     */
    public static String[] getLinesFromFile(String filename) {
        String content = getContentFromFile(filename);
        return content == null ? null : content.split("\\n");
    }
}
