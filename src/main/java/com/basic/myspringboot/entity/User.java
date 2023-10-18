package com.basic.myspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate; // 이름만변경하는데 로그에 이메일도변경된다고뜨는것해결
                                                // Hibernate: update users set name=? where id=?
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter @Setter
@DynamicUpdate    // 이름만변경하는데 로그에 이메일도변경된다고뜨는것해결 // Hibernate: update users set name=? where id=?
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}