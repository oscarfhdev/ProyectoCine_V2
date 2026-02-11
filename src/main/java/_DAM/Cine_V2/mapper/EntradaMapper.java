package _DAM.Cine_V2.mapper;

import _DAM.Cine_V2.dto.request.EntradaRequestDTO;
import _DAM.Cine_V2.dto.response.EntradaResponseDTO;
import _DAM.Cine_V2.modelo.Entrada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntradaMapper {

    @Mapping(target = "funcionId", source = "funcion.id")
    @Mapping(target = "precio", source = "funcion.precio")
    EntradaResponseDTO toResponse(Entrada entrada);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "funcion", ignore = true)
    @Mapping(target = "venta", ignore = true)
    Entrada toEntity(EntradaRequestDTO entradaRequestDTO);
}
