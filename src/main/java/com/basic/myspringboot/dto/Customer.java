package com.basic.myspringboot.dto;

import lombok.*;

@NoArgsConstructor       // 기본생성자
@AllArgsConstructor    // 오버로딩된 생성자
@Getter                 //getter
@Setter    //setter
@ToString   //toString
@Builder    // 빌더패턴 객체를 생성할때 항목이많을떄 유연성을주는것이다.
public class Customer {
    private String name;
    private int age;
}