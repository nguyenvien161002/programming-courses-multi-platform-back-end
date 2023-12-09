package nvv.dev.programingcourses.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nvv.dev.programingcourses.models.Course;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Object data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Courses {
        @Builder.Default
        private List<Course> free_courses = new ArrayList<>();
        @Builder.Default
        private List<Course> pro_courses = new ArrayList<>();
    }

    public static ApiResponse freeCourses(List<Course> freeCourses) {
        return ApiResponse
                .builder()
                .data(ApiResponse.Courses
                        .builder()
                        .free_courses(freeCourses)
                        .build())
                .build();
    }

    public static ApiResponse proCourses(List<Course> proCourses) {
        return ApiResponse
                .builder()
                .data(Courses
                        .builder()
                        .pro_courses(proCourses)
                        .build())
                .build();
    }

    public static ApiResponse allCourses(List<Course> freeCourses, List<Course> proCourses) {
        return ApiResponse
                .builder()
                .data(ApiResponse.Courses
                        .builder()
                        .free_courses(freeCourses)
                        .pro_courses(proCourses)
                        .build())
                .build();
    }
}


