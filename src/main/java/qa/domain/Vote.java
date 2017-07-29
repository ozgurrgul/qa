package qa.domain;

import javax.persistence.*;

@Entity()
public class Vote extends BaseDomain {

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