package _DAM.Cine_V2.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequestDTO {

        @NotBlank(message = "El nombre no puede estar vacío")
        private String nombre;
}
