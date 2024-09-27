package com.spring.basic.servlet.web.frontController.v2.controller;

import com.spring.basic.servlet.web.frontController.MyViewResolver;
import com.spring.basic.servlet.web.frontController.v1.ControllerV1;
import com.spring.basic.servlet.web.frontController.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/front-controller/v2/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServlet() {
        controllerMap.put("/front-controller/v2/member/form", new MemberFormController());
        controllerMap.put("/front-controller/v2/member/join", new MemberJoinController());
        controllerMap.put("/front-controller/v2/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyViewResolver resolver = controller.process(request, response);
        resolver.render(request, response);
    }
}
