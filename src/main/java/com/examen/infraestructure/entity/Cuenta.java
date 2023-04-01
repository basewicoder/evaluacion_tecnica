package com.examen.infraestructure.entity;


import com.examen.infraestructure.entity.Enuns.TipoEstado;
import com.examen.infraestructure.entity.Enuns.TipoMoneda;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoMoneda moneda;

    @Enumerated(EnumType.STRING)
    private TipoEstado estadoCuenta;



}