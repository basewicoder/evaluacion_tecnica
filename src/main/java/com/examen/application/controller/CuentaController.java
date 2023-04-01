package com.examen.application.controller;

import com.examen.config.Log;
import com.examen.config.RestReturn;
import com.examen.config.RestReturnEntity;
import com.examen.infraestructure.entity.Transaccion;
import com.examen.web.entity.pojo.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

import com.wicoder.filter.*;
import com.examen.infraestructure.entity.Cuenta;
import com.examen.domain.data.CuentaDto;
import com.examen.domain.port.CuentaPersistencePort;
import com.wicoder.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/v1/cuenta")
@CrossOrigin(origins = "*", maxAge = 20000)
public class CuentaController{

    private  final CuentaPersistencePort service;

    public CuentaController(CuentaPersistencePort service) {
        this.service = service;
    }

    @PostMapping("/paginate")
    @Operation(summary = "Lista de cuentas paginados",  tags = { "Cuenta" })
    public Object paginateCuenta(@RequestBody RequestData request) {
        return service.db().table(Cuenta.class).request(request).paginate();
    }


    @GetMapping("/list")
    @Operation(summary = "Lista de transaccion",  tags = { "Transaccion" })
    public ResponseEntity<RestReturnEntity<PageInfo<Cuenta>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                        @RequestParam(defaultValue = "10") int pageSize,
                                                                         @RequestParam(value = "numeroCuenta",defaultValue = "") String numeroCuenta
                                                                         /* @RequestParam Map<String, String> req*/) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");
        //Log.log(req);
        PageInfo<Cuenta> pageInfo = service.queryListForPage(pageable,numeroCuenta);
        return RestReturn.ok(pageInfo);
    }


    @GetMapping("/all")
    @Operation(summary = "Listar de cuentas",  tags = { "Cuenta" })
    public ResponseEntity<List<CuentaDto>> allCuenta() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Consultar por el ID de la cuenta",
            description = "Consulta de saldo, Consulta estado cuenta, etc..",
            tags = { "Cuenta" })
    public ResponseEntity<CuentaDto> findBy(@PathVariable(value = "id") Long id) throws Exception {
        CuentaDto cuentaDto = service.findBy(id);
        if (cuentaDto == null) {
            throw new ResourceNotFoundException("No existe cuenta con ese codigo:: " + id);
        }
        return ResponseEntity.ok().body(cuentaDto);
    }
    @GetMapping("/consulta/{numerocuenta}")
    @Operation(summary = "Consultar por el Numero de la cuenta",
            description = "Consulta de saldo, Consulta estado cuenta, etc..",
            tags = { "Cuenta" })
    public ResponseEntity<CuentaDto> findByCuenta(@PathVariable(value = "numerocuenta") String numerocuenta){
        CuentaDto cuentaDto = service.findByCuenta(numerocuenta);
        if (cuentaDto == null) {
            throw new ResourceNotFoundException("No existe cuenta con ese Nuermo de Cuenta :: " + numerocuenta);
        }
        return ResponseEntity.ok().body(cuentaDto);
    }
    @PostMapping()
    @Operation(summary = "Crear un nueva cuenta", tags = { "Cuenta" })
    public CuentaDto create(@RequestBody CuentaDto cuentaDto) throws Exception {
        return service.save(cuentaDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta", tags = { "Cuenta" })
    public ResponseEntity<CuentaDto> update(@PathVariable(value = "id") Long id,@RequestBody CuentaDto cuentaDto) throws Exception {
     CuentaDto data = service.update(cuentaDto);
       if(data == null) {
         throw new ResourceNotFoundException("No existe Cuenta con ese codigo:: " + id);
       }
       return ResponseEntity.ok(cuentaDto);
    }

}
