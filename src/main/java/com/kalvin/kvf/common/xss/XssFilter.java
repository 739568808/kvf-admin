package com.kalvin.kvf.common.xss;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 * Create by Kalvin on 2019/6/25.
 */
@Slf4j
public class XssFilter implements Filter {

    private String[] excludedUrls;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init XssFilter...");
        excludedUrls = filterConfig.getInitParameter("notice").split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpRequestWrapper xssRequest = new XssHttpRequestWrapper((HttpServletRequest) request);
        String url = xssRequest.getServletPath();
        if (isExcludedUrl(url)){
            chain.doFilter(request, response);
        }else {
            chain.doFilter(xssRequest, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isExcludedUrl(String url){
        if (excludedUrls==null || excludedUrls.length==0){
            return false;
        }
        for (String ex:excludedUrls){
            url = url.trim();
            ex = ex.trim();
            if (url.toLowerCase().matches(ex.toLowerCase().replace("*",".*"))){
                return true;
            }
        }
        return false;
    }
}
