package qa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
public class Role extends BaseDomain {

    @Column(unique=true)
    private String roleName; // ADMIN | USER | ...

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}