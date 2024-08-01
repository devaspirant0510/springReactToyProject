package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.entity.Posts;
import ngod.toy.community.talktalk.model.PostForm;
import ngod.toy.community.talktalk.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {
    private PostsRepository repository;

    @Autowired
    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    public Posts writePost(PostForm postForm) {
        System.out.println(postForm);
        Posts posts = new Posts(postForm.getUserId(), postForm.getTitle(), postForm.getContent());
        return repository.save(posts);
    }

    public List<Posts> readAllPosts() {
        return repository.findAll();
    }
    public Posts readOneById(Long id){
        if(repository.findById(id).isEmpty()){
            throw new IllegalStateException("not found error");
        }
        return repository.findById(id).get();
    }
}
