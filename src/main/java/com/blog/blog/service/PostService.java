package com.blog.blog.service;

import com.blog.blog.model.Post;
import com.blog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    PostRepository postRepository;
    @Autowired
    PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public Post create(Post post) {
        return postRepository.save(post);
    }
}
