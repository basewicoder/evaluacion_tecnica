package com.examen.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.examen.domain.data.CuentaDto;
import com.examen.infraestructure.entity.Cuenta;

@Mapper
public interface  CuentaMapper{

     CuentaMapper INSTANCE= Mappers.getMapper(CuentaMapper.class);
     CuentaDto cuentaToCuentaDto(Cuenta cuenta);
     Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);
}
