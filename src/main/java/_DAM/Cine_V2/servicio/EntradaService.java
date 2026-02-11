package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.EntradaRequestDTO;
import _DAM.Cine_V2.dto.response.EntradaResponseDTO;
import _DAM.Cine_V2.mapper.EntradaMapper;
import _DAM.Cine_V2.modelo.Entrada;
import _DAM.Cine_V2.modelo.EstadoEntrada;
import _DAM.Cine_V2.modelo.Funcion;
import _DAM.Cine_V2.repositorio.EntradaRepository;
import _DAM.Cine_V2.repositorio.FuncionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final FuncionRepository funcionRepository;
    private final EntradaMapper entradaMapper;

    @Transactional(readOnly = true)
    public List<EntradaResponseDTO> findAll() {
        return entradaRepository.findAll().stream()
                .map(entradaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EntradaResponseDTO findById(Long id) {
        return entradaRepository.findById(id)
                .map(entradaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada con ID: " + id));
    }

    @Transactional
    public EntradaResponseDTO save(EntradaRequestDTO dto) {
        if (isSeatOccupied(dto.getFuncionId(), dto.getFila(), dto.getAsiento())) {
            throw new RuntimeException("El asiento " + dto.getFila() + "-" + dto.getAsiento() + " ya está ocupado.");
        }

        Entrada entrada = entradaMapper.toEntity(dto);

        Funcion funcion = funcionRepository.findById(dto.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada con ID: " + dto.getFuncionId()));
        entrada.setFuncion(funcion);
        entrada.setEstado(EstadoEntrada.VENDIDA);

        Entrada saved = entradaRepository.save(entrada);
        return entradaMapper.toResponse(saved);
    }

    public boolean isSeatOccupied(Long funcionId, int fila, int asiento) {
        List<Entrada> entradas = entradaRepository.findByFuncionId(funcionId);
        return entradas.stream().anyMatch(
                e -> e.getFila() == fila && e.getAsiento() == asiento && e.getEstado() != EstadoEntrada.CANCELADA);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new RuntimeException("Entrada no encontrada con ID: " + id);
        }
        entradaRepository.deleteById(id);
    }
}
