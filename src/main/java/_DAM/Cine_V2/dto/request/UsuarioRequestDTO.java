package _DAM.Cine_V2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

        @Email(message = "El formato del email no es válido")
        @NotBlank(message = "El email no puede estar vacío")
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        private String password;

        private List<Long> rolIds;
}
