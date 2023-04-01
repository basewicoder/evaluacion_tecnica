package com.examen.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.examen.domain.data.ClienteDto;
import com.examen.infraestructure.entity.Cliente;

@Mapper
public interface  ClienteMapper{

     ClienteMapper INSTANCE= Mappers.getMapper(ClienteMapper.class);
     ClienteDto clienteToClienteDto(Cliente cliente);
     Cliente clienteDtoToCliente(ClienteDto clienteDto);
}
