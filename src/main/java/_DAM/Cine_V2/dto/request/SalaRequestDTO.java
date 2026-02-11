package _DAM.Cine_V2.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaRequestDTO {

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @Min(value = 1, message = "La capacidad debe ser al menos 1")
        private int capacidad;
}
