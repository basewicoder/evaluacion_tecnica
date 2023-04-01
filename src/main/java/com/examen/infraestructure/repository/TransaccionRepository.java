package com.examen.infraestructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examen.infraestructure.entity.Transaccion;

@Repository
public interface TransaccionRepository  extends JpaRepository<Transaccion, Long>{
}
