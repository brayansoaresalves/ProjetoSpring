package com.educando.aprendendo.Domain.sevice;

import org.springframework.stereotype.Service;

import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.repositories.EntregaRepository;
import com.educando.aprendendo.controller.Exception.EntidadeNaoEncontradaException;
import com.educando.aprendendo.controller.Exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId).orElseThrow(()
				-> new EntidadeNaoEncontradaException("Entrega nao encontrada"));
	}

}
