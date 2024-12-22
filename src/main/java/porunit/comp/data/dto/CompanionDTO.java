package porunit.comp.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanionDTO {
    private String name;
    private String icon;
    private Integer dataId;
    private String openAiAplId;
    private Integer authorId;
}
