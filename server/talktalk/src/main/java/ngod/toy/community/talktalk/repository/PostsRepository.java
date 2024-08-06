package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(Long id);
}
