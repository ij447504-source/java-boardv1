package com.example.boardv1.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // object mapping을 hibernate가 할때 디폴트 생성자를 new한다.
@Data
@Entity // 엔티티있어야 하이버네이티가 테이블도 생성해주고 ~ 다 해쥼
@Table(name = "user_tb")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(unique = true) // pk, uk 일때 인덱스를 만들어준다.
    private String username;

    @Column(nullable = false, length = 100) // 비밀번호 해쉬해서 넣을거라 100자설정정
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt; // Insert 할때 시간 해주면 좋음

}
