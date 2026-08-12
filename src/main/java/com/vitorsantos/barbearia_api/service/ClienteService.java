package com.vitorsantos.barbearia_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitorsantos.barbearia_api.dto.ClienteRequestDTO;
import com.vitorsantos.barbearia_api.dto.ClienteResponseDTO;
import com.vitorsantos.barbearia_api.exception.ErrorCode;
import com.vitorsantos.barbearia_api.exception.ResourceNotFoundException;
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
  public List<ClienteResponseDTO> listarClientes() {
    return clienteRepository.findAll().stream()
        .map(ClienteResponseDTO::fromEntity)
        .toList();
  }

  // método para cadastrar clientes
  public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO dto) {
    Cliente cliente = new Cliente();
    cliente.setNome(dto.nome());
    cliente.setNumero(dto.numero());

    Cliente clienteSalvo = clienteRepository.save(cliente);
    return ClienteResponseDTO.fromEntity(clienteSalvo);
  }

  // método para deletar clientes
  public void deletarCliente(Long id) {
    if (!clienteRepository.existsById(id)) {
      throw new ResourceNotFoundException(
          "Cliente não encontrado com o ID: " + id,
          ErrorCode.CLIENTE_NAO_ENCONTRADO);
    }
    clienteRepository.deleteById(id);
  }

}