package com.examen.infraestructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examen.infraestructure.entity.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long>{
}
