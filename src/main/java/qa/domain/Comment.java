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

    @OneToOne
    private User user;
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private BasePost basePost;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
