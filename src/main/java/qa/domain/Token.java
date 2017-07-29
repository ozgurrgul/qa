package qa.domain;

import javax.persistence.*;

@Entity
public class Token extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tokenValue;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    protected Token() {
    }

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
}