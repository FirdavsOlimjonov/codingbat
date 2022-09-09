package ai.ecma.codingbat.service.implemention;

import ai.ecma.codingbat.entity.Attachment;
import ai.ecma.codingbat.entity.AttachmentContent;
import ai.ecma.codingbat.exceptions.RestException;
import ai.ecma.codingbat.payload.ApiResult;
import ai.ecma.codingbat.payload.AttachmentDTO;
import ai.ecma.codingbat.repository.AttachmentContentRepository;
import ai.ecma.codingbat.repository.AttachmentRepository;
import ai.ecma.codingbat.service.contract.AttachmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Value("${app.upload.folder}")
    private String UPLOAD_FOLDER_PATH;

    @SneakyThrows
    @Override
    public ApiResult<List<AttachmentDTO>> uploadDB(MultipartHttpServletRequest request) {

        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();

        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            String key = fileNames.next();
            List<MultipartFile> files = request.getFiles(key);
            for (MultipartFile file : files) {
                Attachment attachment = new Attachment();
                attachment.setName(file.getOriginalFilename());
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment = attachmentRepository.save(attachment);
                attachmentDTOList.add(AttachmentDTO.mapAttachmentToAttachmentDTO(attachment));



                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setContent(file.getBytes());
                attachmentContent.setAttachment(attachment);
                attachmentContentRepository.save(attachmentContent);
            }
        }

        return ApiResult.successResponse(attachmentDTOList);
    }

    //    @SneakyThrows
    @SneakyThrows
    @Override
    public ApiResult<List<AttachmentDTO>> uploadFS(MultipartHttpServletRequest request) {
        List<AttachmentDTO> attachmentDTOList = new ArrayList<>();

        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            String key = fileNames.next();
            List<MultipartFile> files = request.getFiles(key);
            for (MultipartFile file : files) {
                Attachment attachment = new Attachment();
                attachment.setName(file.getOriginalFilename());
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment = attachmentRepository.save(attachment);
                attachmentDTOList.add(AttachmentDTO.mapAttachmentToAttachmentDTO(attachment));

                FileCopyUtils.copy(file.getInputStream(),
                        makeFileOutputStream(attachment.getId(), Objects.requireNonNull(file.getOriginalFilename())));
            }
        }

        return ApiResult.successResponse(attachmentDTOList);
    }

    @SneakyThrows
    @Override
    public void loadFromDB(@NonNull UUID id, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> RestException.restThrow("Such attachment not found", HttpStatus.NOT_FOUND));

//        response.setHeader("Content-disposition", "attachment; filename=\"" + attachment.getName() + "\"");
        response.setHeader("Content-disposition", "inline; filename=\"" + attachment.getName() + "\"");
        response.setHeader("Cache-Control", "max-age=8640000");
        response.setContentLength(attachment.getSize().intValue());
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(
                attachment.getAttachmentContent().getContent(),
                response.getOutputStream());
    }


    @SneakyThrows
    @Override
    public void loadFromFS(@NonNull UUID id, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> RestException.restThrow("Such attachment not found", HttpStatus.NOT_FOUND));

        response.setHeader("Content-disposition", "inline; filename=\"" + attachment.getName() + "\"");
        response.setHeader("Cache-Control", "max-age=8640000");
        response.setContentLength(attachment.getSize().intValue());
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(
                makeFileInputStream(attachment.getId(),attachment.getName()),
                response.getOutputStream());
    }

    private FileOutputStream makeFileOutputStream(UUID id, String originalName) throws FileNotFoundException {
        return new FileOutputStream(UPLOAD_FOLDER_PATH + id + originalName.substring(originalName.lastIndexOf(".")));
    }

    private FileInputStream makeFileInputStream(UUID id, String originalName) throws FileNotFoundException {
        return new FileInputStream(UPLOAD_FOLDER_PATH + id + originalName.substring(originalName.lastIndexOf(".")));
    }
}
