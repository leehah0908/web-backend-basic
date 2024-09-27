package com.spring.basic.servlet.web.frontController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyViewResolver {

    private String viewPath; // forword 할 경로
    private String prefix; // 경로에 있는 공통 접두사
    private String suffix; // 경로에 있는 공통 접미사
    private boolean redirect; // redirect의 여부

    // 전달되는 viewName은 공통점이 있음 -> jps 파일명만 다르고 "/WEB-INF/views/"의 경로는 동일함, 확장자가 .jsp인 것도 같음
    public MyViewResolver(String viewPath) {
        this.prefix = "/WEB-INF/views/";
        this.suffix = ".jsp";

        if (!isRedirect(viewPath)) {
            // redirect 아님
            this.viewPath = prefix + viewPath + suffix;
        } else {
            // redirect
            this.viewPath = viewPath.substring(viewPath.indexOf(":") + 1);
        }
    }

    // 기존의 render는 forward만 했지만 이제는 상황에 따라 sendRedirect도 해야 함 -> rediredct 필드를 확인
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!this.redirect) {
            request.getRequestDispatcher(viewPath).forward(request, response);
        } else {
            response.sendRedirect(viewPath);
        }
    }

    // 전달된 view 요청이 redirect인지 확인
    private boolean isRedirect(String viewName) {
        // redirect 요청은 redirect:로 시작
        this.redirect = viewName.startsWith("redirect:");
        return this.redirect;
    }
}
