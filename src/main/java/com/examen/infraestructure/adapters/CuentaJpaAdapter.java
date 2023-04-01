package com.examen.infraestructure.adapters;

import com.examen.config.Log;
import com.examen.web.entity.pojo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import com.wicoder.adapters.BaseJpaAdapter;
import com.examen.domain.port.CuentaPersistencePort;
import com.examen.domain.data.CuentaDto;
import com.examen.infraestructure.entity.Cuenta;
import com.examen.infraestructure.mappers.CuentaMapper;
import com.examen.infraestructure.repository.CuentaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class  CuentaJpaAdapter extends BaseJpaAdapter implements CuentaPersistencePort{

   @Autowired
   protected CuentaRepository repository;

    @Override
    public PageInfo<Cuenta> queryListForPage(Pageable pageable, String numeroCuenta) {
        Page<Cuenta> page ;

        if(numeroCuenta.isEmpty()){
            page = repository.findAll(pageable);
        }else{
            page = repository.findAllByNumeroCuenta(numeroCuenta,pageable);
        }
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }


    @Override
   public CuentaDto findBy(Long id){
       Optional<Cuenta> cuentaOptional = repository.findById(id);
       return cuentaOptional.map(CuentaMapper.INSTANCE::cuentaToCuentaDto).orElse(null);
   }
    @Override
    public CuentaDto findByCuenta(String cuenta){
        Optional<Cuenta> cuentaOptional = repository.getNumeroCuenta(cuenta);
        return cuentaOptional.map(CuentaMapper.INSTANCE::cuentaToCuentaDto).orElse(null);
    }

   @Override
   public List<CuentaDto> findAll(){
       return repository.findAll().stream().map(CuentaMapper.INSTANCE::cuentaToCuentaDto).collect(Collectors.toList());
   }

   @Override
   public CuentaDto save(CuentaDto cuentaDto){
         Cuenta cuenta = CuentaMapper.INSTANCE.cuentaDtoToCuenta(cuentaDto);
         Cuenta cuentaSaved = repository.save(cuenta);
         return CuentaMapper.INSTANCE.cuentaToCuentaDto(cuentaSaved);
   }

   @Override
   public CuentaDto update(CuentaDto cuentaDto){
         Cuenta cuenta = CuentaMapper.INSTANCE.cuentaDtoToCuenta(cuentaDto);
         Cuenta cuentaSaved = repository.save(cuenta);
         return CuentaMapper.INSTANCE.cuentaToCuentaDto(cuentaSaved);
   }

}