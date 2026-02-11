package _DAM.Cine_V2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {

        private Long id;
        private LocalDateTime fecha;
        private double importeTotal;
        private String estado;
        private Long usuarioId;
        private List<EntradaResponseDTO> entradas;
}
