//package com.dfcu.configs;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//
//import java.io.IOException;
//
//@Component
//public class CSPFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
////        httpResponse.setHeader("Content-Security-Policy", "default-src 'self'; img-src 'self' http://127.0.0.1:5501;");
//        httpResponse.setHeader("Content-Security-Policy",
//                "default-src 'self'; " +
//                        "script-src 'self' 'unsafe-inline'; " +
//                        "img-src 'self' http://127.0.0.1:5501; " +
//                        "style-src 'self';");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void destroy() {}
//}
