package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.RolRequestDTO;
import _DAM.Cine_V2.dto.response.RolResponseDTO;
import _DAM.Cine_V2.modelo.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolMapper {

    RolResponseDTO toResponse(Rol rol);

    @Mapping(target = "id", ignore = true)
    Rol toEntity(RolRequestDTO dto);
}
