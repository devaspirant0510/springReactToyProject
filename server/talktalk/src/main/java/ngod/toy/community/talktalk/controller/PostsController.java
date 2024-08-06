package ngod.toy.community.talktalk.controller;

import ngod.toy.community.talktalk.entity.Post;
import ngod.toy.community.talktalk.model.PostForm;
import ngod.toy.community.talktalk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class PostsController {
    PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    @ResponseBody
    List<Post> readAllPost() {
        return postService.readAllPosts();
    }

    @GetMapping("/api/post/{id}")
    @ResponseBody
    Post readOnePost(@PathVariable("id") Long id){
        System.out.println(id);
        return postService.readOneById(id);
    }

    @PostMapping("/api/post")
    @ResponseBody
    Post savePosts(@RequestBody PostForm form) {
        return postService.writePost(form);
    }

}
