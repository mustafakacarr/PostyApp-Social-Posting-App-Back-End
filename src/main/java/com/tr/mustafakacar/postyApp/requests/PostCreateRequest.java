package com.tr.mustafakacar.postyApp.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    long id;
    String title;
    String description;
    long userId;
}
