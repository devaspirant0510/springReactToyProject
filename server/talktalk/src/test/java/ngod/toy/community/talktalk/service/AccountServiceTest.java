package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.entity.Account;
import ngod.toy.community.talktalk.repository.MemoryUserRepository;
import ngod.toy.community.talktalk.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountServiceTest {
    private AccountRepository repository = new MemoryUserRepository();
    private UserService service = new UserService();

    @Test
    void 기본회원가입테스트() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("12346");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        //when
        Long registeredUserId = service.registerUser(account);
        //then
        Assertions.assertThat(registeredUserId).isEqualTo(account.getId());
    }

    @Test
    void 회원가입_중복된_아이디로가입했을때_예외() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("1234");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        Account duplicatedAccount = new Account();
        duplicatedAccount.setUserName("ngod");
        duplicatedAccount.setUserId("lsh0510");
        duplicatedAccount.setPassword("1234");
        duplicatedAccount.setProfileUrl("https://image.png");
        duplicatedAccount.setUserDesc("ngod");
        //when
        service.registerUser(account);
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.registerUser(duplicatedAccount));
    }

    @Test
    void 로그인성공() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("1234");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        service.registerUser(account);
        //when
        Account loginAccount = service.login("lsh0510", "1234");
        //then
        Assertions.assertThat(loginAccount.getId()).isEqualTo(account.getId());
    }

    @Test
    void 로그인실패_아이디_비밀번호_둘다틀림() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("1234");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        service.registerUser(account);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("asd", "afsd"));
    }
    @Test
    void 로그인실패_아이디만_틀림() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("1234");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        service.registerUser(account);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("asd", "1234"));
    }
    @Test
    void 로그인실패_비번만_틀림() {
        //given
        Account account = new Account();
        account.setUserName("seungho");
        account.setUserId("lsh0510");
        account.setPassword("1234");
        account.setProfileUrl("https://image.png");
        account.setUserDesc("hi");
        service.registerUser(account);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("lsh0510", "1234412"));
    }

}