package com.vitorsantos.barbearia_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorsantos.barbearia_api.models.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
  
}