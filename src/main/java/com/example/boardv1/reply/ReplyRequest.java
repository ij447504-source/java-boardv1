package com.example.boardv1.reply;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class ReplyRequest {

    @Data
    public static class ReplyDTO {

        private Integer boardId;

        @NotBlank(message = "[NotBlank 댓글을 입력해주세요")
        private String comment;
    }

}
