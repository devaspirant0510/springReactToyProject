package ngod.toy.community.talktalk.service;

import ngod.toy.community.talktalk.model.User;
import ngod.toy.community.talktalk.repository.MemoryUserRepository;
import ngod.toy.community.talktalk.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserRepository repository = new MemoryUserRepository();
    private UserService service = new UserService(repository);

    @Test
    void 기본회원가입테스트() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("12346");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        //when
        Long registeredUserId = service.registerUser(user);
        //then
        Assertions.assertThat(registeredUserId).isEqualTo(user.getId());
    }

    @Test
    void 회원가입_중복된_아이디로가입했을때_예외() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("1234");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        User duplicatedUser = new User();
        duplicatedUser.setUserName("ngod");
        duplicatedUser.setUserId("lsh0510");
        duplicatedUser.setPassword("1234");
        duplicatedUser.setProfileUrl("https://image.png");
        duplicatedUser.setUserDesc("ngod");
        //when
        service.registerUser(user);
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.registerUser(duplicatedUser));
    }

    @Test
    void 로그인성공() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("1234");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        service.registerUser(user);
        //when
        User loginUser = service.login("lsh0510", "1234");
        //then
        Assertions.assertThat(loginUser.getId()).isEqualTo(user.getId());
    }

    @Test
    void 로그인실패_아이디_비밀번호_둘다틀림() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("1234");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        service.registerUser(user);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("asd", "afsd"));
    }
    @Test
    void 로그인실패_아이디만_틀림() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("1234");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        service.registerUser(user);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("asd", "1234"));
    }
    @Test
    void 로그인실패_비번만_틀림() {
        //given
        User user = new User();
        user.setUserName("seungho");
        user.setUserId("lsh0510");
        user.setPassword("1234");
        user.setProfileUrl("https://image.png");
        user.setUserDesc("hi");
        service.registerUser(user);
        //when
        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> service.login("lsh0510", "1234412"));
    }

}