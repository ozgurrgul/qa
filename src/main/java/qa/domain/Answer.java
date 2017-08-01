package qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
//@Audited
public class Answer extends BasePost {

    //@NotAudited
    private boolean isAccepted;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post parent;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}