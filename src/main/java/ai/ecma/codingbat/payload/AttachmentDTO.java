package ai.ecma.codingbat.payload;

import ai.ecma.codingbat.entity.Attachment;
import ai.ecma.codingbat.entity.AttachmentContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.bcel.AtAjAttributes;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentDTO {

    private UUID id;

    private String name;

    public static AttachmentDTO mapAttachmentToAttachmentDTO(Attachment attachment) {
        return AttachmentDTO.builder()
                .id(attachment.getId())
                .name(attachment.getName())
                .build();
    }
}
