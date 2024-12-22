package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "companion")
@Data
public class Companion {

    @Id
    @Column(name = "companion_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companionId;

    @Column(name = "name", length = 16, nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "data_id", nullable = false)
    private Integer dataId;

    @Column(name = "openai_apl_id", nullable = false)
    private String openAiAplId;

    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @OneToMany(mappedBy = "companion", cascade = CascadeType.ALL)
    private List<DialogSession> sessions;

    @OneToMany(mappedBy = "companion", cascade = CascadeType.ALL)
    private List<Comment> comments;

}