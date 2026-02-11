package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.ActorRequestDTO;
import _DAM.Cine_V2.dto.response.ActorResponseDTO;
import _DAM.Cine_V2.modelo.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActorMapper {

    ActorResponseDTO toResponse(Actor actor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "peliculas", ignore = true)
    Actor toEntity(ActorRequestDTO dto);
}
