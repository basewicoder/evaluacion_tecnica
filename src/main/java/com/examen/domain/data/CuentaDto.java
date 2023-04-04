package com.examen.domain.data;

import com.examen.infraestructure.entity.Transaccion;
import lombok.*;

import java.lang.Long;
import java.lang.String;
import com.examen.infraestructure.entity.Cliente;
import java.math.BigDecimal;
import java.util.List;

import com.examen.infraestructure.entity.Enuns.TipoMoneda;
import com.examen.infraestructure.entity.Enuns.TipoEstado;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto{

      private Long id;
      private String numeroCuenta;
      private ClienteDto cliente;
      private BigDecimal saldo;
      private TipoMoneda moneda;
      private TipoEstado estadoCuenta;

     // private List<TransaccionDto> transacciones;

}