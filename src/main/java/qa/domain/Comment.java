package qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by ozgur on 7/29/17.
 */
@Entity
@Data
@NoArgsConstructor
public class Comment extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private BasePost basePost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Comment comment = (Comment) o;

        return Objects.equals(getId(), comment.getId());
    }

}
