package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryAccountRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @Test
    void save() {
        //given
        Account account = new Account();
        account.setUserDesc("desc");
        account.setProfileUrl("http://image.png");
        account.setPassword("1234");
        account.setUserName("seungho");
        //when
        Account result = repository.save(account);
        //then
        Assertions.assertThat(result).isEqualTo(account);
        Assertions.assertThat(repository.accountList.size()).isEqualTo(1);
        Assertions.assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void findById() {
        //given
        Account account1 = new Account();
        account1.setUserDesc("desc");
        account1.setProfileUrl("http://image.png");
        account1.setPassword("1234");
        account1.setUserName("seungho");
        Account account2 = new Account();
        account1.setUserDesc("desc");
        account1.setProfileUrl("http://image.png");
        account1.setPassword("1234");
        account1.setUserName("seungho");
        repository.save(account1);
        repository.save(account2);
        //when
        Account findAccount = repository.findById(account1.getId()).get();
        //then
        Assertions.assertThat(findAccount).isEqualTo(account1);
        Assertions.assertThat(repository.accountList.size()).isEqualTo(2);
    }
}