package porunit.comp.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class DialogDTO {
    private Integer companionId;
    private List<MessageDTO> messages;
}
