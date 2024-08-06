package ngod.toy.community.talktalk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @ManyToOne
    private Account account;

    public Post() {
    }

    public Post(Long userId, String title, String content) {
        this.title = title;
        this.content = content;
    }
}
