package _DAM.Cine_V2.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaRequestDTO {

        @NotBlank(message = "El título es obligatorio")
        private String titulo;

        @Min(value = 1, message = "La duración debe ser mayor a 0")
        private int duracion;

        @Min(value = 0, message = "La edad mínima no puede ser negativa")
        private int edadMinima;

        @NotNull(message = "El director es obligatorio")
        private Long directorId;

        private Set<Long> actorIds;
}
