package com.spring.basic.servlet.web.frontController.v3.controller;

import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.MyViewResolver;
import com.spring.basic.servlet.web.frontController.v2.ControllerV2;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MemberFormController implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("member/join-form");
    }
}
