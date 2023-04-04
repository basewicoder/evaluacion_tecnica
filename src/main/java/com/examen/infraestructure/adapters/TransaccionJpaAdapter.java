package com.examen.infraestructure.adapters;

import com.examen.web.entity.SysUser;
import com.examen.web.entity.pojo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import com.wicoder.adapters.BaseJpaAdapter;
import com.examen.domain.port.TransaccionPersistencePort;
import com.examen.domain.data.TransaccionDto;
import com.examen.infraestructure.entity.Transaccion;
import com.examen.infraestructure.mappers.TransaccionMapper;
import com.examen.infraestructure.repository.TransaccionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class  TransaccionJpaAdapter extends BaseJpaAdapter implements TransaccionPersistencePort{

   @Autowired
   protected TransaccionRepository repository;



    @Override
    public PageInfo<Transaccion> queryListForPage(Pageable pageable) {
        Page<Transaccion> page = repository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }


    @Override
   public TransaccionDto findBy(Long id){
       Optional<Transaccion> transaccionOptional = repository.findById(id);
       return transaccionOptional.map(TransaccionMapper.INSTANCE::transaccionToTransaccionDto).orElse(null);
   }

   @Override
   public List<TransaccionDto> findAll(){
       return repository.findAll().stream().map(TransaccionMapper.INSTANCE::transaccionToTransaccionDto).collect(Collectors.toList());
   }

   @Override
   public TransaccionDto save(TransaccionDto transaccionDto){
         Transaccion transaccion = TransaccionMapper.INSTANCE.transaccionDtoToTransaccion(transaccionDto);
         Transaccion transaccionSaved = repository.save(transaccion);
         return TransaccionMapper.INSTANCE.transaccionToTransaccionDto(transaccionSaved);
   }

   @Override
   public TransaccionDto update(TransaccionDto transaccionDto){
         Transaccion transaccion = TransaccionMapper.INSTANCE.transaccionDtoToTransaccion(transaccionDto);
         Transaccion transaccionSaved = repository.save(transaccion);
         return TransaccionMapper.INSTANCE.transaccionToTransaccionDto(transaccionSaved);
   }

}