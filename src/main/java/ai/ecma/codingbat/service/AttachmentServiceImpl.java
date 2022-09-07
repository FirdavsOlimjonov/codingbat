package ai.ecma.codingbat.service;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class AttachmentServiceImpl implements AttachmentService {
    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return null;
    }
}
