package nvv.dev.programingcourses.repository;

import nvv.dev.programingcourses.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByIsPro(Boolean b);
}
