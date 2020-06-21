package com.lucky.blog.common.xss;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            MDC.clear();
            String hashedReqId = generatedReqId(request);
            MDC.put("REQID", hashedReqId);
            response.addHeader("Blog_REQID", hashedReqId);
        } catch (Exception e) {
            log.error("", e);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

    protected String generatedReqId(HttpServletRequest request) {
        String ips = request.getHeader("x-forwarded-for");
        String clientIp;
        if (StringUtils.isNotEmpty(ips)) {
            clientIp = ips.split(",")[0];
        } else {
            clientIp = request.getRemoteHost();
        }
        MDC.put("clientIp", clientIp);
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
