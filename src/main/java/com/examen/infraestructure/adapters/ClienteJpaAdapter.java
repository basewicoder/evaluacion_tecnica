package com.examen.infraestructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import com.wicoder.adapters.BaseJpaAdapter;
import com.examen.domain.port.ClientePersistencePort;
import com.examen.domain.data.ClienteDto;
import com.examen.infraestructure.entity.Cliente;
import com.examen.infraestructure.mappers.ClienteMapper;
import com.examen.infraestructure.repository.ClienteRepository;

public class  ClienteJpaAdapter extends BaseJpaAdapter implements ClientePersistencePort{

   @Autowired
   protected ClienteRepository repository;

   @Override
   public ClienteDto findBy(Long id){
       Optional<Cliente> clienteOptional = repository.findById(id);
       return clienteOptional.map(ClienteMapper.INSTANCE::clienteToClienteDto).orElse(null);
   }

   @Override
   public List<ClienteDto> findAll(){
       return repository.findAll().stream().map(ClienteMapper.INSTANCE::clienteToClienteDto).collect(Collectors.toList());
   }

   @Override
   public ClienteDto save(ClienteDto clienteDto){
         Cliente cliente = ClienteMapper.INSTANCE.clienteDtoToCliente(clienteDto);
         Cliente clienteSaved = repository.save(cliente);
         return ClienteMapper.INSTANCE.clienteToClienteDto(clienteSaved);
   }

   @Override
   public ClienteDto update(ClienteDto clienteDto){
         Cliente cliente = ClienteMapper.INSTANCE.clienteDtoToCliente(clienteDto);
         Cliente clienteSaved = repository.save(cliente);
         return ClienteMapper.INSTANCE.clienteToClienteDto(clienteSaved);
   }

}