package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class BasePost extends BaseDomain  {

    //@Audited
    private String title;

    //@Audited
    private String content;

    private int downVoteCount;
    private int upVoteCount;

    private int commentCount;

    //@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "basePost")
    private Set<Comment> comments = new HashSet<>();

    //@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User lastEditor;

    public void addComment(Comment comment) {
        comment.setBasePost(this);
        comments.add(comment);
        increaseCommentCount();
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }

    public void upVote(User user) {
        upVoteCount++;
    }

    public void downVote(User user, VoteType voteType) {
        downVoteCount++;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


}