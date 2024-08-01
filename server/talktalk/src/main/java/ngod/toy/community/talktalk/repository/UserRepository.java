package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    Optional<User> findByIdByPassword(String userId,String password);
}
