package com.examen.infraestructure.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examen.infraestructure.entity.Cuenta;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long>{

    @Query("select l from Cuenta l where l.numeroCuenta =?1")
    Optional<Cuenta> getNumeroCuenta(String cuenta);

    Page<Cuenta> findAllByNumeroCuenta(String numeroCuenta, Pageable pageable);
}
