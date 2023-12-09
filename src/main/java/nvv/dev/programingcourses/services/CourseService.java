package nvv.dev.programingcourses.services;

import lombok.RequiredArgsConstructor;
import nvv.dev.programingcourses.models.Course;
import nvv.dev.programingcourses.models.Upload;
import nvv.dev.programingcourses.repository.CourseRepository;
import nvv.dev.programingcourses.repository.UploadRepository;
import nvv.dev.programingcourses.response.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Value("${application.base-url}")
    private String BASE_URL;
    @Value("${application.upload-url}")
    private String UPLOAD_URL;
    @Value("${application.upload-image-path}")
    private String UPLOAD_IMAGE_PATH;
    @Value("${application.upload-icon-path}")
    private String UPLOAD_ICON_PATH;
    @Value("${application.upload-video-path}")
    private String UPLOAD_VIDEO_PATH;
    private final CourseRepository courseRepository;

    public void save(Course course) {
        courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getProCourse() {
        return courseRepository.findByIsPro(true);
    }

    public List<Course> getFreeCourse() {
        return courseRepository.findByIsPro(false);
    }

    public Boolean existsById(Integer id) {
        return courseRepository.existsById(id);
    }

    public Course findById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Course not found"));
    }

    public MessageResponse handleNew(
            Course course,
            Optional<Upload> uploadImage,
            Optional<Upload> uploadIcon,
            Optional<Upload> uploadVideo
    ) {
        String msgResponse;
        if (courseRepository.existsById(course.getId())) {
            return MessageResponse.builder().message("Method post: course already exists").build();
        }
        try {
            Course courseSetAtri = this.handleSetAtriCourse(course, uploadImage, uploadIcon, uploadVideo);
            courseSetAtri.setUpdatedAt(new Date());
            courseSetAtri.setCreatedAt(new Date());
            courseRepository.save(courseSetAtri);
            msgResponse = "Course saved successfully";
        } catch (Exception e) {
            msgResponse = "Course saved failed: " + e.getMessage();
        }
        return MessageResponse.builder().message(msgResponse).build();
    }

    public MessageResponse handleUpdate(
            Course course,
            Optional<Upload> uploadImage,
            Optional<Upload> uploadIcon,
            Optional<Upload> uploadVideo
    ) {
        String msgResponse;
        Optional<Course> courseInDb = courseRepository.findById(course.getId());
        if (!courseRepository.existsById(course.getId())) {
            return MessageResponse.builder().message("Method update: course not found").build();
        }
        try {
            Course courseSetAtri = this.handleSetAtriCourse(course, uploadImage, uploadIcon, uploadVideo);
            courseSetAtri.setCreatedAt(courseInDb.get().getCreatedAt());
            courseSetAtri.setUpdatedAt(new Date());
            courseRepository.save(courseSetAtri);
            msgResponse = "Course updated successfully";
        } catch (Exception e) {
            msgResponse = "Course update failed: " + e.getMessage();
        }
        return MessageResponse.builder().message(msgResponse).build();
    }

    private Course handleSetAtriCourse(Course course, Optional<Upload> uploadImage, Optional<Upload> uploadIcon, Optional<Upload> uploadVideo) {
        Set<Upload> listUploads = new HashSet<>();
        listUploads.add(uploadImage.get());
        listUploads.add(uploadIcon.get());
        listUploads.add(uploadVideo.get());
        course.setUploads(listUploads);
        course.setImage(UPLOAD_IMAGE_PATH + uploadImage.get().getFileNameStored());
        course.setImageUrl(BASE_URL + UPLOAD_URL + UPLOAD_IMAGE_PATH + uploadImage.get().getFileNameStored());
        course.setIcon(UPLOAD_ICON_PATH + uploadIcon.get().getFileNameStored());
        course.setIconUrl(BASE_URL + UPLOAD_URL + UPLOAD_ICON_PATH  + uploadIcon.get().getFileNameStored());
        course.setVideo(UPLOAD_VIDEO_PATH + uploadVideo.get().getFileNameStored());
        course.setVideoUrl(BASE_URL + UPLOAD_URL + UPLOAD_VIDEO_PATH  + uploadVideo.get().getFileNameStored());
        return course;
    }

    public MessageResponse handleDelete(Integer id) {
        String msgResponse = "";
        if (!courseRepository.existsById(id)) {
            return MessageResponse.builder().message("Method delete: course not found").build();
        }
        try {
            courseRepository.deleteById(id);
            msgResponse = "Course deleted successfully";
        } catch (Exception e) {
            msgResponse = "Failed to delete the course: " + e.getMessage();
        }
        return MessageResponse.builder().message(msgResponse).build();
    }

}
