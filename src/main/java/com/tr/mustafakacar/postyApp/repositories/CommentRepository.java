package com.tr.mustafakacar.postyApp.repositories;

import com.tr.mustafakacar.postyApp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

    List<Comment> findByUserId(Optional<Long> userId);

    List<Comment> findByPostId(Optional<Long> postId);
    @Query(value = "select 'commented on', c.post_id, u.avatar_id, u.username from comments c left join users u on u.id = c.user_id where c.post_id in :postIds order by c.id desc limit 5", nativeQuery = true)
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
