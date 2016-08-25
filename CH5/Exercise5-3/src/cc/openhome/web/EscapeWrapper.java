package cc.openhome.web;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

public class EscapeWrapper extends HttpServletRequestWrapper {
    private Map<String, String> escapeMap;

    public EscapeWrapper(HttpServletRequest request, Map<String, String> escapeMap) {
        super(request);
        this.escapeMap = escapeMap;
    }
    
    @Override
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);
        return doEscape(StringEscapeUtils.escapeHtml(value));
    }
    
    private String doEscape(String parameter) {
        if(parameter == null) {
            return null;
        }
        String result = parameter;
        for(String origin : escapeMap.keySet()) {
            String escape = escapeMap.get(origin);
            result = result.replaceAll(origin, escape);
        }
        return result;
    }
}
