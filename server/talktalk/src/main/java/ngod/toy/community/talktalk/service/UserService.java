package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.entity.Account;
import ngod.toy.community.talktalk.repository.AccountRepository;
import ngod.toy.community.talktalk.repository.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private AccountRepositoryImpl repository;

    public UserService() {}


    public Long registerUser(Account account) {
        repository.findByUserId(account.getUserId()).ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
        Account result = repository.save(account);
        return result.getId();
    }

    public Long registerUser(String userName, String userId, String password) {
        Account account = new Account();
        account.setUserName(userName);
        account.setUserId(userId);
        account.setPassword(password);
        account.setProfileUrl("https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/773/8548051fcfce80a09534231685454ad4_res.jpeg");
        return registerUser(account);

    }

    public Account login(String userId, String password) {
        if (repository.findByIdByPassword(userId, password).isEmpty()) {
            throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return repository.findByUserId(userId).get();
    }

    public Account getUser(Long userId) {
        return repository.findById(userId).get();
    }
}
