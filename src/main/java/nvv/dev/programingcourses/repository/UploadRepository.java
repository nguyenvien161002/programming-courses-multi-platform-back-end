package nvv.dev.programingcourses.repository;

import nvv.dev.programingcourses.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload ,Long> {
    Optional<Upload> findByFileNameOriginal(String fileNameOriginal);
    Optional<Upload> findByFileNameStored(String fileNameStored);
}