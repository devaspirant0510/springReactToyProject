package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.model.Posts;
import ngod.toy.community.talktalk.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostsRepository repository;

    @Autowired
    public PostService(PostsRepository repository) {
        this.repository = repository;
    }
    public List<Posts> readAllPost(){
        return repository.findAll();
    }
    public Posts writePost(Posts postForm){
        return repository.save(postForm);
    }
    public List<Posts> readAllPosts(){
        return repository.findAll();
    }
}
