package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @Column(name = "comment_id", nullable = false)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "companion_id", nullable = false)
    private Companion companion;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
