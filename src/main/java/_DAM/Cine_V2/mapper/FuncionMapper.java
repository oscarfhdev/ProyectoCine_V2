package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.FuncionRequestDTO;
import _DAM.Cine_V2.dto.response.FuncionResponseDTO;
import _DAM.Cine_V2.modelo.Funcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { PeliculaMapper.class, SalaMapper.class })
public interface FuncionMapper {

    FuncionResponseDTO toResponse(Funcion funcion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pelicula", ignore = true)
    @Mapping(target = "sala", ignore = true)
    @Mapping(target = "entradas", ignore = true)
    Funcion toEntity(FuncionRequestDTO funcionRequestDTO);
}
