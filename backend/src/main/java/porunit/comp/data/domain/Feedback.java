package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}