package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.UsuarioRequestDTO;
import _DAM.Cine_V2.dto.response.UsuarioResponseDTO;
import _DAM.Cine_V2.modelo.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { RolMapper.class })
public interface UsuarioMapper {

    UsuarioResponseDTO toResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "ventas", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
}
