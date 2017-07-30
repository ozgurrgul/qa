package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@MappedSuperclass
public abstract class BasePost extends BaseDomain  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Audited
    private String title;

    @Audited
    private String content;

    private int downVoteCount;
    private int upVoteCount;

    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Comment> comments = new HashSet<>();

    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User lastEditor;

    public void addComment(Comment comment) {
        getComments().add(comment);
    }
}