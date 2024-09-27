package com.spring.basic.servlet.web.frontController.v4.controller;

import com.spring.basic.servlet.repository.MemberRepositoryImpl;
import com.spring.basic.servlet.web.frontController.Model;
import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;
import com.spring.basic.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MermberDeleteController implements ControllerV4
{
    MemberRepositoryImpl repository = MemberRepositoryImpl.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Model model) {
        String delTarget = paramMap.get("id");
        repository.delete(delTarget);

        return "redirect:/front-controller/v4/members";
    }
}
