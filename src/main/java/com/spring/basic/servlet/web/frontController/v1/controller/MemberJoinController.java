package com.spring.basic.servlet.web.frontController.v1.controller;

import com.spring.basic.servlet.domain.Member;
import com.spring.basic.servlet.repository.MemberRepositoryImpl;
import com.spring.basic.servlet.web.frontController.v1.ControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberJoinController implements ControllerV1 {

    private MemberRepositoryImpl repository = MemberRepositoryImpl.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(id, pw, username, age);

        repository.save(member);

        request.setAttribute("member", member);

        request.getRequestDispatcher("/WEB-INF/views/member/join-result.jsp").forward(request, response);
    }
}
