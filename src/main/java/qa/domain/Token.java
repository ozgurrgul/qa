package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Token extends BaseDomain {

    private String tokenValue;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}