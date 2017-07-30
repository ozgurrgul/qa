package qa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String roleName; // ADMIN | USER | ...

    public Role(String roleName) {
        this.roleName = roleName;
    }

}