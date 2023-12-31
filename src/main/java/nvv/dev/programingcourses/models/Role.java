package nvv.dev.programingcourses.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nvv.dev.programingcourses.enums.ERole;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ERole name;
    private String description;
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date updatedAt;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date createdAt;
}
