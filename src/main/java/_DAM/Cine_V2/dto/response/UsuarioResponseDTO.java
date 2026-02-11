package _DAM.Cine_V2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

        private Long id;
        private String email;
        private Set<RolResponseDTO> roles;
}
