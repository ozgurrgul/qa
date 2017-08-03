package qa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class PostVote extends BaseDomain {

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @JsonIgnore
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @JsonIgnore
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_POST"))
    private BasePost post;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}