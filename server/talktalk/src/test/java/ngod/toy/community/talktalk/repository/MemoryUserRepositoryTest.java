package ngod.toy.community.talktalk.repository;

import ngod.toy.community.talktalk.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @Test
    void save() {
        //given
        User user = new User();
        user.setUserDesc("desc");
        user.setProfileUrl("http://image.png");
        user.setPassword("1234");
        user.setUserName("seungho");
        //when
        User result = repository.save(user);
        //then
        Assertions.assertThat(result).isEqualTo(user);
        Assertions.assertThat(repository.userList.size()).isEqualTo(1);
        Assertions.assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void findById() {
        //given
        User user1 = new User();
        user1.setUserDesc("desc");
        user1.setProfileUrl("http://image.png");
        user1.setPassword("1234");
        user1.setUserName("seungho");
        User user2 = new User();
        user1.setUserDesc("desc");
        user1.setProfileUrl("http://image.png");
        user1.setPassword("1234");
        user1.setUserName("seungho");
        repository.save(user1);
        repository.save(user2);
        //when
        User findUser = repository.findById(user1.getId()).get();
        //then
        Assertions.assertThat(findUser).isEqualTo(user1);
        Assertions.assertThat(repository.userList.size()).isEqualTo(2);
    }
}