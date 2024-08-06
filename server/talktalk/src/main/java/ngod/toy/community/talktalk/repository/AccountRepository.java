package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> findByUserId(String userId);
    Optional<Account> findByIdByPassword(String userId, String password);
}
