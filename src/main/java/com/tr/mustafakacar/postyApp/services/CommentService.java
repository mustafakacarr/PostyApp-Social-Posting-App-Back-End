package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.Comment;
import com.tr.mustafakacar.postyApp.entities.Post;
import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.CommentRepository;
import com.tr.mustafakacar.postyApp.requests.CommentCreateRequest;
import com.tr.mustafakacar.postyApp.requests.CommentUpdateRequest;
import com.tr.mustafakacar.postyApp.responses.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    public List<CommentResponse> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> commentList;
        if (userId.isPresent() && postId.isPresent()) {
            commentList = commentRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()) {
            commentList = commentRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            commentList = commentRepository.findByPostId(postId);
        } else {
            commentList = commentRepository.findAll();
        }


        return commentList.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());

    }

    public Comment getCommentById(long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            commentRepository.delete(foundComment);
        }
    }

    public Comment addComment(CommentCreateRequest commentRequest) {
        User user = userService.getUserById(commentRequest.getUserId());
        Post post = postService.getPostById(commentRequest.getPostId());
        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setUser(user);
            comment.setDescription(commentRequest.getDescription());
            comment.setCreateDate(new Date());
            return commentRepository.save(comment);
        } else return null;
    }

    public Comment updateComment(long commentId, CommentUpdateRequest commentUpdateRequest) {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment foundedComment = comment.get();
            foundedComment.setDescription(commentUpdateRequest.getDescription());
            return commentRepository.save(foundedComment);
        } else return null;
    }
}
