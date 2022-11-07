package com.educando.aprendendo.Domain.sevice;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educando.aprendendo.Domain.model.Cliente;
import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.model.StatusEntrega;
import com.educando.aprendendo.Domain.repositories.ClienteRepository;
import com.educando.aprendendo.Domain.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Solicitacao_EntregaService {
	
	private CatagoloClienteService catalogo;
	
	private EntregaRepository entregaRepositorio;
	private ClienteRepository clienteRepositorio;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = catalogo.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepositorio.save(entrega);
	}

}
