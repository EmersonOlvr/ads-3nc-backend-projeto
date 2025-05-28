package com.uninassau.periodo3.backend.projeto.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "agendamento")
@Data
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, length = 150)
	private String nome;

	@Column(nullable = false, length = 150)
	private String email;

	@Column(nullable = false)
	private LocalDateTime data;

	@Column(nullable = false, length = 14)
	private String telefone;
	
}
