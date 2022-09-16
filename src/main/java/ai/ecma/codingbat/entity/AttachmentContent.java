package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.entity.template.AbsAuditingEntity;
import ai.ecma.codingbat.entity.template.AbsUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AttachmentContent extends AbsUUIDEntity {
    private byte[] content;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private Attachment attachment;
}
