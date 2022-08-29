package com.pavlenko.kyrylo.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {

    private static final String LANG = "lang";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getParameter(LANG) != null) {
            req.getSession().setAttribute(LANG, req.getParameter(LANG));
        } else if (req.getSession().getAttribute(LANG) == null) {
            req.getSession().setAttribute(LANG, Locale.ENGLISH);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
