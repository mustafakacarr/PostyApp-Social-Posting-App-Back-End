package com.tr.mustafakacar.postyApp.controllers;

import com.tr.mustafakacar.postyApp.entities.Comment;
import com.tr.mustafakacar.postyApp.requests.CommentCreateRequest;
import com.tr.mustafakacar.postyApp.requests.CommentUpdateRequest;
import com.tr.mustafakacar.postyApp.responses.CommentResponse;
import com.tr.mustafakacar.postyApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1.0/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping
    public Comment addComment(@RequestBody CommentCreateRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllComments(userId,postId);
    }
    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable long commentId) {
        return commentService.getCommentById(commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deletePost(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
    }
    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(commentId,commentUpdateRequest);
    }


}
