package cc.openhome.tag;

import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EachImageTag extends SimpleTagSupport {
    private String var;
    private String dir;

    @Override
    public void doTag() throws JspException {
        try {
            PageContext pageContext = (PageContext) this.getJspContext();
            Iterator<String> images = pageContext.getServletContext().getResourcePaths(dir).iterator();
            while(images.hasNext()) {
                pageContext.setAttribute(var, "." + images.next());
                this.getJspBody().invoke(null);
            }
        } catch (java.io.IOException ex) {
            throw new JspException(ex);
        }
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
