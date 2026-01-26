package com.example.boardv1.Board;

import java.security.PrivateKey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.boardv1.board.Board;
import com.example.boardv1.board.BoardRepository;

@Import(BoardRepository.class)
@DataJpaTest // EntityManger가 ioc에 등록됨
public class BoardReposisotryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save_test() {
        // given
        Board board = new Board();
        board.setTitle("title7");
        board.setContent("content7");

        // when
        boardRepository.save(board);

        // eye
    }

}
