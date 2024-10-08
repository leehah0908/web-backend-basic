package com.spring.basic.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.WildcardType;

// WAS에게 이 서블릿을 언제 호출할지 URL을 매핑 (hello라는 요청이 들어오면 서블릿 호출)
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    // 서버가 가동이 될때 WebServlet을 찾고, 생성자를 통해서 미리 생성자를 만들어 놓음
    public HelloServlet() {
        System.out.println("Hello World \n\n\n");
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        // http://localhost:8181/hello를 주소창에 입력하면 호출됨
        // "/hello"를 요청하면 클래스의 메서드가 호출되기 때문
        System.out.println("HelloServlet의 Service 메서드 호출");

        /*
        매핑된 url로 요청이 들어오면 service()가 자동 호출됨
        요청과 응답 처리를 쉽게 도와주는 request, response 객체를 함께 매개값으로 전달함
        */

        // 요청과 함께 넘어오는 쿼리 파라미터의 값을 얻는 메서드 (매개값으로 name을 자목)
        // 없는 파라미터를 요청하면 null 리턴
        String name = request.getParameter("name");
        System.out.println("name = " + name);

        // response를 사용해서 응답에 관련된 여러가지 기능을 사용한다.
        // 응답 헤더 파일 섲렁 및 실제 응답할 데이터(text, html, json)를 지정한다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Hello " + name);
    }
}
