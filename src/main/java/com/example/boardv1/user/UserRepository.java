package com.example.boardv1.user;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 안붙이면 생성안돼서 최고화됨됨
@Repository // new가 돼야해서
public class UserRepository {

    // DI
    private final EntityManager em; // final 붙은애들 생성자붙어야하니

    // 회원가입할때 insert
    // 유저객체받기
    public User save(User user) {
        em.persist(user); // 쿼리 x
        return user;
    }

    // 로그인할때 username 으로 조회해서 password 검증, 쿼리 ㅇ
    // 로그인할때 username으로 조회해서 password 검증
    public User findByUsername(String username) {
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        try {
            User findUser = (User) query.getSingleResult();
            return findUser;
        } catch (Exception e) {
            return null;
        }

    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

}
