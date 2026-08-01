package com.saurabh3034.connectSphere.postsService.service;

import com.saurabh3034.connectSphere.postsService.entity.Post;
import com.saurabh3034.connectSphere.postsService.entity.PostLike;
import com.saurabh3034.connectSphere.postsService.exception.BadRequestException;
import com.saurabh3034.connectSphere.postsService.exception.ResourceNotFoundException;
import com.saurabh3034.connectSphere.postsService.repository.PostLikeRepository;
import com.saurabh3034.connectSphere.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L;
        log.info("User with ID: {} Liking post with id {}", userId, postId);
        postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post with id " + postId + " not found"));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (hasAlreadyLiked) {
            throw new BadRequestException("Post with id " + postId + " is already liked");
        }

        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLikeRepository.save(postLike);

        // TODO: send notifications to the owner of the post.
    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        log.info("User with ID: {} Unliking post with id {}", userId, postId);
        postRepository.findById(postId).orElseThrow(()
            -> new ResourceNotFoundException("Post with id " + postId + " not found"));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if(!hasAlreadyLiked) {
            throw new BadRequestException("Post with id " + postId + " is not liked");
        }

        postLikeRepository.deleteAllByIdAndPostId(userId, postId);
    }
}
