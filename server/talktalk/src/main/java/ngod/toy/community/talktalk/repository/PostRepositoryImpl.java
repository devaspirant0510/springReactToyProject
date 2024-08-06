package ngod.toy.community.talktalk.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ngod.toy.community.talktalk.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PostRepositoryImpl implements PostsRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Post save(Post post) {
        entityManager.persist(post);
        return entityManager.find(Post.class, post.getId());
    }

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("SELECT p FROM Post AS p", Post.class).getResultList();
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post findPost = entityManager.find(Post.class, id);
        if(findPost==null){
            return Optional.empty();
        }
        return Optional.of(findPost);
    }
}
