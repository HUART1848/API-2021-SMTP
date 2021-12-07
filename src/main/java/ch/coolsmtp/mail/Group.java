/* Par  : Alice Grunder et Hugo Huart
   Date : 2021-12-07
   Desc : Classe modélisant un groupe
*/

package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;

import java.util.Arrays;

public class Group {

    private final String sender;
    private final String[] victims;

    /**
     * Constructeur standard
     *
     * @param sender  adresse d'envoi
     * @param victims adresses des victimes
     */
    public Group(String sender, String[] victims) {
        this.sender = sender;
        this.victims = victims;
    }

    /**
     * 'Parsing' du fichier de configurations 'users'
     *
     * @param filename nom du fichier de configuration 'users'
     * @param nbGrp    nombre de groupes à former
     * @return groupes formés
     * @throws ArithmeticException      si mauvais nombre de groupes
     * @throws IllegalArgumentException si mauvaise adresse mail d'un utilisateur
     */
    public static Group[] parseGroupFromFile(String filename, int nbGrp) throws ArithmeticException, IllegalArgumentException {
        String[] victimList = Parser.getLinesFromFile(filename);
        assert victimList != null;
        if (victimList.length < nbGrp * 3)
            throw new ArithmeticException();

        for (String email : victimList) {
            if (!email.matches("^(.+)@(.+)$"))
                throw new IllegalArgumentException();
        }

        Group[] ret = new Group[nbGrp];

        int vicPerGrp = victimList.length / nbGrp;
        int iterator = 0;
        int i;
        for (i = 0; i < nbGrp - 1; ++i) {
            String s = "";
            String[] v = new String[vicPerGrp - 1];
            for (int j = 0; j < vicPerGrp; ++j) {
                if (j == 0) {
                    s = victimList[iterator++];
                } else {
                    v[j - 1] = victimList[iterator++];
                }
            }
            ret[i] = new Group(s, v);
        }

        String s = victimList[iterator++];
        String[] v = new String[victimList.length - iterator];
        for (int j = 0; j < v.length; ++j) {
            v[j] = victimList[iterator++];
        }

        ret[i] = new Group(s, v);

        return ret;
    }

    /**
     * Getter de l'adresse d'envoi
     *
     * @return adresse d'envoi
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter des adresses de victimes
     *
     * @return adresses de victimes
     */
    public String[] getVictims() {
        return Arrays.copyOf(victims, victims.length);
    }
}
