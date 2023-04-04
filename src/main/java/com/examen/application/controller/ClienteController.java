package com.examen.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.wicoder.filter.*;
import com.examen.infraestructure.entity.Cliente;
import com.examen.domain.data.ClienteDto;
import com.examen.domain.port.ClientePersistencePort;
import com.wicoder.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/v1/cliente")
@CrossOrigin(origins = "*", maxAge = 20000)
public class ClienteController{

    private  final ClientePersistencePort service;

    public ClienteController(ClientePersistencePort service) {
        this.service = service;
    }

    @PostMapping("/paginate")
    @Operation(summary = "Lista de cliente paginados",  tags = { "Cliente" })
    public Object paginateCliente(@RequestBody RequestData request) {
        return service.db().table(Cliente.class).request(request).paginate();
    }


    @GetMapping("/all")
    @Operation(summary = "Listar de cliente",  tags = { "Cliente" })
    public ResponseEntity<List<ClienteDto>> allCliente() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por el ID de la cliente",tags = { "Cliente" })
    public ResponseEntity<ClienteDto> findBy(@PathVariable(value = "id") Long id) throws Exception {
        ClienteDto clienteDto = service.findBy(id);
        if (clienteDto == null) {
            throw new ResourceNotFoundException("No existe cliente con ese codigo:: " + id);
        }
        return ResponseEntity.ok().body(clienteDto);
    }

    @PostMapping()
    @Operation(summary = "Crear un nuevo cliente", tags = { "Cliente" })
    public ClienteDto create(@RequestBody ClienteDto clienteDto) throws Exception {
        return service.save(clienteDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos del cliente", tags = { "Cliente" })
    public ResponseEntity<ClienteDto> update(@PathVariable(value = "id") Long id,@RequestBody ClienteDto clienteDto) throws Exception {
     ClienteDto data = service.update(clienteDto);
       if(data == null) {
         throw new ResourceNotFoundException("No existe Cliente con ese codigo:: " + id);
       }
       return ResponseEntity.ok(clienteDto);
    }

}
