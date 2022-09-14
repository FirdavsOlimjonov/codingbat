package ai.ecma.codingbat.entity.template;

import ai.ecma.codingbat.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbsUUIDEntity extends AbsAuditingEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
}
