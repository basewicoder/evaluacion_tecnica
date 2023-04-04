package com.examen.domain.port;

import java.util.List;

import com.examen.web.entity.pojo.PageInfo;
import com.wicoder.persistence.BasePersistence;
import com.examen.domain.data.CuentaDto;
import com.examen.infraestructure.entity.Cuenta;
import org.springframework.data.domain.Pageable;

public interface CuentaPersistencePort extends BasePersistence{

   CuentaDto findBy(Long id);

   List<CuentaDto> findAll();

   CuentaDto save(CuentaDto cuentaDto);

   CuentaDto update(CuentaDto cuentaDto);

   CuentaDto findByCuenta(String numerocuenta);

    PageInfo<Cuenta> queryListForPage(Pageable pageable, String numeroCuenta);
}