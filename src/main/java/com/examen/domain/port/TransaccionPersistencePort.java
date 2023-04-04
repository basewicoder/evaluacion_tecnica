package com.examen.domain.port;

import java.util.List;

import com.examen.web.entity.SysUser;
import com.examen.web.entity.pojo.PageInfo;
import com.wicoder.persistence.BasePersistence;
import com.examen.domain.data.TransaccionDto;
import com.examen.infraestructure.entity.Transaccion;
import org.springframework.data.domain.Pageable;

public interface TransaccionPersistencePort extends BasePersistence{

   TransaccionDto findBy(Long id);

   List<TransaccionDto> findAll();

   TransaccionDto save(TransaccionDto transaccionDto);

   TransaccionDto update(TransaccionDto transaccionDto);

    PageInfo<Transaccion> queryListForPage(Pageable pageable);
}