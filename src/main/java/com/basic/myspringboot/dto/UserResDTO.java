package com.basic.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserResDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    // createdAt >> created_at 로 테이블칼럼에 인식됨

}
