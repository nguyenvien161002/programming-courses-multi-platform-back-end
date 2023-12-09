package nvv.dev.programingcourses.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.models.Course;
import nvv.dev.programingcourses.models.Upload;
import nvv.dev.programingcourses.response.ApiResponse;
import nvv.dev.programingcourses.response.MessageResponse;
import nvv.dev.programingcourses.services.CourseService;
import nvv.dev.programingcourses.services.UploadService;
import nvv.dev.programingcourses.utilities.ConvertToCourse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/docs")
@RequiredArgsConstructor
public class CourseController {
    @Value("${application.upload-image-path}")
    private String UPLOAD_IMAGE_PATH;
    @Value("${application.upload-icon-path}")
    private String UPLOAD_ICON_PATH;
    @Value("${application.upload-video-path}")
    private String UPLOAD_VIDEO_PATH;
    private final CourseService courseService;
    private final UploadService uploadService;

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse> getAllCourses() {
        return ResponseEntity.ok(
                ApiResponse.allCourses(
                        courseService.getFreeCourse(),
                        courseService.getProCourse()
                )
        );
    }

    @GetMapping("/courses/free")
    public ResponseEntity<ApiResponse> getFreeCourses() {
        return ResponseEntity.ok(
                ApiResponse.freeCourses(courseService.getFreeCourse())
        );
    }

    @GetMapping("/courses/pro")
    public ResponseEntity<ApiResponse> getProCourses() {
        return ResponseEntity.ok(
                ApiResponse.proCourses(courseService.getProCourse())
        );
    }

    @PostMapping("/courses")
    public ResponseEntity<MessageResponse> handleNew(
            @RequestParam("course") String courseJson,
            @RequestParam("image") MultipartFile image,
            @RequestParam("icon") MultipartFile icon,
            @RequestParam("video") MultipartFile video
    ) throws JsonProcessingException {
        Course course = ConvertToCourse.convertToCourse(courseJson);
        Optional<Upload> uploadImage = uploadService.upload(image, UPLOAD_IMAGE_PATH);
        Optional<Upload> uploadIcon = uploadService.upload(icon, UPLOAD_ICON_PATH);
        Optional<Upload> uploadVideo = uploadService.upload(video, UPLOAD_VIDEO_PATH);
        return ResponseEntity.ok(courseService.handleNew(course, uploadImage, uploadIcon, uploadVideo));
    }

    @PutMapping("/courses")
    public ResponseEntity<MessageResponse> handleUpdate(
            @RequestParam("course") String courseJson,
            @RequestParam("image") MultipartFile image,
            @RequestParam("icon") MultipartFile icon,
            @RequestParam("video") MultipartFile video
    ) throws JsonProcessingException {
        Course course = ConvertToCourse.convertToCourse(courseJson);;
        Optional<Upload> uploadImage = uploadService.upload(image, UPLOAD_IMAGE_PATH);
        Optional<Upload> uploadIcon = uploadService.upload(icon, UPLOAD_ICON_PATH);
        Optional<Upload> uploadVideo = uploadService.upload(video, UPLOAD_VIDEO_PATH);
        return ResponseEntity.ok(courseService.handleUpdate(course, uploadImage, uploadIcon, uploadVideo));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<MessageResponse> handleDelCourse(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(courseService.handleDelete(id));
    }

}
