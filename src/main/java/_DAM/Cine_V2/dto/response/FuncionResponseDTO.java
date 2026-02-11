package _DAM.Cine_V2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionResponseDTO {

        private Long id;
        private LocalDateTime fechaHora;
        private double precio;
        private PeliculaResponseDTO pelicula;
        private SalaResponseDTO sala;
}
