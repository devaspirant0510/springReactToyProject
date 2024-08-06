package ngod.toy.community.talktalk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String userId;

    private String password;

    private String profileUrl;

    private String userDesc;

    private String createdAt;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
