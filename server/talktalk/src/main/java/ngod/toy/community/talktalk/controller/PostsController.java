package ngod.toy.community.talktalk.controller;

import ngod.toy.community.talktalk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class PostsController {
    PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

}
