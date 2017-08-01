package qa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class BasePostRevision extends BasePost  {

    private boolean isApproved;
    private String basePostId; // => updated row's post id

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


}