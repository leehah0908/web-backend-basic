package com.spring.basic.servlet.web.servletMVC;

import com.spring.basic.servlet.domain.Member;
import com.spring.basic.servlet.repository.MemberRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> memberList = MemberRepositoryImpl.getInstance().getList();

        request.setAttribute("mList", memberList);
        request.getRequestDispatcher("/WEB-INF/views/member/members.jsp").forward(request, response);
    }
}
