package com.spring.basic.servlet.web.frontController.v1.controller;

import ch.qos.logback.classic.net.SocketNode;
import com.spring.basic.servlet.web.frontController.v1.ControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/front-controller/v1/*")
public class FrontControllerServlet extends HttpServlet {

    // 각 컨트롤러들을 모아놓을 Map 생성
    // key를 url로 주어서 각 요청마다 명령을 내릴 컨트롤러를 구분
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 객체 초기화시 url에 따라 사용할 컨트롤러 초기화
    public FrontControllerServlet() {
        controllerMap.put("/front-controller/v1/member/form", new MemberFormController());
        controllerMap.put("/front-controller/v1/member/join", new MemberJoinController());
        controllerMap.put("/front-controller/v1/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer requestURL = request.getRequestURL();

        // 요청 URL에 맞는 컨트롤러를 앱에서 꺼내기
        ControllerV1 controller = controllerMap.get(requestURL);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 컨트롤러 실행 (인터페이스의 구현체들이기 때문에 동일한 메서드로 호출 가능)
        controller.process(request, response);
    }
}
