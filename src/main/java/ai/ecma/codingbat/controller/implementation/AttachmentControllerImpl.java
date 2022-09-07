package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.controller.cotract.AttachmentController;
import ai.ecma.codingbat.entity.Attachment;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import ai.ecma.codingbat.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }
}
