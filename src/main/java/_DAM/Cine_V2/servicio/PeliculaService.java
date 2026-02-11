package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.PeliculaRequestDTO;
import _DAM.Cine_V2.dto.response.PeliculaResponseDTO;
import _DAM.Cine_V2.mapper.PeliculaMapper;
import _DAM.Cine_V2.modelo.Actor;
import _DAM.Cine_V2.modelo.Director;
import _DAM.Cine_V2.modelo.Pelicula;
import _DAM.Cine_V2.repositorio.ActorRepository;
import _DAM.Cine_V2.repositorio.DirectorRepository;
import _DAM.Cine_V2.repositorio.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final PeliculaMapper peliculaMapper;

    @Transactional(readOnly = true)
    public List<PeliculaResponseDTO> findAll() {
        return peliculaRepository.findAll().stream()
                .map(peliculaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PeliculaResponseDTO findById(Long id) {
        return peliculaRepository.findById(id)
                .map(peliculaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con ID: " + id));
    }

    @Transactional
    public PeliculaResponseDTO save(PeliculaRequestDTO dto) {
        Pelicula pelicula = peliculaMapper.toEntity(dto);

        if (dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Director no encontrado con ID: " + dto.getDirectorId()));
            pelicula.setDirector(director);
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            List<Actor> actores = actorRepository.findAllById(dto.getActorIds());
            if (actores.size() != dto.getActorIds().size()) {
                throw new RuntimeException("Algunos actores no fueron encontrados");
            }
            pelicula.setActores(new HashSet<>(actores));
        }

        Pelicula saved = peliculaRepository.save(pelicula);
        return peliculaMapper.toResponse(saved);
    }

    @Transactional
    public PeliculaResponseDTO update(Long id, PeliculaRequestDTO dto) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con ID: " + id));

        pelicula.setTitulo(dto.getTitulo());
        pelicula.setDuracion(dto.getDuracion());
        pelicula.setEdadMinima(dto.getEdadMinima());

        if (dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Director no encontrado con ID: " + dto.getDirectorId()));
            pelicula.setDirector(director);
        }

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            List<Actor> actores = actorRepository.findAllById(dto.getActorIds());
            if (actores.size() != dto.getActorIds().size()) {
                throw new RuntimeException("Algunos actores no fueron encontrados");
            }
            pelicula.setActores(new HashSet<>(actores));
        }

        Pelicula saved = peliculaRepository.save(pelicula);
        return peliculaMapper.toResponse(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new RuntimeException("Pelicula no encontrada con ID: " + id);
        }
        peliculaRepository.deleteById(id);
    }
}
