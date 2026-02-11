package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.SalaRequestDTO;
import _DAM.Cine_V2.dto.response.SalaResponseDTO;
import _DAM.Cine_V2.mapper.SalaMapper;
import _DAM.Cine_V2.modelo.Sala;
import _DAM.Cine_V2.repositorio.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    public List<SalaResponseDTO> findAll() {
        return salaRepository.findAll().stream()
                .map(salaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SalaResponseDTO findById(Long id) {
        return salaRepository.findById(id)
                .map(salaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id));
    }

    public SalaResponseDTO save(SalaRequestDTO salaRequestDTO) {
        Sala sala = salaMapper.toEntity(salaRequestDTO);
        Sala saved = salaRepository.save(sala);
        return salaMapper.toResponse(saved);
    }

    public SalaResponseDTO update(Long id, SalaRequestDTO salaRequestDTO) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id));
        sala.setNombre(salaRequestDTO.getNombre());
        sala.setCapacidad(salaRequestDTO.getCapacidad());
        Sala saved = salaRepository.save(sala);
        return salaMapper.toResponse(saved);
    }

    public void deleteById(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new RuntimeException("Sala no encontrada con ID: " + id);
        }
        salaRepository.deleteById(id);
    }
}
