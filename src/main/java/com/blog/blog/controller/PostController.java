package com.blog.blog.controller;

import com.blog.blog.model.Post;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    PostService postService;
    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/create")
    public Post create(@RequestBody Post post){
        return postService.create(post);
    }

    @GetMapping("/list")
    public List<Post> findAll(){
        return postService.findAll();
    }


}
