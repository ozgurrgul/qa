package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.*;
import java.util.*;

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
    private List<Comment> comments = new ArrayList<>();

    //@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne
    private User lastEditor;

    @Enumerated(EnumType.STRING)
    private BasePostType basePostType;

    public BasePost (BasePostType basePostType) {
        this.basePostType = basePostType;
    }

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

    public void upVote() {
        upVoteCount++;
    }

    public void downVote() {
        downVoteCount++;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int totalVoteCount() {
        return upVoteCount - downVoteCount;
    }

    public boolean isPost() {
        return this.basePostType == BasePostType.POST;
    }


}