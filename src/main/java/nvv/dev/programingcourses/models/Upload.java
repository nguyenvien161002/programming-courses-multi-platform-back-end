package nvv.dev.programingcourses.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "file_name_original")
    private String fileNameOriginal;
    @Column(name = "file_name_stored")
    private String fileNameStored;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date updatedAt;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date createdAt;
}
