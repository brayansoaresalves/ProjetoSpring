package com.educando.aprendendo.Domain.sevice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educando.aprendendo.Domain.model.Cliente;
import com.educando.aprendendo.Domain.repositories.ClienteRepository;
import com.educando.aprendendo.controller.Exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatagoloClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente salvar (Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}
		
		return clienteRepository.save(cliente);
	}
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
