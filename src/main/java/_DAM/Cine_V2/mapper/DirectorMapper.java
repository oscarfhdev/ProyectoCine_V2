package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.DirectorRequestDTO;
import _DAM.Cine_V2.dto.response.DirectorResponseDTO;
import _DAM.Cine_V2.modelo.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DirectorMapper {

    DirectorResponseDTO toResponse(Director director);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "peliculas", ignore = true)
    Director toEntity(DirectorRequestDTO dto);
}
