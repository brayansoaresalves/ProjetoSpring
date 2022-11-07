package com.educando.aprendendo.Domain.sevice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	
	public BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long idEntrega, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(idEntrega);
		
		return entrega.adicionarOcorrencia(descricao);
	}

}
