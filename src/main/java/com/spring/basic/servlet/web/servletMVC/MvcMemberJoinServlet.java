package com.spring.basic.servlet.web.servletMVC;

import com.spring.basic.servlet.domain.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet-mvc/member/join")
public class MvcMemberJoinServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(id, pw, username, age);

        // View 역할은 jsp가 할 것이기 때문에 jsp에게 데이터를 넘겨줘야 함
        // request.setAttribute("key", value): request 객체에 데이터를 보관해서 view로 넘기기
        request.setAttribute("member", member);

        // request에 데이터를 담아서 view로 전달할 때는 sendRedirect를 사용하면 안됨
        // sendRedirect는 응답이 나갔다가 재요청이 들어오면서, 기존 request가 사라짐
        // response.sendRedirect("/jsp/member/join-result.jsp");

        // forward를 사용해서 요청을 view로 위임하는 기법을 사용해야 함
        RequestDispatcher dp = request.getRequestDispatcher("/jsp/member/join-result.jsp");
        dp.forward(request, response);

    }
}
