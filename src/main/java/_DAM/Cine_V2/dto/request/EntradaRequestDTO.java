package _DAM.Cine_V2.dto.request;

import _DAM.Cine_V2.modelo.EstadoEntrada;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaRequestDTO {

        @NotNull(message = "La función es obligatoria")
        private Long funcionId;

        @Min(value = 1, message = "La fila debe ser mayor a 0")
        private int fila;

        @Min(value = 1, message = "El asiento debe ser mayor a 0")
        private int asiento;

        private double precio;
}
