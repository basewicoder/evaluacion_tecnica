package com.examen.application.controller;

import com.examen.application.outIn.DepositoRequest;
import com.examen.application.outIn.RetiroRequest;
import com.examen.config.CommonRuntimeException;
import com.examen.config.Log;
import com.examen.config.RestReturn;
import com.examen.config.RestReturnEntity;
import com.examen.domain.port.CuentaPersistencePort;
import com.examen.domain.services.CuentaService;
import com.examen.infraestructure.entity.Cuenta;
import com.examen.web.entity.SysUser;
import com.examen.web.entity.pojo.PageInfo;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.wicoder.filter.*;
import com.examen.infraestructure.entity.Transaccion;
import com.examen.domain.data.TransaccionDto;
import com.examen.domain.port.TransaccionPersistencePort;
import com.wicoder.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/v1/transaccion")
@CrossOrigin(origins = "*", maxAge = 20000)
public class TransaccionController{

    private  final TransaccionPersistencePort service;

    @Autowired
    CuentaService cuentaService;

    public TransaccionController(TransaccionPersistencePort service) {
        this.service = service;
    }


    @GetMapping("/list")
    @Operation(summary = "Lista de transaccion",  tags = { "Transaccion" })
    public ResponseEntity<RestReturnEntity<PageInfo<Transaccion>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize,  @RequestParam Map<String, String> req) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");
        Log.log(req);
        PageInfo<Transaccion> pageInfo = service.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }

    @PostMapping("/paginate")
    @Hidden
    @Operation(summary = "Lista de transaccion paginados",  tags = { "Transaccion" })
    public Object paginateTransaccion(@RequestBody RequestData request) {
        return service.db().table(Transaccion.class).request(request).paginate();
    }

    @GetMapping("/all")
    @Operation(summary = "Listar historial de transaccion",  tags = { "Transaccion" })
    public ResponseEntity<List<TransaccionDto>> allTransaccion() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar por el ID de la transaccion",tags = { "Transaccion" })
    public ResponseEntity<TransaccionDto> findBy(@PathVariable(value = "id") Long id) throws Exception {
        TransaccionDto transaccionDto = service.findBy(id);
        if (transaccionDto == null) {
            throw new ResourceNotFoundException("No existe transaccion con ese codigo:: " + id);
        }
        return ResponseEntity.ok().body(transaccionDto);
    }

    @PostMapping("/{id}/retiros")
    @Operation(summary = "Retiro de dinero de la cuenta", tags = { "Transaccion" })
    public ResponseEntity<RestReturnEntity<Object>> retiros(@PathVariable Long id,
                                                            @RequestBody RetiroRequest retiroReques){
        try {
            cuentaService.realizarRetiro(id, retiroReques.getMonto(),retiroReques.getMoneda().name());
            return RestReturn.ok();
        } catch (CommonRuntimeException e) {
            return RestReturn.fail(e.getMessage());
        }
    }

    @PostMapping("/{id}/depositos")
    @Operation(summary = "Crear un nueva cuenta", tags = { "Transaccion" })
    public ResponseEntity<RestReturnEntity<Object>> depositos(@PathVariable Long id,
                                    @RequestBody DepositoRequest depositoReques) throws Exception {
        try {

            cuentaService.realizarDeposito(id, depositoReques.getMonto(),depositoReques.getMoneda().name());

            return RestReturn.ok();
        } catch (CommonRuntimeException e) {
            return RestReturn.fail(e.getMessage());
        }

    }



}
