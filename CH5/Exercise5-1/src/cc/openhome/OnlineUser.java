package cc.openhome;

import java.util.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

@WebListener
public class OnlineUser implements HttpSessionListener {
    private static Map<String, HttpSession> sessions
                       = new HashMap<String, HttpSession>();

    public static Map<String, HttpSession> getSessions() {
        return sessions;
    }

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        getSessions().put(session.getId(), session);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        getSessions().remove(se.getSession().getId());
    }

}
