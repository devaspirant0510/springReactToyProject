package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class MemoryUserRepository implements AccountRepository {
    public ArrayList<Account> accountList = new ArrayList<>();
    private Long id = 1L;

    @Override
    public Account save(Account account) {
        account.setId(id++);
        accountList.add(account);
        return account;
    }

    @Override
    public List<Account> findAll() {
        return accountList;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountList.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    @Override
    public Optional<Account> findByUserId(String userId) {
        return accountList.stream().filter(user -> user.getUserId().equals(userId)).findAny();
    }

    @Override
    public Optional<Account> findByIdByPassword(String userId, String password) {
        return  accountList.stream().filter(user -> user.getUserId().equals(userId)&&user.getPassword().equals(password)).findAny();
    }
}
