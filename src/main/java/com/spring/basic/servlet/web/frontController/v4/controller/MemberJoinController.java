package com.spring.basic.servlet.web.frontController.v4.controller;

import com.spring.basic.servlet.domain.Member;
import com.spring.basic.servlet.repository.MemberRepositoryImpl;
import com.spring.basic.servlet.web.frontController.Model;
import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;
import com.spring.basic.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MemberJoinController implements ControllerV4 {

    private MemberRepositoryImpl repository = MemberRepositoryImpl.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Model model) {
        String id = paramMap.get("id");
        String pw = paramMap.get("pw");
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(id, pw, username, age);
        repository.save(member);

        model.addAttribute("member", member);
        return "member/join-result";
    }
}
