package com.examen.domain.data;

import lombok.*;

import java.lang.Long;
import com.examen.infraestructure.entity.Enuns.TipoTransaccion;
import java.lang.Double;
import com.examen.infraestructure.entity.Enuns.TipoMoneda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.lang.String;
import com.examen.infraestructure.entity.Cuenta;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto{

      private Long id;
      private TipoTransaccion tipo;
      private BigDecimal monto;
      private TipoMoneda moneda;
      private LocalDateTime fecha;
      private BigDecimal saldo;
      private String descripcion;
      private CuentaDto cuenta;

    //  private Cuenta cuenta;
   // private CuentaDto cuenta;

}