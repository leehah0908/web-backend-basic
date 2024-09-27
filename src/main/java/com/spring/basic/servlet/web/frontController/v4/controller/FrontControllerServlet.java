package com.spring.basic.servlet.web.frontController.v4.controller;

import com.spring.basic.servlet.web.frontController.Model;
import com.spring.basic.servlet.web.frontController.ModelView;
import com.spring.basic.servlet.web.frontController.v3.ControllerV3;
import com.spring.basic.servlet.web.frontController.v4.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/front-controller/v4/*")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServlet() {
        controllerMap.put("/front-controller/v4/member/form", new MemberFormController());
        controllerMap.put("/front-controller/v4/member/join", new MemberJoinController());
        controllerMap.put("/front-controller/v4/member/delete", new MermberDeleteController());
        controllerMap.put("/front-controller/v4/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 요청과 함꼐 전달된 데이터(요청 파라미터)를 전부 읽어서 맵에 담아 리턴
        Map<String, String> paramMap = createParamMap(request);

        // 하위 controller에게 모델을 전달해야 함
        Model model = new Model();
        String viewName = controller.process(paramMap, model);
        // 각 controller가 리턴한 viewName과 전달했던 model을 modelView에 일괄 세팅
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        // jsp쪽에서 좀 더 쉽게 데이터를 꺼낼 수 있게 Model이 가진 맵을 순회해서 request 객체에 데이터를 세팅
        Map<String, Object> modelMap = mv.getModel().getModelMap();
        for (String key : modelMap.keySet()) {
            request.setAttribute(key, modelMap.get(key));
        }
        // view 렌더링
        mv.render(request, response);
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        // 요청과 함께 전달된 모든 파라미터를 리턴
        Enumeration<String> parameterNames = request.getParameterNames();
        // 각각의 컨트롤러에게 전달할 파라미터를 담을 맵
        Map<String, String> paramMap = new HashMap<>();

        // 파라미터 요소가 더 이상 조회가 되지 않을 떄까지 반복문을 순회
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement(); // key얻기
            String value = request.getParameter(key);  // key를 통해 value 얻기
            paramMap.put(key, value); // 맵에 담기
        }
        return paramMap;
    }
}
