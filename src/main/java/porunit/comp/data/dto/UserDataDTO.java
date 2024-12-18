package porunit.comp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDataDTO {
    private String userLogin;
    private String name;
    private String surname;
    private String birthdate;
}
