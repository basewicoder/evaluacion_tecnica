package com.examen.domain.services.impl;

import com.examen.config.CommonRuntimeException;
import com.examen.domain.data.CuentaDto;
import com.examen.domain.port.CuentaPersistencePort;
import com.examen.domain.services.CuentaService;
import com.examen.infraestructure.entity.Cliente;
import com.examen.infraestructure.entity.Cuenta;
import com.examen.infraestructure.entity.Enuns.TipoEstado;
import com.examen.infraestructure.entity.Enuns.TipoMoneda;
import com.examen.infraestructure.entity.Enuns.TipoTransaccion;
import com.examen.infraestructure.entity.Transaccion;
import com.examen.infraestructure.repository.CuentaRepository;
import com.examen.infraestructure.repository.TransaccionRepository;
import com.wicoder.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CuentaServiceImpl  implements CuentaService {

    @Autowired
    CuentaPersistencePort cuentaPersistencePort ;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    TransaccionRepository transaccionRepository;


    @Transactional
    public void realizarRetiro(Long id, BigDecimal monto,String moneda) {


        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el codigo:: " + id));

        if (cuenta == null) {
            throw new ResourceNotFoundException("No existe la cuenta con el codigo:: " + id);
        }
        if (cuenta.getEstadoCuenta() == TipoEstado.HOLD) {
            throw new CommonRuntimeException("No se puede realizar retiros en cuentas en estado HOLD.");
        }

        if (!cuenta.getMoneda().equals(TipoMoneda.valueOf(moneda))) {
            throw new CommonRuntimeException("El retiro debe ser en la misma moneda de la cuenta.");
        }

        BigDecimal saldoActual = cuenta.getSaldo();
        if (monto.compareTo(saldoActual) > 0) {
            cuenta.setEstadoCuenta(TipoEstado.HOLD);
        }
        Transaccion transaccion = new Transaccion();
        transaccion.setTipo(TipoTransaccion.RETIRO);
        transaccion.setMoneda(TipoMoneda.valueOf(moneda));
        transaccion.setMonto(monto.negate());
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setDescripcion("Retiro completa : "+cuenta.getEstadoCuenta() );
        transaccion.setCuenta(cuenta);
        BigDecimal saldo1=cuenta.getSaldo().subtract(monto);

        transaccion.setSaldo(saldo1);
        transaccionRepository.save(transaccion);
        cuenta.setSaldo(saldo1);
        cuentaRepository.save(cuenta);

    }



    @Transactional
    public void realizarDeposito(Long id, BigDecimal monto,String moneda) {

        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe la cuenta con el codigo:: " + id));

        if (cuenta == null) {
            throw new ResourceNotFoundException("No existe la cuenta con el codigo:: " + id);
        }
        if (!cuenta.getMoneda().equals(TipoMoneda.valueOf(moneda))) {
            throw new CommonRuntimeException("El deposito debe ser en la misma moneda de la cuenta.");
        }

        if (cuenta.getEstadoCuenta() == TipoEstado.HOLD && monto.compareTo(cuenta.getSaldo().negate()) >= 0) {
            cuenta.setEstadoCuenta(TipoEstado.ACTIVE);
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo(TipoTransaccion.DEPOSITO);
        transaccion.setMoneda(TipoMoneda.valueOf(moneda));
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setDescripcion("Deposito completa : "+cuenta.getEstadoCuenta());
        transaccion.setCuenta(cuenta);

        BigDecimal saldo1=cuenta.getSaldo().add(monto);

        transaccion.setSaldo(saldo1);
        cuenta.setSaldo(saldo1);

        transaccionRepository.save(transaccion);

        cuentaRepository.save(cuenta);
    }
}



