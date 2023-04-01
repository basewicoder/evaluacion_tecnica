package com.examen.domain.services;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CuentaService {

     void realizarRetiro(Long id, BigDecimal monto,String Moneda);

     void realizarDeposito(Long id, BigDecimal monto,String Moneda);
}
