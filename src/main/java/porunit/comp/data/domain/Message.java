package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private DialogSession session;

    @Column(name = "is_user", nullable = false)
    private boolean isUser;

    @Lob
    @Column(name = "data", nullable = false)
    private byte[] data;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

}