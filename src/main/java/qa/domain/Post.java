package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Post extends BasePost  {

    private int answerCount;
    private int viewCount;

    @OneToMany()
    private List<Answer> answers = new ArrayList<>();

    //@Audited(targetAuditMode= RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();

    public Post() {
        super(BasePostType.POST);
    }

    public void addTags(Tag tag) {
        getTags().add(tag);
    }

    public void addAnswer(Answer answer) {
        answer.setParent(this);
        answers.add(answer);
        increaseAnswerCount();
    }

    public void increaseAnswerCount() {
        this.answerCount++;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Post{" +
                "answerCount=" + answerCount +
                ", viewCount=" + viewCount +
                ", id='" + id + '\'' +
                '}';
    }
}