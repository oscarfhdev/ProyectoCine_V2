package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.PeliculaRequestDTO;
import _DAM.Cine_V2.dto.response.PeliculaResponseDTO;
import _DAM.Cine_V2.modelo.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { DirectorMapper.class, ActorMapper.class })
public interface PeliculaMapper {

    @Mapping(target = "director", source = "director")
    @Mapping(target = "actores", source = "actores")
    PeliculaResponseDTO toResponse(Pelicula pelicula);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "actores", ignore = true)
    @Mapping(target = "funciones", ignore = true)
    Pelicula toEntity(PeliculaRequestDTO dto);
}
