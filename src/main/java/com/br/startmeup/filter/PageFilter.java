package com.br.startmeup.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        String contextPath = request.getContextPath()+"/kanbakn.xhtml" ;

        String uri =  request.getRequestURI();

        if(session.getAttribute("UsuarioLogado") == null && contextPath.equals(uri)){
            response.sendRedirect("index.xhtml");

        }else if(session.getAttribute("UsuarioLogado") != null && !contextPath.equals(uri)){
            response.sendRedirect("kanban.xhtml");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
