package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "companiondata")
@Data
public class CompanionData {

    @Id
    @Column(name = "data_id", nullable = false)
    private Integer dataId;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "companion_id", nullable = false)
    private Companion companion;

}
