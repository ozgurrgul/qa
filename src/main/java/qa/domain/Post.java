package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Post extends BaseDomain  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Audited
    private String title;

    @Audited
    private String content;

    private int viewCount;
    private int answerCount;
    private int commentCount;

    private int upVoteCount;

    private int downVoteCount;

    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Post> answers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @Audited
    @OneToOne
    private User lastEditor;

    public void addTags(Tag tag) {
        getTags().add(tag);
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }
}