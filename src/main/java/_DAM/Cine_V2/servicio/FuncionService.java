package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.FuncionRequestDTO;
import _DAM.Cine_V2.dto.response.FuncionResponseDTO;
import _DAM.Cine_V2.mapper.FuncionMapper;
import _DAM.Cine_V2.modelo.Funcion;
import _DAM.Cine_V2.modelo.Pelicula;
import _DAM.Cine_V2.modelo.Sala;
import _DAM.Cine_V2.repositorio.FuncionRepository;
import _DAM.Cine_V2.repositorio.PeliculaRepository;
import _DAM.Cine_V2.repositorio.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionService {

    private final FuncionRepository funcionRepository;
    private final PeliculaRepository peliculaRepository;
    private final SalaRepository salaRepository;
    private final FuncionMapper funcionMapper;

    @Transactional(readOnly = true)
    public List<FuncionResponseDTO> findAll() {
        return funcionRepository.findAll().stream()
                .map(funcionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionResponseDTO findById(Long id) {
        return funcionRepository.findById(id)
                .map(funcionMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada con ID: " + id));
    }

    @Transactional
    public FuncionResponseDTO save(FuncionRequestDTO dto) {
        Funcion funcion = funcionMapper.toEntity(dto);

        if (dto.getPeliculaId() != null) {
            Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId())
                    .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con ID: " + dto.getPeliculaId()));
            funcion.setPelicula(pelicula);
        }

        if (dto.getSalaId() != null) {
            Sala sala = salaRepository.findById(dto.getSalaId())
                    .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + dto.getSalaId()));
            funcion.setSala(sala);
        }

        Funcion saved = funcionRepository.save(funcion);
        return funcionMapper.toResponse(saved);
    }

    @Transactional
    public FuncionResponseDTO update(Long id, FuncionRequestDTO dto) {
        Funcion funcion = funcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada con ID: " + id));

        funcion.setFechaHora(dto.getFechaHora());
        funcion.setPrecio(dto.getPrecio());

        if (dto.getPeliculaId() != null) {
            Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId())
                    .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con ID: " + dto.getPeliculaId()));
            funcion.setPelicula(pelicula);
        }

        if (dto.getSalaId() != null) {
            Sala sala = salaRepository.findById(dto.getSalaId())
                    .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + dto.getSalaId()));
            funcion.setSala(sala);
        }

        Funcion saved = funcionRepository.save(funcion);
        return funcionMapper.toResponse(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!funcionRepository.existsById(id)) {
            throw new RuntimeException("Funcion no encontrada con ID: " + id);
        }
        funcionRepository.deleteById(id);
    }
}
