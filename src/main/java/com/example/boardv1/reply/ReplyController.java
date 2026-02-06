package com.example.boardv1.reply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boardv1.user.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session; // 1. 세션사용할거야!
    // session = key정보가 들어있음.
    // 유저아이디를 받을 때 사용 뉴를 다시할필요없음
    // session을 따로 만들지는 않음음

    @PostMapping("/replies/save") // 디테일에서 주소를 가져옴
    public String save(ReplyRequest.ReplyDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 세션은 서버쪽에서 만들어진거임! 유저클래스에서 관련정보를 세션통해 얻어낼 수 있는 개념으로 이해하면됨
        if (sessionUser == null)
            throw new RuntimeException("인증되지 않았습니다.");

        replyService.댓글쓰기(reqDTO.getComment(), reqDTO.getBoardId(), sessionUser);
        return "redirect:/boards/" + reqDTO.getBoardId(); //
    }

    // /replies/5/delete?oardId=2
    @PostMapping("/replies/{id}/delete")
    public String delete(@PathVariable("id") int id, @RequestParam("boardId") int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null)
            throw new RuntimeException("인증되지 않았습니다.");

        // 댓글삭제
        replyService.댓글삭제(id, sessionUser.getId());

        return "redirect:/boards/" + boardId;

    }

}
