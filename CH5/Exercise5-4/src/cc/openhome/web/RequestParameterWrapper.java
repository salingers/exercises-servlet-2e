package cc.openhome.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestParameterWrapper extends HttpServletRequestWrapper {

    public RequestParameterWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if(values == null) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Enumeration<String> names = getRequest().getParameterNames();
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = getParameterValues(name);
            parameterMap.put(name, values);
        }
        return parameterMap;
    }

}