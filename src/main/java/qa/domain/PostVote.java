package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PostVote extends BaseDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_POST"))
    private Post post;

}