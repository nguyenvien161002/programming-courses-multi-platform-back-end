package nvv.dev.programingcourses.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("${application.upload-url}")
@RequiredArgsConstructor
public class UploadController {
    @GetMapping("/uploads/{folder}/{fileNameStored}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String folder, @PathVariable String fileNameStored) {
        try {
            Path fileName = Paths.get("uploads/" + folder, fileNameStored);
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
            if(folder.equals("videos")) {
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("video/mp4"))
                        .body(byteArrayResource);
            } else {
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
