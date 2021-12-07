package ch.coolsmtp;

public class Config {
    private String address;
    private int port;
    private int nGroups;

    public Config(String address, int port, int nGroups) {
        this.address = address;
        this.port = port;
        this.nGroups = nGroups;
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
}
