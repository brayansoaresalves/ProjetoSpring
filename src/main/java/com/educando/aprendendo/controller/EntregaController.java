package com.educando.aprendendo.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educando.aprendendo.Domain.input.EntregaInput;
import com.educando.aprendendo.Domain.model.Destinatario;
import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.repositories.EntregaRepository;
import com.educando.aprendendo.Domain.sevice.FinalizacaoEntregaService;
import com.educando.aprendendo.Domain.sevice.Solicitacao_EntregaService;
import com.educando.aprendendo.assemble.EntregaAssemble;
import com.educando.aprendendo.model.DestinatarioModel;
import com.educando.aprendendo.model.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private Solicitacao_EntregaService entregar;
	private EntregaRepository entregaRepository;
	private EntregaAssemble entregaAssemble;
	private FinalizacaoEntregaService finalizar;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssemble.toEntity(entregaInput);
		Entrega entregaSolicitada = entregar.solicitar(novaEntrega);
		
		return entregaAssemble.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssemble.toCollectionModel(entregaRepository.findAll());
	}
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId).map(entrega -> 
		ResponseEntity.ok(entregaAssemble.toModel(entrega))).orElse(ResponseEntity.notFound().build());
		}
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizar.finalizar(entregaId);  
	}

}
