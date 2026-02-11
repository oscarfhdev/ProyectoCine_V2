package _DAM.Cine_V2.servicio;

import _DAM.Cine_V2.dto.request.UsuarioRequestDTO;
import _DAM.Cine_V2.dto.response.UsuarioResponseDTO;
import _DAM.Cine_V2.mapper.UsuarioMapper;
import _DAM.Cine_V2.modelo.Rol;
import _DAM.Cine_V2.modelo.Usuario;
import _DAM.Cine_V2.repositorio.RolRepository;
import _DAM.Cine_V2.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Transactional
    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(dto.getPassword());

        if (dto.getRolIds() != null && !dto.getRolIds().isEmpty()) {
            List<Rol> roles = rolRepository.findAllById(dto.getRolIds());
            usuario.setRoles(new HashSet<>(roles));
        }

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(saved);
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        existing.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(dto.getPassword());
        }

        if (dto.getRolIds() != null) {
            List<Rol> roles = rolRepository.findAllById(dto.getRolIds());
            existing.setRoles(new HashSet<>(roles));
        }

        Usuario saved = usuarioRepository.save(existing);
        return usuarioMapper.toResponse(saved);
    }

    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
