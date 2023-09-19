package com.tr.mustafakacar.postyApp.controllers;

import com.tr.mustafakacar.postyApp.entities.Comment;
import com.tr.mustafakacar.postyApp.entities.Like;
import com.tr.mustafakacar.postyApp.requests.CommentCreateRequest;
import com.tr.mustafakacar.postyApp.requests.CommentUpdateRequest;
import com.tr.mustafakacar.postyApp.requests.LikeCreateRequest;
import com.tr.mustafakacar.postyApp.responses.LikeResponse;
import com.tr.mustafakacar.postyApp.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1.0/likes")
public class LikeController {
@Autowired
    LikeService likeService;


    @PostMapping
    public Like addLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.addLike(likeCreateRequest);
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);
    }
    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable long likeId){
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable long likeId) {
        likeService.deleteLike(likeId);
    }


}
