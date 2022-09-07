package ai.ecma.codingbat.controller.cotract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping(value =AttachmentController.BASE_PATH)
public interface AttachmentController {

    String BASE_PATH="/api/attachment";

    @PostMapping("/upload")
    ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request);
}

