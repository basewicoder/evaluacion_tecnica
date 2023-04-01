package com.examen.application.outIn;

import com.examen.infraestructure.entity.Enuns.TipoMoneda;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class RetiroRequest {
   private BigDecimal monto;
   private TipoMoneda moneda;

}
