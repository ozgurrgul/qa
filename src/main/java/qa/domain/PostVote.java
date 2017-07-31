package qa.domain;

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

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_POST"))
    private BasePost post;


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}