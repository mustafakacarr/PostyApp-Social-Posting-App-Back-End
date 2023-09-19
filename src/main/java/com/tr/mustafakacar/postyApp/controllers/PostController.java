package com.tr.mustafakacar.postyApp.controllers;

import com.tr.mustafakacar.postyApp.entities.Post;
import com.tr.mustafakacar.postyApp.requests.PostCreateRequest;
import com.tr.mustafakacar.postyApp.requests.PostUpdateRequest;
import com.tr.mustafakacar.postyApp.responses.PostResponse;
import com.tr.mustafakacar.postyApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1.0/posts")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping
    public Post addPost(@RequestBody PostCreateRequest postRequest) {
        return postService.addPost(postRequest);
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }
    @GetMapping("/{postId}")
    public PostResponse getPostById(@PathVariable long postId) {
        return postService.getPostByIdWithLikes(postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
    }
    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable long postId, @RequestBody PostUpdateRequest updateRequest) {
        return postService.updatePost(postId,updateRequest);
    }

}
