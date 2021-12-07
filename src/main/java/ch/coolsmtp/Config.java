package ch.coolsmtp;

import ch.coolsmtp.util.Parser;

public class Config {
    private String address;
    private int port;
    private int nGroups;

    public Config() {
        port = -1;
        nGroups = -1;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getNumberOfGroups() {
        return nGroups;
    }

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
}
