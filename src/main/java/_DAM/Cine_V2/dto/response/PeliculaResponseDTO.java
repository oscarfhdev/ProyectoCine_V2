package _DAM.Cine_V2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaResponseDTO {

        private Long id;
        private String titulo;
        private int duracion;
        private int edadMinima;
        private DirectorResponseDTO director;
        private List<ActorResponseDTO> actores;
}
