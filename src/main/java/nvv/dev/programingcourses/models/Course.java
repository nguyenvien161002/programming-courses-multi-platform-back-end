package nvv.dev.programingcourses.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String slug;
    private String description;
    private String image;
    private String icon;
    @Column(name = "video_type")
    private String videoType;
    private String video;
    @Column(name = "old_price")
    private Double oldPrice;
    private Double price;
    @Column(name = "pre_order_price")
    private Double preOrderPrice;
    @Column(name = "students_count")
    private Integer studentsCount;
    @Column(name = "is_pro")
    private Boolean isPro;
    @Column(name = "is_coming_soon")
    private Boolean isComingSoon;
    @Column(name = "is_selling")
    private Boolean isSelling;
    @Column(name = "published_at")
    private Date publishedAt;
    @Column(name = "is_registered")
    private Boolean isRegistered;
    @Column(name = "user_progress")
    private Integer userProgress;
    @Column(name = "last_completed_at")
    private Date lastCompletedAt;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "icon_url")
    private String iconUrl;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "landing_page_url")
    private String landingPageUrl;
    @Column(name = "is_pre_order")
    private Boolean isPreOrder;
    @Column(name = "isPublished")
    private Boolean isPublished;
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date updatedAt;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd ss::mm::HH")
    private Date createdAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "courses_uploads",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "upload_id")
    )
    private Set<Upload> uploads = new HashSet<>();
}

