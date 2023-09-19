package com.tr.mustafakacar.postyApp.responses;

import com.tr.mustafakacar.postyApp.entities.Like;
import com.tr.mustafakacar.postyApp.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private long id;
    private long userId;
    private String username;
    private String title;
    private String description;
    private List<LikeResponse> likeList;

    public PostResponse(Post post, List<LikeResponse> likeList) {
        this.id=post.getId();
        this.userId=post.getUser().getId();
        this.username=post.getUser().getUsername();
        this.title=post.getTitle();
        this.description=post.getDescription();
        this.likeList=likeList;
    }
}
