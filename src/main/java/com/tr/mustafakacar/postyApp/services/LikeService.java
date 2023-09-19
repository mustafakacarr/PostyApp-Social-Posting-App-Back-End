package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.Like;
import com.tr.mustafakacar.postyApp.entities.Post;
import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.LikeRepository;
import com.tr.mustafakacar.postyApp.requests.LikeCreateRequest;
import com.tr.mustafakacar.postyApp.responses.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Like addLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(likeCreateRequest.getUserId());
        Post post = postService.getPostById(likeCreateRequest.getPostId());
        if (user != null && post != null) {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        } else return null;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList;
        if (userId.isPresent()) {
            likeList = likeRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            likeList = likeRepository.findByPostId(postId);
        } else {
            likeList = likeRepository.findAll();
        }
        return likeList.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());

    }

    public Like getLikeById(long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteLike(long likeId) {
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isPresent()) {
            likeRepository.deleteById(likeId);
        }
    }
}
