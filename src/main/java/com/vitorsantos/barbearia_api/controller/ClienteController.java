package com.vitorsantos.barbearia_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorsantos.barbearia_api.dto.ClienteRequestDTO;
import com.vitorsantos.barbearia_api.dto.ClienteResponseDTO;
import com.vitorsantos.barbearia_api.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService clienteService;

  @GetMapping
  public List<ClienteResponseDTO> listarClientes() {
    return clienteService.listarClientes();
  }

  @PostMapping
  public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO dto) {
    ClienteResponseDTO clienteCriado = clienteService.cadastrarCliente(dto);
    return ResponseEntity
        .created(URI.create("/clientes/" + clienteCriado.id()))
        .body(clienteCriado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
    clienteService.deletarCliente(id);
    return ResponseEntity.noContent().build();
  }

}