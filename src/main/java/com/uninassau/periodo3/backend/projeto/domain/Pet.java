package com.uninassau.periodo3.backend.projeto.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pets")
@Data
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, length = 150)
	private String nome;

	@Column(nullable = false, length = 50)
	private String especie;

	@Column(nullable = false, length = 100)
	private String raca;

	@Column(nullable = false)
	private LocalDate dataNascimento;

	@Column
	private Boolean adotado = false;

	@Column(length = 255)
	private String descricao;

	@Column
	private String fotoUrl;
	
}
