package com.example.boardv1.reply;

import org.springframework.stereotype.Service;

import com.example.boardv1.board.Board;
import com.example.boardv1.board.BoardRepository;
import com.example.boardv1.user.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final EntityManager em;

    // boarID = 100
    @Transactional
    public void 댓글쓰기(String comment, Integer boardId, User sessionUser) {
        Reply reply = new Reply();
        reply.setComment(comment);
        // 보드가 아닌 옵셔널로 가져옴.

        // 보드를 갖고옴
        // Board board = boardRepository.findById(boardId)
        // .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 리플라이 5개 가져오기
        // id, 생성날짜는 자동으로 옴
        Board board = em.getReference(Board.class, boardId);
        User user = em.getReference(User.class, sessionUser.getId());
        // User mockUser = em.getReference(User.class, boardId);
        // 위 코드 수정필요 em 오류 1행
        reply.setBoard(board);
        reply.setUser(user);

        replyRepository.save(reply);
    }

    @Transactional
    public void 댓글삭제(int id, Integer sessionUserId) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        // 2. 권한체크
        if (reply.getUser().getId() != sessionUserId)
            throw new RuntimeException("댓글을 삭제할 권한이 없습니다.");

        // 3. 댓글삭제
        replyRepository.delete(reply);
    }

}
