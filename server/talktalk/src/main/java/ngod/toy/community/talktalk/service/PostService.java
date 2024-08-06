package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.entity.Post;
import ngod.toy.community.talktalk.model.PostForm;
import ngod.toy.community.talktalk.repository.PostRepositoryImpl;
import ngod.toy.community.talktalk.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {
    @Autowired
    private PostRepositoryImpl repository;

    public PostService() {}

    public Post writePost(PostForm postForm) {
        System.out.println(postForm);
        Post post = new Post(postForm.getUserId(), postForm.getTitle(), postForm.getContent());
        return repository.save(post);
    }

    public List<Post> readAllPosts() {
        return repository.findAll();
    }
    public Post readOneById(Long id){
        if(repository.findById(id).isEmpty()){
            throw new IllegalStateException("not found error");
        }
        return repository.findById(id).get();
    }
}
