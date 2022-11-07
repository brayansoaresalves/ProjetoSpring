package com.educando.aprendendo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educando.aprendendo.Domain.model.Cliente;
import com.educando.aprendendo.Domain.repositories.ClienteRepository;
import com.educando.aprendendo.Domain.sevice.CatagoloClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {
	
	/*
	 * @PersistenceContext private EntityManager manager;
	 */
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CatagoloClienteService catalogo;
	
	@GetMapping
	public List<Cliente> listar(){
		return repository.findAll();
		
	}
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		/*
		 * Optional<Cliente> cliente = repository.findById(clienteId);
		 * 
		 * if (cliente.isPresent()) { return ResponseEntity.ok(cliente.get()); } return
		 * ResponseEntity.notFound().build();
		 */
		// expressao lambidas
		return repository.findById(clienteId)
				.map(cliente ->ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return repository.save(cliente);
		return catalogo.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
		if (!repository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		//cliente = repository.save(cliente);
		catalogo.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!repository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		//repository.deleteById(clienteId);
		catalogo.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
		
}
