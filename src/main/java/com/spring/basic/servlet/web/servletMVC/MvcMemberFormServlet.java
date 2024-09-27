package com.spring.basic.servlet.web.servletMVC;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet-mvc/member/form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("/jsp/member/join-form.jsp");
        request.getRequestDispatcher("/WEB-INF/views/member/join-form.jsp").forward(request, response);
    }
}
