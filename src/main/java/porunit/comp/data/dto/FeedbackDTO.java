package porunit.comp.data.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FeedbackDTO {
    private String header;
    private String text;
}
