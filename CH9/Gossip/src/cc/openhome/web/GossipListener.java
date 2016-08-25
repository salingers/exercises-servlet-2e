package cc.openhome.web;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import cc.openhome.model.*;

@WebListener
public class GossipListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/gossip");
            ServletContext context = sce.getServletContext();
            context.setAttribute("userService", new UserService(
                    new AccountDAOJdbcImpl(dataSource), new BlahDAOJdbcImpl(dataSource)));
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {}
}
