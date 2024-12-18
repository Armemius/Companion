package porunit.comp.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import porunit.comp.data.domain.Role;

@Data
@Builder
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private Role role;
}
