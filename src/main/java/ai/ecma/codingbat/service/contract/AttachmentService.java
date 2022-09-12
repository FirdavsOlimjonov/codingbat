package ai.ecma.codingbat.service.contract;

import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

public interface AttachmentService {
    ApiResult<List<AttachmentDTO>> uploadDB(MultipartHttpServletRequest request);

    ApiResult<List<AttachmentDTO>> uploadFS(MultipartHttpServletRequest request);

    void loadFromDB(UUID id, HttpServletResponse response);

    void loadFromFS(UUID id, HttpServletResponse response);
}
