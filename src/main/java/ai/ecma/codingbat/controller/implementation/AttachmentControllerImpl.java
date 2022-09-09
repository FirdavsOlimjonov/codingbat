package ai.ecma.codingbat.controller.implementation;

import ai.ecma.codingbat.controller.cotract.AttachmentController;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import ai.ecma.codingbat.service.contract.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {

    private final AttachmentService attachmentService;

    @Override
    public ApiResult<List<AttachmentDTO>> uploadDB(MultipartHttpServletRequest request) {
        return attachmentService.uploadDB(request);
    }

    @Override
    public ApiResult<List<AttachmentDTO>> uploadFS(MultipartHttpServletRequest request) {
        return attachmentService.uploadFS(request);
    }

    @Override
    public void getFileFromDB(UUID id, HttpServletResponse response) {
        attachmentService.loadFromDB(id,response);
    }

    @Override
    public void getFileFromFS(UUID id, HttpServletResponse response) {
        attachmentService.loadFromFS(id,response);
    }
}
