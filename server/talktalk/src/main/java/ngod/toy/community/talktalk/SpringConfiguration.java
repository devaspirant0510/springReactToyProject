package ngod.toy.community.talktalk;

import ngod.toy.community.talktalk.repository.*;
import ngod.toy.community.talktalk.service.PostService;
import ngod.toy.community.talktalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {
    private final DataSource dataSource;

    @Autowired
    public SpringConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    UserService getUserService(){
        return new UserService(getUserRepository());
    }

    @Bean
    UserRepository getUserRepository(){
        return new JDBCUserRepository(dataSource);
    }

    @Bean
    PostService getPostService(){
        return new PostService(getPostRepository());
    }

    @Bean
    PostsRepository getPostRepository(){
        return new JDBCPostsRepository(dataSource);
    }
}
