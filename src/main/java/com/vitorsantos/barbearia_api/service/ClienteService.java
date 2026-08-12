package com.vitorsantos.barbearia_api.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.vitorsantos.barbearia_api.models.Cliente;
import com.vitorsantos.barbearia_api.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
/*
 * Gera um construtor automático para todos os atributos 'final',
 * realizando a injeção de dependências do Spring de forma segura e sem a
 * necessidade do @Autowired.
 */
@RequiredArgsConstructor
public class ClienteService {

  /*
   * O uso do 'final' garante a imutabilidade da dependência, blindando a
   * classe contra alterações indesejadas em tempo de execução.
   */
  private final ClienteRepository clienteRepository;

  // método para listar clientes
  public List<Cliente> listarClientes() {
    return clienteRepository.findAll();
  }

  // método para cadastrar clientes
  public Cliente cadastrarCliente(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  // método para deletar clientes
  public void deletarCliente(Long id) {
    if (!clienteRepository.existsById(id)) {
      throw new RuntimeException("Cliente não encontrado com o ID: " + id);
    }
    clienteRepository.deleteById(id);

  }


}
