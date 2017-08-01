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

@Entity
@Data
@NoArgsConstructor
public class Post extends BasePost  {

    private int answerCount;
    private int viewCount;

    @OneToMany()
    private Set<Answer> answers = new HashSet<>();

    //@Audited(targetAuditMode= RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

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
}