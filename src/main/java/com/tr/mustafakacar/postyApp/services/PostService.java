package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.Post;
import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.PostRepository;
import com.tr.mustafakacar.postyApp.requests.PostCreateRequest;
import com.tr.mustafakacar.postyApp.requests.PostUpdateRequest;
import com.tr.mustafakacar.postyApp.responses.LikeResponse;
import com.tr.mustafakacar.postyApp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    public Post addPost(PostCreateRequest postRequest) {
        User user = userService.getUserById(postRequest.getUserId());
        if (user == null) {
            return null;
        } else {
            Post post = new Post();
            post.setUser(user);
            post.setDescription(postRequest.getDescription());
            post.setTitle(postRequest.getTitle());
            post.setCreateDate(new Date());
            return postRepository.save(post);
        }
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent())
            list = postRepository.findByUserId(userId.get());
        else list = postRepository.findAll();
        return list.stream().map(post -> {
            List<LikeResponse> likeList = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(post.getId()));
            return new PostResponse(post, likeList);
        }).collect(Collectors.toList());
    }

    public Post getPostById(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        return post;

    }

    public PostResponse getPostByIdWithLikes(long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<LikeResponse> likeList = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(post.getId()));
        return new PostResponse(post, likeList);

    }

    public void deletePost(long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post foundPost = post.get();
            postRepository.delete(foundPost);
        }
    }

    public Post updatePost(long postId, PostUpdateRequest updateRequest) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {

            Post foundedPost = post.get();
            foundedPost.setTitle(updateRequest.getTitle());
            foundedPost.setDescription(updateRequest.getDescription());
            return postRepository.save(foundedPost);
        } else return null;


    }
}
