package nvv.dev.programingcourses.request;

import lombok.*;
import nvv.dev.programingcourses.models.Course;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private Course course;
}
