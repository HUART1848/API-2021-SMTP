/* Par  : Alice Grunder et Hugo Huart
   Date : 2021-12-07
   Desc : Classe modélisant la configuration de l'application
*/

package ch.coolsmtp;

import ch.coolsmtp.util.Parser;

public class Config {
    private String address;
    private int port;
    private int nGroups;

    /**
     * Constructeur standard
     */
    public Config() {
        port = -1;
        nGroups = -1;
    }

    /**
     * Getter de l'adresse
     *
     * @return adresse
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter du port
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * Getter du nombre de groupes
     *
     * @return nombre de groupes
     */
    public int getNumberOfGroups() {
        return nGroups;
    }

    /**
     * 'Parsing' du fichier de configuration
     *
     * @param filename nom du fichier de configuration
     * @return nom du fichier de configuration
     */
    public boolean parseConfigFromFile(String filename) {
        String[] lines = Parser.getLinesFromFile(filename);
        if (lines == null || lines.length != 3)
            return false;

        try {
            port = Integer.parseInt(lines[1]);
            nGroups = Integer.parseInt(lines[2]);
        } catch (NumberFormatException e) {
            port = -1;
            nGroups = -1;
            return false;
        }
        address = lines[0];

        return true;
    }

    /**
     * Représente la configuration
     *
     * @return affichage de la configuration
     */
    public String toString() {
        return String.format("=== CONFIG ===\nServer address : %s\nServer port : %d\nNumber of groups : %d\n", address, port, nGroups);
    }
}
