package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class MemoryPostsRepository implements PostsRepository{
    List<Post> posts = new ArrayList<>();
    Long id = 1L;

    @Override
    public Post save(Post post) {
        post.setId(id++);
        posts.add(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return posts.stream().filter(data->data.getId().equals(id)).findAny();
    }
}
