package nvv.dev.programingcourses.services;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.models.Upload;
import nvv.dev.programingcourses.repository.UploadRepository;
import nvv.dev.programingcourses.utilities.Generate;
import nvv.dev.programingcourses.utilities.MakeFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;

    public void save(Upload upload) {
        uploadRepository.save(upload);
    }

    public List<Upload> getAll() {
        return uploadRepository.findAll();
    }

    public Upload findByFileNameStored(String fileNameStored) {
        return uploadRepository.findByFileNameStored(fileNameStored)
                .orElseThrow(() -> new RuntimeException("Error: Upload not found"));
    }

    public Upload findByFileNameOriginal(String fileNameOriginal) {
        return uploadRepository.findByFileNameOriginal(fileNameOriginal)
                .orElseThrow(() -> new RuntimeException("Error: Upload not found"));
    }

    public Optional<Upload> upload(MultipartFile image, String path) {
        Optional<Upload> uploadDb = uploadRepository.findByFileNameOriginal(image.getOriginalFilename());
        try {
            // Nếu mà không tồn tại trong db thì lấy ra và lưu ảnh này, không thì ngược lại
            if(uploadDb.isEmpty()) {
                String uploadsDirectory = System.getProperty("user.dir") + path;
                MakeFile.makeDirectoryUpload(uploadsDirectory);
                Path fileNamePath = Paths.get(uploadsDirectory);
                String fileNameStored = Generate.generateUploadFileNameStored(
                        Objects.requireNonNull(image.getOriginalFilename())
                );
                InputStream inputStream = image.getInputStream();
                Files.copy(
                        inputStream,
                        fileNamePath.resolve(fileNameStored),
                        StandardCopyOption.REPLACE_EXISTING
                );

                return Optional.of(uploadRepository.save(Upload.builder()
                        .type(image.getContentType())
                        .fileNameOriginal(image.getOriginalFilename())
                        .fileNameStored(fileNameStored)
                        .status(true)
                        .updatedAt(new Date())
                        .createdAt(new Date())
                        .build()));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return uploadDb;
    }

}
