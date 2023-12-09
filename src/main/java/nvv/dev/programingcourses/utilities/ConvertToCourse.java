package nvv.dev.programingcourses.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nvv.dev.programingcourses.models.Course;

public class ConvertToCourse {

    public static Course convertToCourse(String courseJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(courseJson, Course.class);
    }
}
