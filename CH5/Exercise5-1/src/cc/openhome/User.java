package cc.openhome;

public class User  {
    private String name;
    private String remoteAddr;
    private String userAgent;

    
    
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

  
}
