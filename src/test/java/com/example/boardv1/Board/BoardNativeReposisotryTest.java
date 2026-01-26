package com.example.boardv1.Board;

import java.security.Timestamp;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.boardv1.board.Board;
import com.example.boardv1.board.BoardNativeRepository;

@Import(BoardNativeRepository.class)
@DataJpaTest // EntityManger가 ioc에 등록됨
public class BoardNativeReposisotryTest {

    @Autowired // 어노테이션 DI 기법
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardNativeRepository.findById(id);

        // eye
        System.out.println(board);
    }

    @Test
    public void findAll_test() {
        // when
        List<Board> list = boardNativeRepository.findAll();

        // eye
        for (Board board : list) {
            System.out.println(board);

        }
    }

    @Test
    public void save_test() {
        // given 매서드에 들어갈 데이터 7넣기기

        String title = "title7";
        String content = "content7";

        // 1. when 검사할 메서드, void는 돌려줄것이 없어 Board board 할 필요없음.
        boardNativeRepository.save(title, content);
        // eye 정상검증
        findAll_test();
    }

    @Test
    public void deleteById_test() {
        // // given
        // int id = 1;// 생성, {}안에서만 동작
        // // when
        // boardNativeRepository.deleteById(id);
        // // eye
        // findAll_test();

    }

    @Test
    public void updateById_test() {
        // given
        int id = 6;// 검색용키
        String title = "title10";
        String content = "content10";

        // when
        boardNativeRepository.updateById(id, title, content);
        // eye
        findAll_test();

    }
}