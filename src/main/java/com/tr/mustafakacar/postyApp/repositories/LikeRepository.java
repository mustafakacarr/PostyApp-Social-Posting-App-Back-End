package com.tr.mustafakacar.postyApp.repositories;

import com.tr.mustafakacar.postyApp.entities.Comment;
import com.tr.mustafakacar.postyApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findByUserId(Optional<Long> userId);

    List<Like> findByPostId(Optional<Long> postId);

    @Query(value = "select 'liked', l.post_id, u.avatar_id, u.username from likes l left join users u on u.id = l.user_id where l.post_id in :postIds order by l.id desc limit 5",nativeQuery = true)
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
