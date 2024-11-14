package ru.kpfu.itis.oris.armanov.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "custonFilter")
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String customHeader = httpServletRequest.getHeader("Custom-Header");

        if (customHeader != null && customHeader.equals("customvalue")) {
            System.out.println("Custom header found: " + customHeader);
            chain.doFilter(request, response);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.getWriter().write("Access denied. Custom-Header is missing or invalid.");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
