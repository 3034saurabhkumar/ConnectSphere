package com.saurabh3034.connectSphere.postsService.service;

import com.saurabh3034.connectSphere.postsService.dto.PostCreateRequestDto;
import com.saurabh3034.connectSphere.postsService.dto.PostDto;
import com.saurabh3034.connectSphere.postsService.entity.Post;
import com.saurabh3034.connectSphere.postsService.exception.ResourceNotFoundException;
import com.saurabh3034.connectSphere.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, long userId) {
        log.info("Creating post for user with id: {}", userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("Getting post with id: {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id " + postId + " not found"));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all posts for user with id: {}", userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

    }
}
