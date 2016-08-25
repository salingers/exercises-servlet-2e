package cc.openhome;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class UserAttrListener implements HttpSessionAttributeListener {
    public void attributeRemoved(HttpSessionBindingEvent arg0) {

    }

    public void attributeAdded(HttpSessionBindingEvent se) {
        User user = (User) se.getValue();
        user.setData(user.getName() + " 來自資料庫的資料...");
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {}	
}
