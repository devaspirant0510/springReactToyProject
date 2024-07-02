package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
    public ArrayList<User> userList = new ArrayList<>();
    private Long id = 1L;

    @Override
    public User save(User user) {
        user.setId(id++);
        userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findAny();
    }

    @Override
    public Optional<User> findByIdByPassword(String userId, String password) {
        return  userList.stream().filter(user -> user.getUserId().equals(userId)&&user.getPassword().equals(password)).findAny();
    }
}
