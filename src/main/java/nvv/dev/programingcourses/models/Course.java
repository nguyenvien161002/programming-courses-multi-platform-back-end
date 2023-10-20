package nvv.dev.programingcourses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_courses")
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
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
    private boolean isPro;
    @Column(name = "is_coming_soon")
    private boolean isComingSoon;
    @Column(name = "is_selling")
    private boolean isSelling;
    @Column(name = "published_at")
    private Date publishedAt;
    @Column(name = "is_registered")
    private boolean isRegistered;
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
    private boolean isPreOrder;
    @Column(name = "isPublished")
    private boolean is_published;
}

