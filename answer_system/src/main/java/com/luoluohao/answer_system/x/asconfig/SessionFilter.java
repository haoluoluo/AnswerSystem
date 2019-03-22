package com.luoluohao.answer_system.x.asconfig;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author  : S_ven
 * Create  : 2018/9/28 下午5:22
 * Describe: SessionFilter
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        res.setContentType("textml;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域访问报错
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers",
                "Origin,No-Cache,X-Requested-With, " +
                        "If-Modified-Since,Pragma,Last-Modified, " +
                        "Cache-Control,Expires,Content-Type,X-E4M-With," +
                        "userId,token,Accept,client_id,uuid,Authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域
        res.setHeader("XDomainRequestAllowed", "1");
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP1.1.
        res.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0.
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
    }

}
