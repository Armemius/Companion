package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dialogsession")
@Data
public class DialogSession {

    @Id
    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "companion_id", nullable = false)
    private Companion companion;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Message> messages;

}
