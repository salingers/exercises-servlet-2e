package cc.openhome.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;


@WebFilter(
        urlPatterns = { "/*" }, 
        initParams = { 
                @WebInitParam(name = "CODE_LIST", value = "/WEB-INF/codelist.txt")
        })
public class EscapeFilter implements Filter {
    private Map<String, String> escapeMap;

    public void init(FilterConfig filterConfig)
                  throws ServletException {
        BufferedReader reader = null;
        try {
            String codeList = filterConfig.getInitParameter("CODE_LIST");
            reader = new BufferedReader(
                    new InputStreamReader(
                            filterConfig.getServletContext().getResourceAsStream(codeList)));
            String input = null;
            escapeMap = new HashMap<String, String>();
            while ((input = reader.readLine()) != null) {
                String[] tokens = input.split("\t");
                escapeMap.put(tokens[0], tokens[1]);
            }
        } catch (IOException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
        finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
	public void doFilter(ServletRequest request, 
	                     ServletResponse response, 
	                     FilterChain chain) 
	                          throws IOException, ServletException {
        HttpServletRequest requestWrapper = 
            new EscapeWrapper((HttpServletRequest) request, escapeMap);
        chain.doFilter(requestWrapper, response);
	}

	
    public void destroy() {}
}
