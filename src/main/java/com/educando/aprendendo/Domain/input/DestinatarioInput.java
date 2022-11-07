package com.educando.aprendendo.Domain.input;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioInput {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String logadouro;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	@NotBlank
	private String bairro;

}
