package com.examen.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.examen.domain.data.TransaccionDto;
import com.examen.infraestructure.entity.Transaccion;

@Mapper
public interface  TransaccionMapper{

     TransaccionMapper INSTANCE= Mappers.getMapper(TransaccionMapper.class);
     TransaccionDto transaccionToTransaccionDto(Transaccion transaccion);
     Transaccion transaccionDtoToTransaccion(TransaccionDto transaccionDto);
}
