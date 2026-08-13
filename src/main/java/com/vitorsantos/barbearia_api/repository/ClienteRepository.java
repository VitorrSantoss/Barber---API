package com.vitorsantos.barbearia_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorsantos.barbearia_api.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  List<Cliente> findByUltimoLoginBefore(LocalDateTime data);

  Optional<Cliente> findByNumero(String numero);

  boolean existsByNumero(String numero);
  
}
