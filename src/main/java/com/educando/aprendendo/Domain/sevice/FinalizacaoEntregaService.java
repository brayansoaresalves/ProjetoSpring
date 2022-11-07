package com.educando.aprendendo.Domain.sevice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.model.StatusEntrega;
import com.educando.aprendendo.Domain.repositories.EntregaRepository;
import com.educando.aprendendo.controller.Exception.NegocioException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FinalizacaoEntregaService {
	
	private EntregaRepository repository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		
		entrega.setStatus(StatusEntrega.FINALIZADA);
		
		repository.save(entrega);
		
		
	}

}
