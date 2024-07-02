package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.model.Posts;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryPostsRepository implements PostsRepository{
    List<Posts> posts = new ArrayList<>();
    Long id = 1L;

    @Override
    public Posts save(Posts post) {
        post.setId(id++);
        posts.add(post);
        return post;
    }

    @Override
    public List<Posts> findAll() {
        return posts;
    }

    @Override
    public Optional<Posts> findById(Long id) {
        return posts.stream().filter(data->data.getId().equals(id)).findAny();
    }
}
