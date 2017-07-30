package qa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ozgur on 7/29/17.
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseDomain {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;
    private String content;

    @OneToOne
    private Post parent;


}
