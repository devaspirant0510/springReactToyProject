package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Posts;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    Posts save(Posts post);
    List<Posts> findAll();
    Optional<Posts> findById(Long id);
}
