package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class RemoveTag extends TagSupport {
    private String var;
    private String scope;
    
    public void setVar(String var) {
        this.var = var;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @Override
    public int doStartTag() throws JspException {
        if(scope == null || scope.equals("page")) {
            this.pageContext.removeAttribute(var, PageContext.PAGE_SCOPE);
        }
        else if(scope.equals("request")) {
            this.pageContext.removeAttribute(var, PageContext.REQUEST_SCOPE);
        }
        else if(scope.equals("session")) {
            this.pageContext.removeAttribute(var, PageContext.SESSION_SCOPE);
        }
        else if(scope.equals("application")) {
            this.pageContext.removeAttribute(var, PageContext.APPLICATION_SCOPE);
        }
        return SKIP_BODY;
    }
}
