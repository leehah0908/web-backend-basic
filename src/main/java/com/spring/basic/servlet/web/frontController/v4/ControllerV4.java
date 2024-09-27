package com.spring.basic.servlet.web.frontController.v4;

import com.spring.basic.servlet.web.frontController.Model;

import java.util.Map;

/*
이 인터페이스를 구현하는 다양한 하위 컨트롤러들이 항상 request, response를 사용하는 것이 아님
요청, 응답 정보 처리를 FrontController에게 위임
*/
public interface ControllerV4 {
    /**
     * 요청이 들어오면 적절한 처리를 수행
     *
     * @param paramMap: 요청 정보(뭐리 파라미터)를 모두 맵에 담음 (FrontController)
     * @return - 응답시 보여줄 화면 처리 객체(ViewResolver)와 화면 처리를 위해 사용할 데이터(Model)를 일괄적으로 처리하는 객체(ModelView)
     */
    String process(Map<String, String> paramMap, Model model);
}
