package com.mycompany.webapp.security;


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
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "content-type, authtoken");
        response.setHeader("Access-Control-Max-Age", "3600");
        // �����?: ���� ����ϴ���?
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // �����?: �Ʒ� 3���� �����θ� ����ϴ���?
        // response.addHeader("Access-Control-Allow-Origin", "http://S1.ABC.co.kr");
        // response.addHeader("Access-Control-Allow-Origin", "http://S2.ABC.co.kr");
        // response.addHeader("Access-Control-Allow-Origin", "http://S3.ABC.co.kr");
 
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
