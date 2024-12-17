package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "userdata")
@Data
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uniqueID")
    private Integer uniqueId;

    @Column(name = "name", length = 16)
    private String name;

    @Column(name = "surname", length = 16)
    private String surname;

    @Column(name = "birthdate")
    private LocalDateTime birthdate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}