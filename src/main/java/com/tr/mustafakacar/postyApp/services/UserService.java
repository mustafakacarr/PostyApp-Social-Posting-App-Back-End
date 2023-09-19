package com.tr.mustafakacar.postyApp.services;

import com.tr.mustafakacar.postyApp.entities.User;
import com.tr.mustafakacar.postyApp.repositories.CommentRepository;
import com.tr.mustafakacar.postyApp.repositories.LikeRepository;
import com.tr.mustafakacar.postyApp.repositories.PostRepository;
import com.tr.mustafakacar.postyApp.repositories.UserRepository;
import com.tr.mustafakacar.postyApp.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(long userId) {

        //ADD CUSTOM EXCEPTION
        return userRepository.findById(userId).orElse(null);
    }

    public UserResponse getUserByIdWithSpecialResponse(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return new UserResponse(user.getId(), user.getUsername(), user.getAvatarId());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(long userId, User toUpdateUserObject) {
        //ADD CUSTOM EXCEPTION
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            if (toUpdateUserObject.getUsername() != null) {
                foundUser.setUsername(toUpdateUserObject.getUsername());
            }
            if (toUpdateUserObject.getPassword() != null) {
                foundUser.setPassword(toUpdateUserObject.getPassword());
            }
            foundUser.setAvatarId(toUpdateUserObject.getAvatarId());


            return userRepository.save(foundUser);
        } else return null;
    }

    public void deleteUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            userRepository.delete(foundUser);
        }
    }

    public List<Object> getActivities(long userId) {
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if (postIds.isEmpty()) return null;
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> result = new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;
    }
}
