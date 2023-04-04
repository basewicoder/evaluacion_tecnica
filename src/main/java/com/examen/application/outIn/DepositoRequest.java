package com.examen.application.outIn;

import com.examen.infraestructure.entity.Enuns.TipoMoneda;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositoRequest {
   private BigDecimal monto;
   private TipoMoneda moneda;
}
