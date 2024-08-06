package ngod.toy.community.talktalk.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import ngod.toy.community.talktalk.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        return entityManager.find(Account.class, account.getId());
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("select a from Account as a", Account.class).getResultList();
    }

    @Override
    public Optional<Account> findById(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account == null) {
            return Optional.empty();
        }
        return Optional.of(account);
    }

    @Override
    public Optional<Account> findByUserId(String userId) {
        try{
            Account findAccount = (Account) entityManager.createQuery(

                    "select a from Account as a " +
                            "where a.userId=userId"
            ).getSingleResult();
            return Optional.of(findAccount);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> findByIdByPassword(String userId, String password) {
        try{
            Account findAccount = (Account) entityManager.createQuery(
                    "select a from Account as a " +
                            "where a.userId=userId AND a.password=password"
            ).getSingleResult();
            return Optional.of(findAccount);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}
