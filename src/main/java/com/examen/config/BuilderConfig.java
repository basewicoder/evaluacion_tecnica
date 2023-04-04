package com.examen.config;

import com.examen.domain.port.ClientePersistencePort;
import com.examen.domain.port.CuentaPersistencePort;
import com.examen.domain.port.TransaccionPersistencePort;
import com.examen.domain.services.CuentaService;
import com.examen.domain.services.impl.CuentaServiceImpl;
import org.springframework.context.annotation.*;
import com.examen.infraestructure.adapters.*;

@Configuration
public class BuilderConfig{

    @Bean
    public ClientePersistencePort clientepersistenceport() {
        return new ClienteJpaAdapter();
    }
    @Bean
    public CuentaPersistencePort cuentapersistenceport() {
        return new CuentaJpaAdapter();
    }
    @Bean
    public TransaccionPersistencePort transaccionpersistenceport() {
        return new TransaccionJpaAdapter();
    }

    @Bean
    public CuentaService cuentaService(){
        return new CuentaServiceImpl();
    }

}