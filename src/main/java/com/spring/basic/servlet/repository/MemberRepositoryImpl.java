package com.spring.basic.servlet.repository;

import com.spring.basic.servlet.domain.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 역할: 실제 데이터베이스에 Member들을 CRUD
// MVC의 Model 담당
public class MemberRepositoryImpl implements MemberRepository {

    // 싱글톤 구현
    private MemberRepositoryImpl() {
    }

    private static MemberRepositoryImpl repo = new MemberRepositoryImpl();

    public static MemberRepository getInstance() {
        return repo;
    }

    private String username = "root"; // db 계정명
    private String password = "mysql"; // db 패스워드
    private String url = "jdbc:mysql://localhost:3306/spring?serverTimezone=Asia/Seoul"; // mysql 주소 형식
    private String driverClassName = "com.mysql.cj.jdbc.Driver"; // db 벤더별 전용 커넥터 클래스

    @Override
    public void save(Member member) {
        // conn.close()를 안해도 됨!
        // DB 접속을 위한 객체
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // 커넥터 드라이버 강제 구동 -> 자바 프로그램과 DB 연동
            Class.forName(driverClassName);

            // 실행할 SQL을 문자열로 작성
            String sql = "insert into tb1_members values(?, ?, ?, ?);";

            // SQL을 실행할 객체를 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 미완성 SQL의 ?값 채우기
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getUserName());
            pstmt.setInt(4, member.getAge());

            // 실행 명령
            // insert, update, delete는 실행 명령 메서드가 똑같음
            // int N = pstmt.executeUpdate(); // 성공시 실행 쿼리문의 개수, 실패시 0
            pstmt.executeUpdate(); // 근데 굳이 안하는게 나음 -> 유효성 검사는 이미 이전에 끝냈어야 함;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> getList() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Class.forName(driverClassName);

            String sql = "select * from tb1_members order by age;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // select문의 실행명령
            // resultSet -> select 쿼리의 결과 집합을 가지고 있는 객체.
            ResultSet rs = pstmt.executeQuery();
            List<Member> memberList = new ArrayList<>();

            while (rs.next()) {
                // next()가 true를 리턴하고, 한 행씩 타겟을 잡아줌
                // 타겟으로 잡힌 행의 각 컬럼을 받아오기
//                String id = rs.getString("id");
//                String pw = rs.getString("pw");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");

                memberList.add(
                        new Member(
                                rs.getString("id"),
                                rs.getString("pw"),
                                rs.getString("name"),
                                rs.getInt("age")
                        )
                );
            }
            return memberList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
