package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.entity.User;
import ngod.toy.community.talktalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Long registerUser(User user) {
        repository.findByUserId(user.getUserId()).ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
        User result = repository.save(user);
        return result.getId();
    }

    public Long registerUser(String userName, String userId, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setUserId(userId);
        user.setPassword(password);
        user.setProfileUrl("https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/773/8548051fcfce80a09534231685454ad4_res.jpeg");
        return registerUser(user);

    }

    public User login(String userId, String password) {
        if (repository.findByIdByPassword(userId, password).isEmpty()) {
            throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return repository.findByUserId(userId).get();
    }

    public User getUser(Long userId) {
        return repository.findById(userId).get();
    }
}
