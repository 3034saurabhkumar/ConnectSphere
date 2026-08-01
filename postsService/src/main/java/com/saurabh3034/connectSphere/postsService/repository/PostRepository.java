package com.saurabh3034.connectSphere.postsService.repository;

import com.saurabh3034.connectSphere.postsService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
