package ch.coolsmtp.mail;

import ch.coolsmtp.util.Parser;

import java.util.Arrays;

public class Group {

    private String sender;
    private String[] victims;

    public Group( String sender, String[] victims) {
        this.sender = sender;
        this.victims = victims;
    }

    public static Group[] parseGroupFromFile(String filename, int nbGrp) {
        String[] victimList = Parser.getLinesFromFile(filename);
        assert victimList != null;
        if(victimList.length < nbGrp*3)
            throw new ArithmeticException("User list doesn't have 3 email adresses for " + nbGrp + " groupes");

        Group[] ret = new Group[nbGrp];

        int vicPerGrp = victimList.length/nbGrp;
        int iterator = 0;
        int i;
        for (i = 0; i < nbGrp - 1; ++i){
            String s = "";
            String[] v = new String[vicPerGrp-1];
            for(int j = 0; j < vicPerGrp; ++j){
                if(j == 0){
                    s = victimList[iterator++];
                } else {
                    v[j-1] = victimList[iterator++];
                }
            }
            ret[i] = new Group(s, v);
        }

        String s = victimList[iterator++];
        String[] v = new String[victimList.length - iterator];
        for(int j = 0; j < v.length; ++j){
            v[j] = victimList[iterator++];
        }

        ret[i] = new Group(s, v);

        return ret;
    }

    public String getSender() {
        return sender;
    }

    public String[] getVictims() {
        return Arrays.copyOf(victims, victims.length);
    }
}
