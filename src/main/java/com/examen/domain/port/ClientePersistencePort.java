package com.examen.domain.port;

import java.util.List;
import com.wicoder.persistence.BasePersistence;
import com.examen.domain.data.ClienteDto;
import com.examen.infraestructure.entity.Cliente;

public interface ClientePersistencePort extends BasePersistence{

   ClienteDto findBy(Long id);

   List<ClienteDto> findAll();

   ClienteDto save(ClienteDto clienteDto);

   ClienteDto update(ClienteDto clienteDto);

}