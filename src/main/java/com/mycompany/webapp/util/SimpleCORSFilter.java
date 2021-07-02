package com.mycompany.webapp.util;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
 
@Component
public class SimpleCORSFilter implements Filter {
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "content-type, authtoken");
        response.setHeader("Access-Control-Max-Age", "3600");
        // ï¿½ï¿½ï¿½ï¿½ï¿?: ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Ï´ï¿½ï¿½ï¿?
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // ï¿½ï¿½ï¿½ï¿½ï¿?: ï¿½Æ·ï¿½ 3ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Î¸ï¿½ ï¿½ï¿½ï¿½ï¿½Ï´ï¿½ï¿½ï¿?
        // response.addHeader("Access-Control-Allow-Origin", "http://S1.ABC.co.kr");
        // response.addHeader("Access-Control-Allow-Origin", "http://S2.ABC.co.kr");
        // response.addHeader("Access-Control-Allow-Origin", "http://S3.ABC.co.kr");
 
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
