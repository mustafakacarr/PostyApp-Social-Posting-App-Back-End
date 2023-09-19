package com.tr.mustafakacar.postyApp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    long postId;
    long userId;
}
