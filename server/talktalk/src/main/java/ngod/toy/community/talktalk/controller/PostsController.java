package ngod.toy.community.talktalk.controller;

import jakarta.servlet.http.HttpSession;
import ngod.toy.community.talktalk.model.Posts;
import ngod.toy.community.talktalk.model.User;
import ngod.toy.community.talktalk.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostsController {
    PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    String homePage(HttpSession session, Model model){
        User user = (User)session.getAttribute("user");
        List<Posts> postList = postService.readAllPosts();
        System.out.println(postList);
        model.addAttribute("user",user);
        model.addAttribute("posts",postList);
        return "home";
    }
    @GetMapping("/posts")
    String getPostsView(){
        return "posts";
    }
    @PostMapping("/posts")
    String writePost(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Long userId,
            Model model,
            HttpSession session
    ){
        Posts posts = new Posts();
        posts.setContent(content);
        posts.setUserId(userId);
        posts.setTitle(title);
        Posts writedPOst = postService.writePost(posts);
        System.out.println(writedPOst);
        List<Posts> postList = postService.readAllPosts();
        User user = (User)session.getAttribute("user");
        model.addAttribute("posts",postList);
        model.addAttribute("user",user);
        return "home";

    }
}
