package com.tr.mustafakacar.postyApp.responses;

import com.tr.mustafakacar.postyApp.entities.Comment;
import com.tr.mustafakacar.postyApp.entities.Post;
import lombok.Data;

@Data
public class CommentResponse {
    private long id;
    private long userId;
    private String username;
    private String description;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.description = comment.getDescription();
    }
}
