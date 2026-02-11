package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.EntradaRequestDTO;
import _DAM.Cine_V2.dto.request.VentaRequestDTO;
import _DAM.Cine_V2.dto.response.VentaResponseDTO;
import _DAM.Cine_V2.mapper.EntradaMapper;
import _DAM.Cine_V2.mapper.VentaMapper;
import _DAM.Cine_V2.modelo.*;
import _DAM.Cine_V2.repositorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FuncionRepository funcionRepository;
    private final EntradaRepository entradaRepository;
    private final VentaMapper ventaMapper;
    private final EntradaMapper entradaMapper;

    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO findById(Long id) {
        return ventaRepository.findById(id)
                .map(ventaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    @Transactional
    public VentaResponseDTO save(VentaRequestDTO dto) {
        Venta venta = ventaMapper.toEntity(dto);
        venta.setFecha(LocalDateTime.now());
        venta.setEstado("COMPLETADA");

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuarioId()));
        venta.setUsuario(usuario);

        Set<Entrada> entradasEntities = new HashSet<>();
        double importeTotal = 0;

        for (EntradaRequestDTO eDTO : dto.getEntradas()) {
            Funcion funcion = funcionRepository.findById(eDTO.getFuncionId())
                    .orElseThrow(() -> new RuntimeException("Funcion no encontrada " + eDTO.getFuncionId()));

            boolean occupied = entradaRepository.findByFuncionId(funcion.getId()).stream()
                    .anyMatch(e -> e.getFila() == eDTO.getFila() && e.getAsiento() == eDTO.getAsiento()
                            && e.getEstado() != EstadoEntrada.CANCELADA);

            if (occupied) {
                throw new RuntimeException("Asiento ocupado: " + eDTO.getFila() + "-" + eDTO.getAsiento());
            }

            Entrada entrada = entradaMapper.toEntity(eDTO);
            entrada.setFuncion(funcion);
            entrada.setVenta(venta);
            entrada.setEstado(EstadoEntrada.VENDIDA);
            entradasEntities.add(entrada);

            importeTotal += funcion.getPrecio();
        }

        venta.setEntradas(entradasEntities);
        venta.setImporteTotal(importeTotal);

        Venta saved = ventaRepository.save(venta);
        return ventaMapper.toResponse(saved);
    }

    public void deleteById(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }
}
