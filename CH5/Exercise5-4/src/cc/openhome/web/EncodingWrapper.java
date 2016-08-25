package cc.openhome.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class EncodingWrapper extends RequestParameterWrapper {
    private String ENCODING;
    public EncodingWrapper(HttpServletRequest request, String ENCODING) {
        super(request);
        this.ENCODING = ENCODING;
    }
    
    @Override
    public String[] getParameterValues(String name) {
        String[] values = getRequest().getParameterValues(name);
        if(values != null) {
            try {
                for(int i = 0; i < values.length; i++) {
                    byte[] b = values[i].getBytes("ISO-8859-1");
                    values[i] = new String(b, ENCODING);
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);   
            }
        }
        return values;
    }
}
