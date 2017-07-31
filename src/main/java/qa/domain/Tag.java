package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Tag extends BaseDomain {

    private String tagName;
    private String iconUrl;
    private String description;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}