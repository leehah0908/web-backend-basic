package com.spring.basic.servlet.web.frontController.v3.controller;

import com.spring.basic.servlet.repository.MemberRepositoryImpl;
import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;

import java.util.Map;

public class MermberDeleteController implements ControllerV3
{
    MemberRepositoryImpl repository = MemberRepositoryImpl.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String delTarget = paramMap.get("id");
        repository.delete(delTarget);

        return new ModelView("redirect:/front-controller/v3/members");
    }
}
