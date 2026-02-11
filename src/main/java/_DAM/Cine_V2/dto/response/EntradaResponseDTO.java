package _DAM.Cine_V2.dto.response;

import _DAM.Cine_V2.modelo.EstadoEntrada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaResponseDTO {

        private Long id;
        private int fila;
        private int asiento;
        private double precio;
        private EstadoEntrada estado;
        private Long funcionId;
}
