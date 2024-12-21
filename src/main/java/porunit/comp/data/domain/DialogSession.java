package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dialogsession")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DialogSession {

    @Id
    @Column(name = "session_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
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
