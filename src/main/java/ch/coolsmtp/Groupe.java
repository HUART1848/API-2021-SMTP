package ch.coolsmtp;

public class Groupe {

    private String sender;
    private String[] victimes;

    public Groupe(String[] v){
        sender = v[0];
        victimes = new String[v.length - 1];
        System.arraycopy(v, 1, victimes, 0 , v.length - 1);
    }

    public Groupe[] makeGroupes(String filePath, int nbrOfGroupes){
        Groupe[] ret = new Groupe[nbrOfGroupes];
        
        return ret;
    }
}
