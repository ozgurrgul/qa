package qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Inheritance(
    strategy = InheritanceType.SINGLE_TABLE
)
@DiscriminatorColumn(name="REF_TYPE")
@DiscriminatorValue("__post")
@DiscriminatorOptions(force = true)
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

    private int downVoteCount;
    private int upVoteCount;

    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User user;

    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Comment> comments = new HashSet<>();

    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User lastEditor;

    public void addTags(Tag tag) {
        getTags().add(tag);
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }
}