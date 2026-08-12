package com.vitorsantos.barbearia_api.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorsantos.barbearia_api.models.Cliente;
import com.vitorsantos.barbearia_api.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService clienteService;

  @GetMapping
  public List<Cliente> listarClientes() {
    return clienteService.listarClientes();
  }

  @PostMapping
  public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
    Cliente novoCliente = clienteService.cadastrarCliente(cliente);
    return ResponseEntity.ok(novoCliente);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCliente(Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }
  
}
