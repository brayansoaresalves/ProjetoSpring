package com.educando.aprendendo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educando.aprendendo.Domain.input.OcorrenciaInput;
import com.educando.aprendendo.Domain.model.Entrega;
import com.educando.aprendendo.Domain.model.Ocorrencia;
import com.educando.aprendendo.Domain.model.OcorrenciaModel;
import com.educando.aprendendo.Domain.sevice.BuscaEntregaService;
import com.educando.aprendendo.Domain.sevice.RegistroOcorrenciaService;
import com.educando.aprendendo.assemble.OcorrenciaAssemble;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService; 
	private OcorrenciaAssemble ocorrenciaAssemble;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar (@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssemble.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssemble.toCollectionModel(entrega.getOcorrencias());
	}

}
