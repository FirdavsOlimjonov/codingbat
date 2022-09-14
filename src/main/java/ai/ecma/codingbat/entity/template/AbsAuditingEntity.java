package ai.ecma.codingbat.entity.template;

import ai.ecma.codingbat.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AbsAuditingEntity extends AbsTimestampEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(updatable = false)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    private User updatedBy;
}
