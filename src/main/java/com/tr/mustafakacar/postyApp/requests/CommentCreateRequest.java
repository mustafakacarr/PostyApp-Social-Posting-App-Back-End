package com.tr.mustafakacar.postyApp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    long userId;
    long postId;
    String description;
}
