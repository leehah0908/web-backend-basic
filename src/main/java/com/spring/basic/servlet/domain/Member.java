package com.spring.basic.servlet.domain;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    // 사용자의 입력값을 객체로 포장해서 관리하기 위해
    private String id;
    private String pw;
    private String userName;
    private int age;
}
