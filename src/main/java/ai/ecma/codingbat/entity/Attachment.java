package ai.ecma.codingbat.entity;

import ai.ecma.codingbat.entity.template.AbsUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attachment extends AbsUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String contentType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "attachment")
    private AttachmentContent attachmentContent;

    public Attachment(String name, Long size, String contentType, AttachmentContent attachmentContent) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
        this.attachmentContent = attachmentContent;
    }
}
