package com.tr.mustafakacar.postyApp.responses;

import com.tr.mustafakacar.postyApp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private long id;
    private long userId;
    private long postId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.postId = like.getPost().getId();
    }
}
