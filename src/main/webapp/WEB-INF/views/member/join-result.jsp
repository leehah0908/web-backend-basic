<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.spring.basic.servlet.domain.Member" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <h2>회원 가입 성공!</h2>
    <ul>
        <!-- 클래스에 적힌 변수명 그대로 적어줘야 함 -->
        <li>${member.userName}님 환영합니다!</li>
        <li>id: ${member.id}</li>
        <li>나이: ${member.age}세</li>
    </ul>

    <br>
    <a href="/front-controller/v4/members">전체 회원 목록 보기</a>
</body>
</html>