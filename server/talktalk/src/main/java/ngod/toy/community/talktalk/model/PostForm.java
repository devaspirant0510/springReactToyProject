package ngod.toy.community.talktalk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    private Long userId;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "PostForm{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
