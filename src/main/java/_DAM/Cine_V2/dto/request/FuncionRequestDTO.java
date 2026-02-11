package _DAM.Cine_V2.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionRequestDTO {

        @NotNull(message = "La fecha y hora son obligatorias")
        private LocalDateTime fechaHora;

        @Min(value = 0, message = "El precio no puede ser negativo")
        private double precio;

        @NotNull(message = "Debe haber una película asignada")
        private Long peliculaId;

        @NotNull(message = "Debe haber una sala asignada")
        private Long salaId;
}
