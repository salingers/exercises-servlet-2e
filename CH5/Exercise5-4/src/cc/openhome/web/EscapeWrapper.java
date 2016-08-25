package cc.openhome.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

public class EscapeWrapper extends RequestParameterWrapper {
    private Map<String, String> escapeMap;

    public EscapeWrapper(HttpServletRequest request,
            Map<String, String> escapeMap) {
        super(request);
        this.escapeMap = escapeMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = getRequest().getParameterValues(name);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                values[i] = doEscape(StringEscapeUtils.escapeHtml(values[i]));
            }
        }
        return values;
    }

    private String doEscape(String parameter) {
        if (parameter == null) {
            return null;
        }
        String result = parameter;
        for (String origin : escapeMap.keySet()) {
            String escape = escapeMap.get(origin);
            result = result.replaceAll(origin, escape);
        }
        return result;
    }
}
