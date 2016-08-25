package cc.openhome;

public class User  {
    private String name;
    private String remoteAddr;
    private String userAgent;
    private String data;
    
    public User(String name, String remoteAddr, String userAgent) {
        this.name = name;
        this.remoteAddr = remoteAddr;
        this.userAgent = userAgent;
    }

    public String getName() {
        return name;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getRemoteAddr() + ", " + this.getUserAgent();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
