package com.saurabh3034.connectSphere.postsService.repository;

import com.saurabh3034.connectSphere.postsService.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteAllByIdAndPostId(Long userId, Long postId);
}
