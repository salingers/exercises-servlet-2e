package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class SetTag extends TagSupport {
    private String var;
    private Object value;
    private String scope;
    
    public void setVar(String var) {
        this.var = var;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @Override
    public int doStartTag() throws JspException {
        if(scope == null || scope.equals("page")) {
            this.pageContext.setAttribute(var, value, PageContext.PAGE_SCOPE);
        }
        else if(scope.equals("request")) {
            this.pageContext.setAttribute(var, value, PageContext.REQUEST_SCOPE);
        }
        else if(scope.equals("session")) {
            this.pageContext.setAttribute(var, value, PageContext.SESSION_SCOPE);
        }
        else if(scope.equals("application")) {
            this.pageContext.setAttribute(var, value, PageContext.APPLICATION_SCOPE);
        }

        return SKIP_BODY;
    }
}
