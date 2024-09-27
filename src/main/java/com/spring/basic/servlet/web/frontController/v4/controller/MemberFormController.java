package com.spring.basic.servlet.web.frontController.v4.controller;

import com.spring.basic.servlet.web.frontController.Model;
import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;
import com.spring.basic.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MemberFormController implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Model model) {
        return "member/join-form";
    }
}
