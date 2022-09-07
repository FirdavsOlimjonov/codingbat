package ai.ecma.codingbat.service;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {
    ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request);
}
