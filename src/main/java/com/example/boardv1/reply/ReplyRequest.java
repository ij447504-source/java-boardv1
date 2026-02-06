package com.example.boardv1.reply;

import lombok.Data;

public class ReplyRequest {

    @Data
    public static class ReplyDTO {
        private Integer boardId;
        private String comment;
    }

}
